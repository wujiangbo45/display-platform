package com.mapbar.display;

import com.mapbar.common.MockMvcTestBase;

import java.util.HashMap;

/**
 * @Author: wujiangbo
 * @Create: 2017/06/01 8:58
 */
public class TestDisplay extends MockMvcTestBase {
    @org.junit.Test
    public void testDisplay() throws Exception{
        assertSuccess("/display/queryVehicleTotalInfo",new HashMap<String,String>());
    }

}
