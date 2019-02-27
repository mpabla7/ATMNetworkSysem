import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class CashCard {

    //A cash card comes with a card number from which bank_id and the associated account number can be identified.
    //A cash card also comes with its expiration date.

    private String cardNumber;           //'A 11' .....bank_id and associated account number can be identified
    private LocalDate expirationDate;   //when does this card expire

    public CashCard(String cardNumber){
        this.cardNumber=cardNumber;

        //create a random date and set it to the expirationDate
        long minDay = LocalDate.of(2018, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2022, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

        this.expirationDate=randomDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
