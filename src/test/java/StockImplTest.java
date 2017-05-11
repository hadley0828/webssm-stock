package java;

import com.quantour.ssm.service.StockService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by loohaze on 2017/5/11.
 */
public class StockImplTest {

    @Resource
    private StockService stockService;


    @Test
    public void testGetAllDateByCode(){
        String code = "1";
        ArrayList<String> datelist = stockService.getAllDateByCode(code);
        System.out.println(datelist.size());
        for(String s : datelist){
            System.out.println(s);
        }
    }
}
