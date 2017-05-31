package com.quantour.ssm.plugin;

import com.quantour.ssm.model.DayKLineKey;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;


/**
 * Created by loohaze on 2017/5/25.
 */


@Intercepts(
        {
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        }
)
public class CheckSQLInterceptor implements Interceptor {

    public static String codeList = "";


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameter = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler resultHandler = (ResultHandler) args[3];

        Executor executor = (Executor) invocation.getTarget();

        CacheKey cacheKey;
        BoundSql boundSql;

        if(args.length == 4){
            boundSql = ms.getBoundSql(parameter);
            cacheKey = executor.createCacheKey(ms,parameter,rowBounds,boundSql);
        }else{
            cacheKey = (CacheKey) args[4];
            boundSql = (BoundSql) args[5];
        }
//        System.out.println(parameter.getClass());
//        DayKLineKey test = (DayKLineKey) parameter;

        initCodeList();
        System.out.println(boundSql.getSql());
        String sql = modifySql(boundSql.getSql(),parameter);
        System.out.println(sql);

        BoundSql newSQL = new BoundSql(new Configuration(),sql,boundSql.getParameterMappings(),boundSql.getParameterObject());

//        List list = boundSql.getParameterMappings();
//        for(int i = 0; i < list.size(); i++){
//            ParameterMapping m = (ParameterMapping) list.get(i);
//            System.out.println(m.getExpression());
//        }
//        System.out.println((test.getStockCode()));
//        System.out.println(test.getStockDate());
//        System.out.println(boundSql.getSql());
//        return null;
        return executor.query(ms,parameter,rowBounds,resultHandler,cacheKey,newSQL);
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    public static void initCodeList(){
        try {
            FileReader fileReader = new FileReader("codelist");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            List<String> list = new ArrayList<String>();
            String temp = "";
            while((temp = bufferedReader.readLine()) != null){
                codeList = codeList + "day_stock_" + temp + ",";
            }
            codeList = codeList.substring(0,codeList.length()-1);
//            System.out.println(codeList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String modifySql(String sql, Object parameter){
        if(parameter instanceof DayKLineKey){
            String result = "";
            DayKLineKey dayKLineKey = (DayKLineKey) parameter;
//            String[] list = sql.split("\n" );
            result = sql.replace("day_stock","day_stock_"+dayKLineKey.getStockCode());
//            list[1] = list[1] + "_" + dayKLineKey.getStockCode();
//            for(String s : list){
//                System.out.println(s);
//                result += s;
//            }
            return result;
        }else if(parameter instanceof String){
            String result = "";
            result = sql.replace("day_stock","day_stock_"+(String)parameter);
            return result;
        }else if(parameter instanceof HashMap){
            String result = "";
            if(((HashMap) parameter).containsKey("code")){
                result = sql.replace("day_stock","day_stock_" + ((HashMap) parameter).get("code"));
            }else{
                result = sql;
            }
            return result;
        }else if(parameter instanceof java.sql.Date){
            String result = "";
//            result = sql.replace("day_stock",codeList);
            result = sql;
            return result;
        }

        return sql;
    }
}
