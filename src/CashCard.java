/**
 * @author Mandeep Pabla
 * @version 1.0 2/27/2019
 */

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The CashCard Class is represents a cash card. A cash card comes with a card number from with the bank_id and the associated
 * account number can be identified. A cash card also comes with its expiration date.
 */
public class CashCard {

    private String cardNumber;           //bank_id and associated account number can be identified. For example: 'A 11'
    private LocalDate expirationDate;   //expiration date of card

    /**
     * The constructor sets the cardNumber and the expiration date associated to that card.
     * The constructor creates a random date and sets it to the expiration date.
     * @param cardNumber
     */
    public CashCard(String cardNumber){
        this.cardNumber=cardNumber;

        //create a random date and set it to the expirationDate
        long start = LocalDate.of(2018, 1, 1).toEpochDay();
        long end = LocalDate.of(2022, 12, 31).toEpochDay();
        long pickedDay = ThreadLocalRandom.current().nextLong(start, end);
        LocalDate rando = LocalDate.ofEpochDay(pickedDay);

        this.expirationDate=rando;
    }

    /**
     * accessor returns expiration date of card
     * @return LocalDate
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
