/**
 * @author Mandeep Pabla
 * @version 1.0 2/27/2019
 */

import java.util.Scanner;

/**
 * Each ATM is associated with corresponding bank. ATM class is responsible for the card validation and user authentication UI.
 * The card/password info is passed along to the corresponding bank the ATM is linked to so it can check if the info is correct.
 */
public class ATM {

    private final int MAX_AMOUNT = 1000; //ATM maximum withdraw of $1000
    private String ATM_name;             //Holds ATMs name
    private Bank myBank;                 //Holds which bank the ATM is linked to

    /**
     * Assigns name of ATM and the Bank the ATM is linked to.
     *
     * @param name
     * @param myBank
     */
    ATM(String name, Bank myBank){
        this.ATM_name=name;
        this.myBank=myBank;
    }

    /**
     * ATM will read the object's cardNumber to check validation. The card number is passed into the 'checkCardValidation' method in the Bank class.
     * An error message is displayed if the card is expired or not if the card is not supported by the particiular ATM.
     * @param cardNumber
     */
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

    /**
     * If the card is valid then authDialog method is called. This method asks user for password and invokes the 'isPasswordValid' in Bank.java.
     * The 'isPasswordValid' will return true if the password is correct.
     *
     * @param cardNumber
     */
    public void authDialog(String cardNumber){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your password");
        String password = scanner.nextLine();

        //password verified with BANK
        if(myBank.isPasswordValid(cardNumber, password)){  //Bank will return accept or reject

            System.out.println("Congrats! Password is verified");
            //if accepted then call transactionDialog() method
            transactionDialog(cardNumber);

        }else{
            System.out.println("Error wrong password, card is returned");
            authDialog(cardNumber);     //let customer re-enter his/her password
        }
    }

    /**
     * The transactionDialog is called if the password is valid and the user is asked to withdraw an amount from their account. If the amount
     * exceeds the max of the ATM or if the amount exceeds the amount of funds in user's checking account then an error is displayed. The user can enter in
     * 0 if they want to exit the program.
     *
     * @param cardNumber
     */
    public void transactionDialog(String cardNumber){

        Scanner in = new Scanner(System.in);
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

    /**
     * This method overloads the transactionDialog method above and is only activated if the user enters in an amount that exceeds current funds
     *
     * @param cardNumber
     * @param amount
     */
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

    /**
     * Method is activated when the user wants to quit the program
     */
    public void Quit(){
        System.out.println("Quiting...");
    }

    /**
     * Displays the maximum amount the user can withdraw from particular ATM
     */
    public void ATMinfo(){
        System.out.println("The maximum amount of cash this ATM can withdraw per transaction: $" + MAX_AMOUNT+ ". Associated with Bank " + myBank.getName());
    }
}
