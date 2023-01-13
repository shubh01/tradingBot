package com.tradingbot.bidder;

import com.tradingbot.bidder.impl.BotBidder;
import com.tradingbot.bidder.impl.HumanBidder;
import com.tradingbot.data.BidderType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test class for BidderFactory
 */
@RunWith(JUnit4.class)
public class BidderFactoryTest {

    private BidderFactory bidderFactory;

    /**
     * Method to initialise test variables
     */
    @Before
    public void setUp(){
        bidderFactory = new BidderFactory();
    }

    /**
     * Test getBidder method of BidderFactory class for BotBidder instance
     */
    @Test
    public void getBidderTest1(){
        Bidder bidder = bidderFactory.getBidder(BidderType.BOT_BIDDER);
        Assert.assertTrue(bidder instanceof BotBidder);
    }

    /**
     * Test getBidder method of BidderFactory class for HumanBidder instance
     */
    @Test
    public void getBidderTest2(){
        Bidder bidder = bidderFactory.getBidder(BidderType.Human_BIDDER);
        Assert.assertTrue(bidder instanceof HumanBidder);
    }

}
