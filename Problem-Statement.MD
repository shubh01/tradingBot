GENERAL INFO | QU = quantity units MU = monetary units
EXPLANATION |A product x QU will be auctioned under 2 parties.
The parties have each y MU for auction. They then offer an arbitrary number simultaneously of its MU on
the first 2 QU of the product. After that, the bids will be visible to both.
The 2 QU of the product is awarded to who has offered the most MU; if both bid the same, then both get 1
QU. Both bidders must pay their amount - including the defeated. A bid of 0 MU is allowed. Bidding on each
2 QU is repeated until the supply of x QU is fully auctioned.
THESIS | Each bidder aims to get a larger amount than its competitor.
In an auction, the program that is able to get more QU than the other wins. In case of a tie, the program that
retains more MU wins.

TASK |Your task is to write a program that can participate in this auction and competes with one of our pro-
grams. Please explain its strategy.

THE BIDDER INTERFACE |
package auction;
/**
* Represents a bidder for the action.
*/
public interface Bidder {
/**
* Initializes the bidder with the production quantity and the allowed cash limit.
*
* @param quantity
* the quantity
* @param cash
* the cash limit
*/
void init(int quantity, int cash);
/**
* Retrieves the next bid for the product, which may be zero.
*
* @return the next bid
*/
int placeBid();
/**
* Shows the bids of the two bidders.
*
* @param own
* the bid of this bidder
* @param other
* the bid of the other bidder
*/
void bids(int own, int other);
}
EVALUATION CRITERIA |
1. Code Style (structure, design, quality, documentation, tests)
2. Strategy (logic, creativity, ranking)
For Non-IT positions (Trading) we mostly evaluate the logic. High programming skills are not necessary.
Feel free to provide all material (tests, documentation etc.) you have worked out which helps us evaluate
your programming skills and your approach.
