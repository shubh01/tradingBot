package com.tradingbot.auction.impl;

import com.tradingbot.bidder.Bidder;
import com.tradingbot.bidder.impl.BotBidder;
import com.tradingbot.bidder.impl.HumanBidder;
import com.tradingbot.data.BidResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Test class for AuctionRunner
 */
@RunWith(JUnit4.class)
public class AuctionRunnerTest {

    private AuctionRunner auctionRunner;
    private Bidder firstBidder;
    private Bidder secondBidder;
    private int cash = 100;
    private int productQuantity = 2;
    private static final String WINNER_MAP = "winnerMap";
    private static final String DISPLAY_WINNER = "displayWinner";
    private static final String BID_VALUE = "1";

    /**
     * Method to initialise test variables
     */
    @Before
    public void setUp(){
        firstBidder = new BotBidder();
        secondBidder = new HumanBidder(new Scanner(new ByteArrayInputStream(BID_VALUE.getBytes(StandardCharsets.UTF_8))));
        auctionRunner = new AuctionRunner(firstBidder,secondBidder,cash,productQuantity);
    }

    /**
     * Test case for beginAuction method
     */
    @Test
    public void beginAuctionTest(){
        BidResult bidResult = auctionRunner.beginAuction();
        Assert.assertEquals(BidResult.SYSTEM_WIN.getBidResult(),bidResult.getBidResult());
    }

    /**
     * Test case displayWinner method covering Draw condition
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    @Test
    public void displayWinnerTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        HashMap<BidResult, Integer> winnerMap = new HashMap<>();
        winnerMap.put(BidResult.SYSTEM_WIN,1);
        winnerMap.put(BidResult.USER_WIN,1);
        Field field = auctionRunner.getClass().getDeclaredField(WINNER_MAP);
        field.setAccessible(Boolean.TRUE);
        field.set(auctionRunner,winnerMap);
        Method method = auctionRunner.getClass().getDeclaredMethod(DISPLAY_WINNER);
        method.setAccessible(Boolean.TRUE);
        BidResult bidResult = (BidResult) method.invoke(auctionRunner);
        Assert.assertEquals(BidResult.DRAW,bidResult);
    }

    /**
     * Test case for displayWinner method covering System Win condition
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    @Test
    public void displayWinnerTest2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        HashMap<BidResult, Integer> winnerMap = new HashMap<>();
        winnerMap.put(BidResult.SYSTEM_WIN,3);
        winnerMap.put(BidResult.USER_WIN,1);
        Field field = auctionRunner.getClass().getDeclaredField(WINNER_MAP);
        field.setAccessible(Boolean.TRUE);
        field.set(auctionRunner,winnerMap);
        Method method = auctionRunner.getClass().getDeclaredMethod(DISPLAY_WINNER);
        method.setAccessible(Boolean.TRUE);
        BidResult bidResult = (BidResult) method.invoke(auctionRunner);
        Assert.assertEquals(BidResult.SYSTEM_WIN,bidResult);
    }

    /**
     * Test case for displayWinner method covering User/Human Win condition
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    @Test
    public void displayWinnerTest3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        HashMap<BidResult, Integer> winnerMap = new HashMap<>();
        winnerMap.put(BidResult.SYSTEM_WIN,3);
        winnerMap.put(BidResult.USER_WIN,4);
        Field field = auctionRunner.getClass().getDeclaredField(WINNER_MAP);
        field.setAccessible(Boolean.TRUE);
        field.set(auctionRunner,winnerMap);
        Method method = auctionRunner.getClass().getDeclaredMethod(DISPLAY_WINNER);
        method.setAccessible(Boolean.TRUE);
        BidResult bidResult = (BidResult) method.invoke(auctionRunner);
        Assert.assertEquals(BidResult.USER_WIN,bidResult);
    }

    /**
     * Test case for putBidInWinnerMap method covering User Win condition
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    @Test
    public void putBidInWinnerMapTest1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        HashMap<BidResult, Integer> winnerMap = new HashMap<>();
        Field field = auctionRunner.getClass().getDeclaredField(WINNER_MAP);
        field.setAccessible(Boolean.TRUE);
        field.set(auctionRunner,winnerMap);
        Method method = auctionRunner.getClass().getDeclaredMethod("putBidInWinnerMap",BidResult.class);
        method.setAccessible(Boolean.TRUE);
        method.invoke(auctionRunner,BidResult.USER_WIN);
        Assert.assertEquals(1,winnerMap.size());
    }

    /**
     * Test case for putBidInWinnerMap method covering auction Draw condition
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    @Test
    public void putBidInWinnerMapTest2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        HashMap<BidResult, Integer> winnerMap = new HashMap<>();
        Field field = auctionRunner.getClass().getDeclaredField(WINNER_MAP);
        field.setAccessible(Boolean.TRUE);
        field.set(auctionRunner,winnerMap);
        Method method = auctionRunner.getClass().getDeclaredMethod("putBidInWinnerMap",BidResult.class);
        method.setAccessible(Boolean.TRUE);
        method.invoke(auctionRunner,BidResult.DRAW);
        Assert.assertEquals(1,winnerMap.size());
    }

    /**
     * Test case for putBidInWinnerMap method covering System Win condition along with winner map not empty condition
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    @Test
    public void putBidInWinnerMapTest3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        HashMap<BidResult, Integer> winnerMap = new HashMap<>();
        winnerMap.put(BidResult.SYSTEM_WIN,1);
        Field field = auctionRunner.getClass().getDeclaredField(WINNER_MAP);
        field.setAccessible(Boolean.TRUE);
        field.set(auctionRunner,winnerMap);
        Method method = auctionRunner.getClass().getDeclaredMethod("putBidInWinnerMap",BidResult.class);
        method.setAccessible(Boolean.TRUE);
        method.invoke(auctionRunner,BidResult.SYSTEM_WIN);
        Assert.assertEquals(1,winnerMap.size());
    }

    /**
     * Test case for putBidInWinnerMap method covering auction Draw condition along with winner map not empty condition
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    @Test
    public void putBidInWinnerMapTest4() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        HashMap<BidResult, Integer> winnerMap = new HashMap<>();
        winnerMap.put(BidResult.DRAW,1);
        Field field = auctionRunner.getClass().getDeclaredField(WINNER_MAP);
        field.setAccessible(Boolean.TRUE);
        field.set(auctionRunner,winnerMap);
        Method method = auctionRunner.getClass().getDeclaredMethod("putBidInWinnerMap",BidResult.class);
        method.setAccessible(Boolean.TRUE);
        method.invoke(auctionRunner,BidResult.DRAW);
        Assert.assertEquals(1,winnerMap.size());
    }

}
