import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Bank {

    private HashMap<String, checkingAccount> customers = new HashMap<>();    //KEY = card number, VALUE = checkingAccount obj
    private String name; //name 'A' or 'B'

    Bank(String bank_name){
        this.name=bank_name;
    }

    public void setCustomers(String key, checkingAccount value){
        customers.put(key,value);
    }

    public void StateOfBank(){
        for(String key: customers.keySet()){
            checkingAccount acc = customers.get(key);
            System.out.println("bankid: " + acc.getBank_id() + ", account number: " + acc.getAccountNumber() +", expires on "
            +acc.getExpDate() +", password: " + acc.getPassword() +", balance: " + acc.getBalance());
        }
    }

    //A card is valid if it is not expired and its bank id is correct for the bank associated with the ATM.
    public boolean checkCardValidation(String cardNumber){  //'A 11' or 'B 111'

        if(customers.containsKey(cardNumber)){              //it already knows if it is bank A or bank B

            ArrayList<checkingAccount> info = new ArrayList<>();
            info.addAll(customers.values());

            for(int i =0; i < info.size(); i++){            //it already knows if it is bank A or bank B

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

    //returns false if password is invalid
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

    //After the bank gets a withdraw request from the ATM, the bank checks if the corresponding bank account has enough money for the transaction.
    //If the amount exceeds the limit, the transaction will fail and the bank will send an error message to the ATM.
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

    //the transaction is logged against the card number at the bank
    public void transactionLog(String cardNumber, int amount){

        ArrayList<checkingAccount> info = new ArrayList<>();
        info.addAll(customers.values());

        for(int i =0; i < info.size(); i++){

            if(info.get(i).getCardNumber().equals(cardNumber)){
                info.get(i).addNode(amount);
            }
        }
    }

    public String getName() {
        return name;
    }
}
