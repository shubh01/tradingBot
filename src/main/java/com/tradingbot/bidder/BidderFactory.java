package com.tradingbot.bidder;

import com.tradingbot.bidder.impl.BotBidder;
import com.tradingbot.bidder.impl.HumanBidder;
import com.tradingbot.data.BidderType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Concrete class return Bidder instance as per the argument
 */
public class BidderFactory {

    private Logger logger = LoggerFactory.getLogger(BidderFactory.class);

    /**
     * method accepts bidder type an return required bidder instance
     * @param bidderType
     * @return
     */
    public Bidder getBidder(BidderType bidderType){
        logger.debug("bidderType: "+bidderType.getBidderType());
        if(bidderType.equals(BidderType.BOT_BIDDER))
            return new BotBidder();
        else return new HumanBidder(new Scanner(System.in));
    }
}
