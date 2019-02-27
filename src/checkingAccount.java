import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class checkingAccount {

    private int balance=250;            //  $2500
    private String cardNumber;          //  'A 11' or 'B 111'
    private String password;            //  Bob123

    private String bank_id;             //'A' or 'B'
    private String accountNumber;       // '11' or '111' in cardNumber variable

    private CashCard cashCard;          //CashCard object should know cardNumber and expDate
    private LocalDate expDate;          //expDate of CashCard should be assigned in CashCard
    private String name;

    private LinkedList<Integer> log = new LinkedList<>();

    public checkingAccount(String name, String password, String cardNumber, Bank obj){

        this.password=password;
        this.cardNumber=cardNumber;

        this.bank_id=String.valueOf(cardNumber.charAt(0));                  //stores first index of string and will indicate what bank the account belongs to.   (bank_id)
        this.accountNumber=cardNumber.substring(cardNumber.indexOf(' '));   //stores the numbers after the ' ' in cardNumber,    (accountNumber variable)
        this.name=name;
        cashCard=new CashCard(cardNumber);

        obj.setCustomers(cardNumber,this);

    }

    //checks if customer has sufficient funds before withdraw
    public boolean sufficient_money(int amount){
        if(balance-amount < 0){
            return false;
        }
        return true;
    }

    public void withdraw(int amount){
        balance=balance-amount;
    }

    public void addNode(int amount){
        this.log.add(amount);
    }

    /*
     *  accessors
     */

    public LinkedList<Integer> getLog(){
        return log;
    }

    //return balance in account
    public int getBalance() {
        return balance;
    }

    //return expiration date
    public LocalDate getExpDate(){
        expDate = cashCard.getExpirationDate();
        return expDate;
    }

    //will return 'A' or 'B'
    public String getBank_id(){
        return bank_id;
    }

    //will return '11' or '111'
    public String getAccountNumber(){
        return accountNumber;
    }

    //will return 'A 11' or 'B 111'
    public String getCardNumber(){
        return cardNumber;
    }

    //return password
    public String getPassword(){
        return password;
    }

}
