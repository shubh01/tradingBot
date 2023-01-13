package com.tradingbot.bidder.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Test class for HumanBidder
 */
@RunWith(JUnit4.class)
public class HumanBidderTest {

    private HumanBidder humanBidder;

    /**
     * Method to initialise test variables
     */
    @Before
    public void setUp(){
        humanBidder = new HumanBidder(new Scanner(new ByteArrayInputStream("10".getBytes(StandardCharsets.UTF_8))));
        humanBidder.init(2,10);
    }

    /**
     * Test case for placeBid method
     */
    @Test
    public void placeBidTest(){
       int bid =  humanBidder.placeBid();
        Assert.assertEquals(10,bid);
    }

    /**
     * Test case for bid method
     */
    @Test
    public void bidsTest(){
        humanBidder.bids(10,10);
        Assert.assertEquals(0,humanBidder.myCash);
    }

}
