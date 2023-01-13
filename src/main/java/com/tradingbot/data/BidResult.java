package com.tradingbot.data;

/**
 * Enum to holds the bid results and description
 */
public enum BidResult {
    SYSTEM_WIN ("System Win"),
    USER_WIN ("User Win"),
    DRAW ("Draw");

    private final String bidResultDescription;

    private BidResult(String value) {
        bidResultDescription = value;
    }

    public String getBidResult() {
        return bidResultDescription;
    }
}

