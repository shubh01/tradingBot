package com.tradingbot.bidder.impl;

import com.tradingbot.data.BidResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class for automated bidder
 */
public class BotBidder extends AbstractCommonBidder {

    private Logger logger = LoggerFactory.getLogger(BotBidder.class);

    private ArrayList<Integer> bidHistory = new ArrayList<>();

    public BotBidder() {
    }

    /**
     * method contains logic to place bids for system
     * @return
     */
    @Override
    public int placeBid() {

        if(this.myCash == 0){
            logger.debug("myCash is 0 returning 0");
            return 0;
        }
        if(opponentCash == 0){
            logger.debug("opponentCash is 0 returning 1");
            return 1;
        }
        if(totalQuantity == 2) {
            logger.debug("totalQuantity is 2 returning all cash");
            return myCash;
        }
        if(bidHistory.size() == 0){
            logger.debug("bid history is empty");
            int randomBid = new Random().nextBoolean() ? 1 : 2;
            return randomBid <= this.myCash ? randomBid : this.myCash;
        }
        double median = findMedian(bidHistory.stream().mapToInt(value -> value).toArray());
        int nextValue = (int) (Math.round(median)) + 1;
        return nextValue <= this.myCash ? nextValue : this.myCash;
    }

    /**
     * method contains the logic to compare the bids of two bidders and allot the quantity accordingly
     * @param own
     * the bid of this bidder
     * @param other
     */
    @Override
    public void bids(int own, int other) {
        logger.debug("bids own:"+own+" other: "+other);
        BidResult bidResult = compareBids(own,other);
        if(bidResult.equals(BidResult.SYSTEM_WIN)){
            this.myPurchasedQuantity += 2;
            this.myCash -= own;
            this.opponentCash -= other;
            bidHistory.add(own);
            System.out.println("Available Cash - System: "+myCash+" User: "+opponentCash+" Product Quantity - System: "+myPurchasedQuantity+" User: "+opponentPurchasedQuantity);
            logger.debug("Available Cash - System: "+myCash+" User: "+opponentCash+" Product Quantity - System: "+myPurchasedQuantity+" User: "+opponentPurchasedQuantity);
        }else if(bidResult.equals(BidResult.DRAW)){
            this.myPurchasedQuantity += 1;
            this.myCash -= own;
            this.opponentCash -= other;
            bidHistory.add(own);
            this.opponentPurchasedQuantity +=1;
            System.out.println("Available Cash - System: "+myCash+" User: "+opponentCash+" Product Quantity - System: "+myPurchasedQuantity+" User: "+opponentPurchasedQuantity);
            logger.debug("Available Cash - System: "+myCash+" User: "+opponentCash+" Product Quantity - System: "+myPurchasedQuantity+" User: "+opponentPurchasedQuantity);
        }else if(bidResult.equals(BidResult.USER_WIN)){
            this.opponentPurchasedQuantity += 2;
            this.opponentCash -= other;
            this.myCash -= own;
            System.out.println("Available Cash - System: "+myCash+" User: "+opponentCash+" Product Quantity - System: "+myPurchasedQuantity+" User: "+opponentPurchasedQuantity);
            logger.debug("Available Cash - System: "+myCash+" User: "+opponentCash+" Product Quantity - System: "+myPurchasedQuantity+" User: "+opponentPurchasedQuantity);
            bidHistory.add(other);
        }
    }
}
