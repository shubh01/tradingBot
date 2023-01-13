package com.tradingbot.data;

/**
 * Enum to hold bidder type
 */
public enum BidderType {
    BOT_BIDDER ("Bot Bidder"),
    Human_BIDDER("Human Bidder");

    private final String bidderTypeDescription;

    private BidderType(String value) {
        bidderTypeDescription = value;
    }

    public String getBidderType() {
        return bidderTypeDescription;
    }
}