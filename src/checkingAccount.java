/**
 * @author Mandeep Pabla
 * @version 1.0 2/27/2019
 */

import java.time.LocalDate;
import java.util.LinkedList;

/**
 *  CheckingAccount is responsible for creating a customer's account. This class holds the card number, balance, password, cash card, and name of account holder.
 *  This class contains a LinkedList that keeps track of the checking accounts withdraw history. The class contains a withdraw methods for withdrawing funds.
 *
 */
public class checkingAccount {

    private int balance=250;            //  $250
    private String cardNumber;          //  Such as: 'A 11' or 'B 111'
    private String password;            //  Such as: 'Bob123'

    private String bank_id;             //Such as: 'A' or 'B'
    private String accountNumber;       //Such as: '11' or '111'

    private CashCard cashCard;          //CashCard object should know cardNumber and expDate
    private LocalDate expDate;          //expDate of CashCard should be assigned in CashCard
    private String name;                //name of customer

    private LinkedList<Integer> log = new LinkedList<>();   //holds transaction history

    /**
     * Constructs new cash card.
     * Assigns name, password, card number, and corresponding bank object.
     * Passes the card number an this object into 'setCustomers' method in bank class.
     *
     * @param name
     * @param password
     * @param cardNumber
     * @param obj
     */
    public checkingAccount(String name, String password, String cardNumber, Bank obj){      //since checking account temporalry uses Bank object checkingaccount depends on bank

        this.password=password;
        this.cardNumber=cardNumber;

        this.bank_id=String.valueOf(cardNumber.charAt(0));                  //stores first index of string and will indicate what bank the account belongs to. (bank_id)
        this.accountNumber=cardNumber.substring(cardNumber.indexOf(' '));   //stores the numbers after the ' ' in cardNumber, (accountNumber variable)
        this.name=name;
        cashCard=new CashCard(cardNumber);

        obj.setCustomers(cardNumber,this);
    }

    /**
     * Checks if customer has sufficient funds before withdraw.
     *
     * @param amount - amount customer wants to withdraw
     * @return true/false if sufficient funds in account
     */
    public boolean sufficient_money(int amount){
        if(balance-amount < 0){
            return false;
        }
        return true;
    }

    /**
     * Withdraw amount from existing balance
     * @param amount
     */
    public void withdraw(int amount){
        balance=balance-amount;
    }

    /**
     * Adds a node every time the customer withdraws an amount to keep track of all transaction on account
     * @param amount
     */
    public void addNode(int amount){
        this.log.add(amount);
    }

    /**
     * Returns transaction history of account
      * @return
     */
    public LinkedList<Integer> getLog(){
        return log;
    }

    /**
     * returns current balance in account
     * @return
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Returns card's expiration date
     * @return
     */
    public LocalDate getExpDate(){
        expDate = cashCard.getExpirationDate();
        return expDate;
    }

    /**
     * Returns bank's id such as:'A' or 'B'
     * @return
     */
    public String getBank_id(){
        return bank_id;
    }

    /**
     * Returns account number such as: '11' or '111'
     * @return
     */
    public String getAccountNumber(){
        return accountNumber;
    }

    /**
     * Returns card number such as: 'A 11' or 'B 111'
     * @return
     */
    public String getCardNumber(){
        return cardNumber;
    }

    /**
     * Returns password for account
     * @return
     */
    public String getPassword(){
        return password;
    }

}
