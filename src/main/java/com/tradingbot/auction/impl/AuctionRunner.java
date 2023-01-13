package com.tradingbot.auction.impl;

import com.tradingbot.auction.Auction;
import com.tradingbot.bidder.Bidder;
import com.tradingbot.data.BidResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.stream.IntStream;

/**
 * Class implements Auction interface and define beginAuction method
 */
public class AuctionRunner implements Auction {

    private Bidder firstBidder;
    private Bidder secondBidder;
    private int cash;
    private int productQuantity;
    private Logger logger = LoggerFactory.getLogger(AuctionRunner.class);
    private HashMap<BidResult, Integer> winnerMap = new HashMap<>();

    public AuctionRunner(Bidder firstBidder, Bidder secondBidder, int cash, int productQuantity) {
        this.firstBidder = firstBidder;
        this.secondBidder = secondBidder;
        this.cash = cash;
        this.productQuantity = productQuantity;
    }

    /**
     *Method begin auction for both the parties
     * @return
     */
    @Override
    public BidResult beginAuction() {
        firstBidder.init(productQuantity,cash);
        secondBidder.init(productQuantity,cash);
        System.out.println("--------------Auction for "+productQuantity+" products will begin now.---------------");

        IntStream.range(0, productQuantity / 2).forEach(product ->{
            int userBid = secondBidder.placeBid();
            int systemBid = firstBidder.placeBid();
            System.out.println("System Bid: "+systemBid+" User Bid: "+userBid);
            logger.debug("System Bid: "+systemBid+" User Bid: "+userBid);
            firstBidder.bids(systemBid,userBid);
            int comparison = Integer.compare(systemBid,userBid);
            winnerMap = comparison == 0 ? putBidInWinnerMap(BidResult.DRAW) : comparison ==1 ? putBidInWinnerMap(BidResult.SYSTEM_WIN) : putBidInWinnerMap(BidResult.USER_WIN);

        });
        logger.debug("Auction for "+productQuantity+" products has finished. Going to display the winner.");
        return displayWinner();
    }

    /**
     * Method display winner of the auction
     */
    private BidResult displayWinner() {
        if(winnerMap.get(BidResult.USER_WIN) != null && winnerMap.get(BidResult.USER_WIN).equals(winnerMap.get(BidResult.SYSTEM_WIN))){
            logger.debug("Auction Result : Draw");
            return BidResult.DRAW;
        }else{
            BidResult bidResult = winnerMap.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1: entry1.getValue() < entry2.getValue() ? -1 : 0).get().getKey();
            logger.debug("Auction Result : "+bidResult.getBidResult());
            return  bidResult;
        }

    }

    /**
     * Method puts bid winner in map and increments the winner count
     * @param bidResult
     */
    private HashMap<BidResult,Integer> putBidInWinnerMap(BidResult bidResult){
        logger.debug("bidResult: "+bidResult.getBidResult());
        Integer value = winnerMap.get(bidResult);
        if(winnerMap.get(bidResult) == null){
            if(bidResult.equals(BidResult.DRAW))
                winnerMap.put(bidResult,1);
            else winnerMap.put(bidResult,2);
        }else{
            if(bidResult.equals(BidResult.DRAW))
                winnerMap.put(bidResult,value+1);
            else winnerMap.put(bidResult,value+2);
        }
        return winnerMap;
    }
}
