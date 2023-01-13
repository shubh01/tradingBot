Read Me:-

#Problem Statement:
A product x QU will be auctioned under 2 parties. The parties have each y MU for auction. They then offer an arbitrary number simultaneously of its MU on the first 2 QU of the product. After that, the bids will be visible to both. The 2 QU of the product is awarded to who has offered the most MU; if both bid the same, then both get 1 QU. Both bidders must pay their amount - including the defeated. A bid of 0 MU is allowed. Bidding on each 2 QU is repeated until the supply of x QU is fully auctioned.
Each bidder aims to get a larger amount than its competitor. In an auction, the program that is able to get more QU than the other wins. In case of a tie, the program that retains more MU wins.
Your task is to write a program that can participate in this auction and competes with one of our programs. Please explain its strategy.

#Logic/Strategy:
Created a trading system in which a user/human and system can bid against a number of product quantity(2 quantity at a time).
Initially same cash amount is alloted to both system and user/human.
Implemented a median based trading algorithm. Created a list which contains all the winning bids, system canculates the median of the winning bids,adds one 1 to it and places the next bid.
logic for system next bid:
-if system cash == 0,place bid -- 0.
-if opponent cash is 0, place bid value -- 1.
-if total product quantity is 2 -place maximum bid i.e all system cash.
-if winner bidder history does not exist, place random bid -- 1 or 2.
-if winner bidder history exist, calculate median,add 1,compare with available system cash and place the next bid.

	double median = findMedian(bidHistory.stream().mapToInt(value -> value).toArray());
	int nextBid = (int) (Math.round(median)) + 1;
	return nextBid <= this.myCash ? nextBid : this.myCash;

	protected double findMedian(int bidArray[])
	{
		Arrays.sort(bidArray);
		if (bidArray.length % 2 != 0)
			return (double)bidArray[bidArray.length / 2];
		return (double)(bidArray[(bidArray.length - 1) / 2] + bidArray[bidArray.length / 2]) / 2.0;
	}

#Evidences and attachments
Jacoco test reports are attached along with screenshots of test scenarios.
Overall test coverage of the code is 96%.
Class diagram and sequence diagram are attached.

#Technology Stack:
Core Java 1.8
JUnit
Maven
Jacoco

#Assumptions:
Product quantity will always be non negative and even.
Cash will be always positive.
2 quanity of product will be auctioned at a time.
0 is a valid bid.
In case of draw/tie,1 product unit/quantity will be alloted to both the parties.
Party with maximum number of product quantity will be winner.
