import java.time.LocalDate;
import java.util.Scanner;

public class ATM {

    private final int MAX_AMOUNT = 1000; //ATM maximum withdraw of $1000
    private String ATM_name;
    private String cardNumber;

    ATM(String name){
        this.ATM_name=name;
    }

    //ATM will read the object's cardNumber to check validation
    public void cardValidation(String cardNumber, Bank obj){

        this.cardNumber=cardNumber;

            if (obj.getName().equals(cardNumber.substring(0, 1))) {    //check if bank_ID is correct for the ATM associated with bank

                if (obj.checkCardValidation(cardNumber)) {    //'A 11' or 'B 111'
                    authDialog(cardNumber,obj);
                }
            } else {
                System.out.println("This card is not supported by this ATM\n");
            }
        }


    public void authDialog(String cardNumber, Bank obj){

        //user input
        Scanner scanner = new Scanner(System.in);

        //ask customer to enter password
        System.out.println("Enter your password");
        String password = scanner.nextLine();

        //password verified with BANK
        if(obj.isPasswordValid(cardNumber, password)){  //Bank will return accept or reject from

            System.out.println("Congrats! Password is verified");
            //if accepted then call transactionDialog() method
            transactionDialog(cardNumber, obj);

        }else{
            System.out.println("Error wrong password, card is returned");
            authDialog(cardNumber,obj);     //let customer re-enter his/her password
        }
    }

    public void transactionDialog(String cardNumber, Bank obj){

        Scanner in = new Scanner(System.in);    //user input

        //let customer enter amount
        System.out.println("Please enter an amount to withdraw");

        int amount =  in.nextInt();

        //check if amount > MAX_AMOUNT, if so display error
        if(amount>MAX_AMOUNT){
            System.out.println("amount exceeds MAX_AMOUNT, please re-do transaction");
            transactionDialog(cardNumber,obj);
        }

        //else, start transaction by sending request to bank

            //logic is conducted in Bank.java file

        //Bank sends back success message

        //money dispensed from ATM

    }

    public String getATM_name() {
        return ATM_name;
    }
}
