
import java.util.Scanner;

public class ATM {

    private final int MAX_AMOUNT = 1000; //ATM maximum withdraw of $1000
    private String ATM_name;
    private Bank myBank;

    ATM(String name, Bank myBank){
        this.ATM_name=name;
        this.myBank=myBank;
    }

    //ATM will read the object's cardNumber to check validation
    public void cardValidation(String cardNumber){

        Scanner in = new Scanner(System.in);

            if (myBank.getName().equals(cardNumber.substring(0, 1))) {    //check if bank_ID is correct for the ATM associated with bank

                if (myBank.checkCardValidation(cardNumber)) {             //'A 11' or 'B 111'
                    authDialog(cardNumber);
                }else{
                    //the card is expired
                    System.out.println("This card is expired and returned to you.");
                    System.out.println("Enter your card");
                    String card = in.nextLine();
                    cardValidation(card);
                }
            } else {
                System.out.println("Error: this card is not supported by this ATM\n");
                System.out.println("Enter your card");
                String card = in.nextLine();
                cardValidation(card);
            }
        }


    public void authDialog(String cardNumber){

        Scanner scanner = new Scanner(System.in); //user input
        System.out.println("Enter your password");
        String password = scanner.nextLine();

        //password verified with BANK
        if(myBank.isPasswordValid(cardNumber, password)){  //Bank will return accept or reject from

            System.out.println("Congrats! Password is verified");
            //if accepted then call transactionDialog() method
            transactionDialog(cardNumber);

        }else{
            System.out.println("Error wrong password, card is returned");
            authDialog(cardNumber);     //let customer re-enter his/her password
        }
    }

    public void transactionDialog(String cardNumber){

        Scanner in = new Scanner(System.in);                            //user input
        System.out.println("Please enter an amount to withdraw or enter 0 to quit");
        int amount =  in.nextInt();
        if(amount==0) Quit();

        //check if amount > MAX_AMOUNT, if so display error
        while(amount>=MAX_AMOUNT){
            System.out.println("Error: this amount exceeds the maximum amount you can withdraw per transaction.\nPlease enter the amount or enter 0 to Quit.");
            amount =  in.nextInt();
            if(amount==0) Quit();
        }

        if(amount<MAX_AMOUNT && amount!=0){
            //start transaction by sending request to bank
            if(!myBank.withdraw(cardNumber,amount)){
                transactionDialog(cardNumber);
            }else{
                //transaction is successful, log the transaction against card number
                myBank.transactionLog(cardNumber,amount);
                System.out.println("If you have more transactions, enter the amount or enter 0 to Quit.");
                amount = in.nextInt();
                if(amount==0) Quit();
                else transactionDialog(cardNumber,amount);
            }
        }
    }
    //overloads transactionDialog
    public void transactionDialog(String cardNumber, int amount){

        Scanner in = new Scanner(System.in);

        //check if amount > MAX_AMOUNT, if so display error
        while(amount>=MAX_AMOUNT){
            System.out.println("Error: This amount exceeds the maximum amount you can withdraw per transaction.\nPlease enter the amount or enter 0 to Quit.");
            amount =  in.nextInt();
            if(amount==0) Quit();
        }

        if(amount<MAX_AMOUNT && amount!=0){
            //start transaction by sending request to bank
            if(!myBank.withdraw(cardNumber,amount)){
                transactionDialog(cardNumber);
            }else{
                //transaction is successful, log the transaction against card number
                myBank.transactionLog(cardNumber,amount);
                System.out.println("If you have more transactions, enter the amount or enter 0 to Quit.");
                amount = in.nextInt();
                if(amount==0) Quit();
                else transactionDialog(cardNumber,amount);
            }
        }
    }

    public void Quit(){
        System.out.println("Quiting...");
    }

    public Bank getMyBank() {
        return myBank;
    }
}
