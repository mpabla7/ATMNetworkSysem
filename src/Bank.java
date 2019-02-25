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
                            System.out.println("This card is expired and returned to you.");
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
                            System.out.println("This card is expired and returned to you.");
                            return false;
                        }
                    }
                }

            }
        }

        return true;
    }

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
                if(account.equals(acc)){
                    //the account is the same, and the password matches
                    return true;
                }
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }
}
