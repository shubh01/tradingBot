package com.tradingbot;

import com.tradingbot.bidder.Bidder;
import com.tradingbot.bidder.BidderFactory;
import com.tradingbot.auction.impl.AuctionRunner;
import com.tradingbot.data.BidResult;
import com.tradingbot.data.BidderType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Class to execute auction program
 */
public class AuctionMain {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuctionMain.class);

    /**
     *main method to start the auction
     * @param args
     */
    public static void main(String[] args) {
        LOGGER.debug("main method execution starts");
        System.out.println("***********Welcome To The Optimax Energy Auction Event ***************************\n\n");
        System.out.println("Please enter the Product Quantity -  *Quantity Should Be Positive And Even*");
        Scanner in = new Scanner(System.in);
        int quantity = getQuantity(in);
        System.out.println("Enter The Cash Value:");
        int cash = in.nextInt();
        BidderFactory bidderFactory = new BidderFactory();
        Bidder bidder = bidderFactory.getBidder(BidderType.BOT_BIDDER);
        Bidder humanBidder = bidderFactory.getBidder(BidderType.Human_BIDDER);
        AuctionRunner auctionRunner = new AuctionRunner(bidder,humanBidder,cash,quantity);
        BidResult bidResult = auctionRunner.beginAuction();
        System.out.println("*-*-*-*-*-*-Auction Result :"+bidResult.getBidResult()+"-*-*-*-*-*-*");
        LOGGER.debug("main method execution completed");
    }

    /**
     * method to get and check quantity (user input)
     * @param in
     * @return
     */
    private static int getQuantity(Scanner in) {
        int quantity = in.nextInt();
        while (quantity <=0 || (quantity %2 != 0)){
            System.out.println("------ Invalid Quantity-------");
            System.out.println("Quantity should be >0 and even");
            quantity = in.nextInt();
        }
        return quantity;
    }

}
