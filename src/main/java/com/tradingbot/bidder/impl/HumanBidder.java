package com.tradingbot.bidder.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Class for Human Bidder
 */
public class HumanBidder  extends AbstractCommonBidder {

    private final Scanner scanner;

    private Logger logger = LoggerFactory.getLogger(HumanBidder.class);

    public HumanBidder(Scanner scanner){
        this.scanner = scanner;
    }

    /**
     * method contains logic to place bids for user
     * @return
     */
    @Override
    public int placeBid() {
        logger.debug("@method placeBid");

        System.out.println("Place Bid - Positive and less than Cash");
        int bid = scanner.nextInt();

        while (bid <0 || bid>myCash){
            System.out.println("------ Invalid Bid-------");
            System.out.println("Bid should be >=0 and <"+myCash);
            bid = scanner.nextInt();
        }

        return bid;
    }

    /**
     * method contains the logic to deduct cash
     * @param own
     * the bid of this bidder
     * @param other
     */
    @Override
    public void bids(int own, int other) {
        logger.debug("@method bids");
        this.myCash -= own;
    }
}
