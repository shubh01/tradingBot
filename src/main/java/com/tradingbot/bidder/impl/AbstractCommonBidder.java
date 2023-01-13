package com.tradingbot.bidder.impl;

import com.tradingbot.bidder.Bidder;
import com.tradingbot.data.BidResult;

import java.util.Arrays;

/**
 * Abstract class which implements Bidder interface and hold common attributes along with provides default implementation of Bidder interface
 */
public abstract class AbstractCommonBidder implements Bidder {

    protected int totalQuantity;
    protected int myCash;
    protected int opponentCash;
    protected int myPurchasedQuantity;
    protected int opponentPurchasedQuantity;

    /**
     * method initialises cash and product quantity
     * @param quantity
     * the quantity
     * @param cash
     */
    @Override
    public void init(int quantity, int cash) {
        totalQuantity = quantity;
        opponentCash = cash;
        myCash = cash;
    }

    /**
     * Method to calculate median of an integer array
     * @param bidArray
     * @return
     */
    protected double findMedian(int bidArray[])
    {
        Arrays.sort(bidArray);
        if (bidArray.length % 2 != 0)
            return (double)bidArray[bidArray.length / 2];
        return (double)(bidArray[(bidArray.length - 1) / 2] + bidArray[bidArray.length / 2]) / 2.0;
    }

    /**
     * this method compares the bid return the bid result
     * @param bidOne
     * @param bidTwo
     * @return
     */
    protected BidResult compareBids(int bidOne, int bidTwo){
        int result = Integer.compare(bidOne,bidTwo);
        return result ==0 ? BidResult.DRAW : result ==1 ? BidResult.SYSTEM_WIN : BidResult.USER_WIN;
    }

}
