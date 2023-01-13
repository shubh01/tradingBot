package com.tradingbot.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test class for BidResult enum
 */
@RunWith(JUnit4.class)
public class BidResultTest {


    private static final String USER_WIN = "User Win";
    private static final String SYSTEM_WIN = "System Win";
    private static final String DRAW = "Draw";
    private static final String USER_WIN_ENUM_CONSTANT = "USER_WIN";

    /**
     * test case for getBidResult method of BidResult enum
     */
    @Test
    public void test(){
        Assert.assertEquals(USER_WIN,BidResult.USER_WIN.getBidResult());
        Assert.assertEquals(SYSTEM_WIN,BidResult.SYSTEM_WIN.getBidResult());
        Assert.assertEquals(DRAW,BidResult.DRAW.getBidResult());
        Assert.assertEquals(3,BidResult.values().length);
        Assert.assertEquals(BidResult.USER_WIN,BidResult.valueOf(USER_WIN_ENUM_CONSTANT));
    }


}
