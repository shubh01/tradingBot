package com.tradingbot.auction;

import com.tradingbot.data.BidResult;

/**
 * interface with single method to begin auction
 */
public interface Auction {

    /**
     * method to begin the auction
     */
    public BidResult beginAuction();

}
