/**
 * @author Mandeep Pabla
 * @version 1.0 2/27/2019
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Bank class contains an Hash map that keeps track of all customers and their checking accounts.
 * This class is responsible for showing the state of the ATM and check validation of password/correct card.
 * This class is also responsible for allowing the customer to withdraw funds and updates the transaction log every time the customer
 * makes a withdraw.
 */
public class Bank {

    private HashMap<String, checkingAccount> customers = new HashMap<>();    //KEY = card number, VALUE = checkingAccount obj
    private String name;                                                    //name 'A' or 'B'

    /**
     * Sets the bank name
     * @param bank_name
     */
    Bank(String bank_name){
        this.name=bank_name;
    }

    /**
     * method populates hash map so that the hash map maintains a record of all card numbers that are mapped to their
     * corresponding checking account
     *
     * @param key   - card number
     * @param value - checking account reference
     */
    public void setCustomers(String key, checkingAccount value){
        customers.put(key,value);
    }

    /**
     * Displays the info of every customer associated with this bank
     */
    public void StateOfBank(){
        for(String key: customers.keySet()){
            checkingAccount acc = customers.get(key);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String format = acc.getExpDate().format(formatter);

            System.out.println("bankid: " + acc.getBank_id() + ", account number: " + acc.getAccountNumber() +", expires on "
            +format +", password: " + acc.getPassword() +", balance: " + acc.getBalance());
        }
    }

    /**
     * A card is valid if it is not expired and its bank id is correct for the bank associated with the ATM.
     * If card is valid then return true
     *
     * @param cardNumber
     * @return
     */
    public boolean checkCardValidation(String cardNumber){
        if(customers.containsKey(cardNumber)){

            ArrayList<checkingAccount> info = new ArrayList<>();
            info.addAll(customers.values());

            for(int i =0; i < info.size(); i++){

                if(cardNumber.equals(info.get(i).getCardNumber())) {

                    LocalDate checkDate = info.get(i).getExpDate();
                    if(checkDate.isBefore(LocalDate.now())){
                        //This card is expired and returned to you.
                        return false;
                    }else{
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if password is correct. Returns false if password is invalid.
     * @param cardNumber
     * @param password
     * @return
     */
    public boolean isPasswordValid(String cardNumber, String password){
        if(customers.containsKey(cardNumber)){

            ArrayList<checkingAccount> info = new ArrayList<>();
            info.addAll(customers.values());

            for(int i =0; i < info.size(); i++){

                if(info.get(i).getCardNumber().equals(cardNumber)){         //same card number, now check password
                    if(info.get(i).getPassword().equals(password)){         //same password, return true
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * The bank gets a withdraw request from the ATM, the bank checks if the corresponding bank account has enough money for the transaction.
     * If the amount exceeds the limit, the transaction will fail and the bank will send an error message to the ATM.
     *
     * @param cardNumber
     * @param amount
     * @return
     */
    public boolean withdraw(String cardNumber, int amount){
        if(customers.containsKey(cardNumber)){

            ArrayList<checkingAccount> info = new ArrayList<>();
            info.addAll(customers.values());

            for(int i =0; i < info.size(); i++){

                if(info.get(i).getCardNumber().equals(cardNumber)){

                    if(info.get(i).sufficient_money(amount)){
                        info.get(i).withdraw(amount);
                        System.out.println("$" + amount+" is withdrawn from your account. The remaining balance of this account is $"+ info.get(i).getBalance());
                        return true;
                    }else{
                        System.out.println("Error: account does not have sufficient funds");
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * The transaction is logged against the card number at the bank
     * @param cardNumber
     * @param amount
     */
    public void transactionLog(String cardNumber, int amount){

        ArrayList<checkingAccount> info = new ArrayList<>();
        info.addAll(customers.values());

        for(int i =0; i < info.size(); i++){

            if(info.get(i).getCardNumber().equals(cardNumber)){
                info.get(i).addNode(amount);
            }
        }
    }

    /**
     * Returns the name of the bank
     * @return
     */
    public String getName() {
        return name;
    }
}
