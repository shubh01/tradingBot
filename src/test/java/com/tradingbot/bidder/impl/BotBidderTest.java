package com.tradingbot.bidder.impl;

import com.tradingbot.data.BidResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Test class for BotBidder
 */
@RunWith(JUnit4.class)
public class BotBidderTest {

    private BotBidder bidder;
    private static final String OPPONENT_CASH = "opponentCash";

    /**
     * Method to initialise test variables
     */
    @Before
    public void setUp(){
        bidder = new BotBidder();
        bidder.init(2,100);
    }

    /**
     * Test case for placeBid method covering total 2 product quantity condition
     */
    @Test
    public void placeBidTest1(){
       int bid = bidder.placeBid();
        Assert.assertEquals(100,bid);
    }

    /**
     * Test case for placeBid method covering 0 cash condition
     */
    @Test
    public void placeBidTest2(){
        bidder.init(2,0);
        int bid = bidder.placeBid();
        Assert.assertEquals(0,bid);
    }

    /**
     * Test case for placeBid method covering opponent cash 0 condition
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void placeBidTest3() throws NoSuchFieldException, IllegalAccessException {
        Field field = bidder.getClass().getSuperclass().getDeclaredField(OPPONENT_CASH);
        field.setAccessible(Boolean.TRUE);
        field.set(bidder,0);
        int bid = bidder.placeBid();
        Assert.assertEquals(1,bid);
    }

    /**
     * Test case for placeBid method covering bidHistory empty condition
     */
    @Test
    public void placeBidTest4(){
        bidder.init(4,1);
        int bid = bidder.placeBid();
        Assert.assertEquals(1,bid);
    }

    /**
     * Test case for placeBid method covering bidHistory non empty condition
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void placeBidTest5() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<Integer> bidHistory = new ArrayList<>();
        bidHistory.add(20);
        bidHistory.add(30);
        Field field = bidder.getClass().getDeclaredField("bidHistory");
        field.setAccessible(Boolean.TRUE);
        field.set(bidder,bidHistory);
        bidder.init(4,100);
        int bid = bidder.placeBid();
        Assert.assertEquals(26,bid);
    }

    /**
     * Test case for findMedian method
     */
    @Test
    public void findMedianTest(){
        int[] bidArray = new int[]{20,30};
        double median = bidder.findMedian(bidArray);
        Assert.assertEquals(new Double(25.0),new Double(median));
    }

    /**
     * Test case for init method
     */
    @Test
    public void initTest(){
        Assert.assertEquals(2,bidder.totalQuantity);
        Assert.assertEquals(100,bidder.myCash);
        Assert.assertEquals(100,bidder.opponentCash);
    }

    /**
     * Test case for compareBids method
     */
    @Test
    public void compareBidsTest(){
        BidResult bidResult = bidder.compareBids(1,2);
        Assert.assertEquals(BidResult.USER_WIN,bidResult);
    }

    /**
     * Test case for bids method covering user bid > system bid condition
     */
    @Test
    public void bidsTest1(){
        bidder.bids(2,10);
        Assert.assertEquals(90,bidder.opponentCash);
        Assert.assertEquals(98,bidder.myCash);
        Assert.assertEquals(2,bidder.opponentPurchasedQuantity);
        Assert.assertEquals(0,bidder.myPurchasedQuantity);
    }

    /**
     * Test case for bids method covering user bid = system bid condition
     */
    @Test
    public void bidsTest2(){
        bidder.bids(10,10);
        Assert.assertEquals(90,bidder.opponentCash);
        Assert.assertEquals(90,bidder.myCash);
        Assert.assertEquals(1,bidder.opponentPurchasedQuantity);
        Assert.assertEquals(1,bidder.myPurchasedQuantity);
    }

    /**
     * Test case for bids method covering user bid < system bid condition
     */
    @Test
    public void bidsTest3(){
        bidder.bids(20,10);
        Assert.assertEquals(90,bidder.opponentCash);
        Assert.assertEquals(80,bidder.myCash);
        Assert.assertEquals(0,bidder.opponentPurchasedQuantity);
        Assert.assertEquals(2,bidder.myPurchasedQuantity);
    }

    /**
     * Test case for findMedian method covering odd array length condition
     */
    @Test
    public void findMedianTest2(){
        int[] bidArray = new int[]{20,30,30};
        double median = bidder.findMedian(bidArray);
        Assert.assertEquals(new Double(30.0),new Double(median));
    }

}
