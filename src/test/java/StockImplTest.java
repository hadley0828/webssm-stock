import com.quantour.ssm.model.Stock;
import com.quantour.ssm.util.FKSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by loohaze on 2017/5/11.
 */
public class StockImplTest {

    public static void main(String[] args) {
        SqlSession session = FKSqlSessionFactory.getSqlSession();

        List<Stock> stockList = session.selectList("com.quantour.ssm.dao.StockMapper.getTimesStocks");

        for(Stock stock: stockList){
            System.out.println(stock);
        }
        session.commit();
        session.close();
    }
}
