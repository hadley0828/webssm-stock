package com.quantour.ssm.plugin;

import com.quantour.ssm.util.CodeIndustryMap;
import com.quantour.ssm.util.ReflectUtil;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


/**
 * Created by loohaze on 2017/6/7.
 */
@Intercepts(@Signature(method = "handleResultSets",type = ResultSetHandler.class, args = {Statement.class}))
public class MapInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();

        if(target instanceof ResultSetHandler){
            ResultSetHandler resultSetHandler = (ResultSetHandler) target;

            ParameterHandler parameterHandler = ReflectUtil.getFieldValue(resultSetHandler,"parameterHandler");
            Object parameterObj = parameterHandler.getParameterObject();

            if(parameterObj instanceof CodeIndustryMap){
                CodeIndustryMap codeIndustryMap = (CodeIndustryMap) parameterObj;

                Statement stmt = (Statement) invocation.getArgs()[0];
                return handleResultSet(stmt.getResultSet(),codeIndustryMap);
            }
        }
        return invocation.proceed();
    }

    /**
     * 处理结果集
     * @param resultSet
     * @param codeIndustryMap
     * @return
     */
    private Object handleResultSet(ResultSet resultSet, CodeIndustryMap codeIndustryMap) {
        // TODO Auto-generated method stub
        if (resultSet != null) {
            //拿到Key对应的字段
            String code = (String) codeIndustryMap.get(CodeIndustryMap.KEY_FIELD);
            //拿到Value对应的字段
            String industry = (String) codeIndustryMap.get(codeIndustryMap.KEY_VALUE);
            //定义用于存放Key-Value的Map
            Map<String, String> map = new HashMap<String, String>();
            //handleResultSets的结果一定是一个List，当我们的对应的Mapper接口定义的是返回一个单一的元素，并且handleResultSets返回的列表
            //的size为1时，Mybatis会取返回的第一个元素作为对应Mapper接口方法的返回值。
            List<Object> resultList = new ArrayList<Object>();
            try {
                //把每一行对应的Key和Value存放到Map中
                while (resultSet.next()) {
                    String key = (String) resultSet.getObject("stock_code");
                    String value = (String)resultSet.getObject("industry");
                    map.put(key, value);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeResultSet(resultSet);
            }
            //把封装好的Map存放到List中并进行返回
            resultList.add(map);
            return resultList;
        }
        return null;
    }

    /**
     * 关闭ResultSet
     * @param resultSet 需要关闭的ResultSet
     */
    private void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {

        }
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
