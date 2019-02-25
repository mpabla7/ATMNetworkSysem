import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Bank {

    private String bank_id;                      //Can be 'A' or 'B' depending on Bank
    private String accountNumber;                //Can be '11' or '111'

    private String cardNumber;                   //Can be 'A 11' or 'B 111'

    private HashMap<String, LocalDate> customers;    //will get customer info from checkingAccount, //KEY=cardNumber + " " +password, VALUE = expDate

    private String name; //name 'A' or 'B'

    Bank(String bank_name){
        this.name=bank_name;
    }

    public void StateOfBank(){

        ArrayList<checkingAccount> map = new ArrayList<>();
        map.addAll(checkingAccount.getCheckingAccuntMap().values());

        for(int i =0; i < map.size(); i++){
            System.out.println("bankid: " + map.get(i).getBank_id() + ", account number: "
            + map.get(i).getAccountNumber() + ", expires on "
                    + map.get(i).getExpDate()+", password: " + map.get(i).getPassword()
            + ", balance: " + map.get(i).getBalance());
        }
    }

    //A card is valid if it is not expired and its bank id is correct for the bank associated with the ATM.
    public boolean checkCardValidation(String cardNumber){  //'A 11' or 'B 111'

        customers = new HashMap<>();
        customers.putAll(checkingAccount.getCustomers());

        if(cardNumber.charAt(0)=='A'){

            ArrayList<String> info = new ArrayList<>();
            info.addAll(customers.keySet());

            for(int i =0; i < info.size(); i++){

                if(info.get(i).substring(0,1).equals("A")){     //confirm that the customers are from bank A
                    if(customers.containsKey(info.get(i))){
                        LocalDate check = customers.get(info.get(i));

                        if(check.isBefore(LocalDate.now())){
                          //  System.out.println("This card is expired and returned to you.");
                            return false;
                        }
                    }
                }
            }
        }else if(cardNumber.charAt(0)=='B'){

            ArrayList<String> info = new ArrayList<>();
            info.addAll(customers.keySet());

            for(int i =0; i < info.size(); i++){

                if(info.get(i).substring(0,1).equals("B")){     //confirm that the customers are from bank B
                    if(customers.containsKey(info.get(i))){
                        LocalDate check = customers.get(info.get(i));

                        if(check.isBefore(LocalDate.now())){
                           // System.out.println("This card is expired and returned to you.");
                            return false;
                        }
                    }
                }

            }
        }

        return true;
    }

    //returns false if password is invalid
    public boolean isPasswordValid(String cardNumber, String password){

        customers = new HashMap<>();
        customers.putAll(checkingAccount.getCustomers());

        String account = cardNumber;

        ArrayList<String> info = new ArrayList<>();
        info.addAll(customers.keySet());

        for(int i =0; i < info.size(); i++){
            if(info.get(i).charAt(0)=='A'){
                String acc = info.get(i).substring(0,4);

                String pass = info.get(i).substring(info.get(i).lastIndexOf(" ")+1);    //this will get password for index i of array list

                if(account.equals(acc) && pass.equals(password)){       //check if the specific account contains this password

                    //the account is the same, and the password matches
                    return true;
                }
            }else if(info.get(i).charAt(0)=='B'){

                String acc = info.get(i).substring(0,5);                                    //this will get card number
                String pass = info.get(i).substring(info.get(i).lastIndexOf(" ")+1);    //this will get password for index i of array list

                if(account.equals(acc) && pass.equals(password)){       //check if the specific account contains this password
                    //the account is the same, and the password matches
                    return true;
                }
            }
        }
        return false;
    }

    //After the bank gets a withdraw request from the ATM, the bank checks if the corresponding bank account has enough money for the transaction.
    //If the amount exceeds the limit, the transaction will fail and the bank will send an error message to the ATM.
    public void withdraw(String cardNumber, int amount){

        ArrayList<checkingAccount> map = new ArrayList<>();
        map.addAll(checkingAccount.getCheckingAccuntMap().values());

        for(int i =0; i < map.size(); i++){
            if(map.get(i).getCardNumber().equals(cardNumber)){

                if(map.get(i).sufficient_money(amount)) {   //if true, then account has sufficient funds

                    System.out.println("before " + map.get(i).getBalance());
                    map.get(i).withdraw(amount);
                    System.out.println("after " + map.get(i).getBalance());

                }else{
                    System.out.println("The account does not have sufficient funds");
                }
            }
        }
        //DEBUGGER LOOP
        for(int i = 0; i < map.size(); i++){
            System.out.println("DEBUGGER LOOP: " + map.get(i).getCardNumber() + " " + map.get(i).getBalance());
        }
    }

    public void transactionLog(){

    }

    public String getName() {
        return name;
    }
}
