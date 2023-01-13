package com.tradingbot.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *  class for BidderType enum
 */
@RunWith(JUnit4.class)
public class BidderTypeTest {

    private static final String BOT_BIDDER = "Bot Bidder";
    private static final String Human_BIDDER = "Human Bidder";
    private static final String BOT_BIDDER_ENUM_CONSTANT = "BOT_BIDDER";

    /**
     * test case for getBidderType method of BidderType enum
     */
    @Test
    public void test(){
        Assert.assertEquals(BOT_BIDDER,BidderType.BOT_BIDDER.getBidderType());
        Assert.assertEquals(Human_BIDDER,BidderType.Human_BIDDER.getBidderType());
        Assert.assertEquals(2,BidderType.values().length);
        Assert.assertEquals(BidderType.BOT_BIDDER,BidderType.valueOf(BOT_BIDDER_ENUM_CONSTANT));
    }
}
