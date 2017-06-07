package com.quantour.ssm.util;

import java.util.HashMap;

/**
 * Created by loohaze on 2017/6/7.
 */
public class CodeIndustryMap extends HashMap {

    public static final String KEY_FIELD = "code";

    public static final String KEY_VALUE = "industry";

    public CodeIndustryMap(){

    }

    public CodeIndustryMap(String keyfield,String valuefield){
        this.put(KEY_FIELD,keyfield);
        this.put(KEY_VALUE,valuefield);
    }
}
