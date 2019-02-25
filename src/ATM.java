import java.time.LocalDate;

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

        obj.checkCardValidation(cardNumber);    //'A 11'

        //is bank id correct for the Bank associated with the ATM
        //bank id for WellsFargo != bank id for Bank of America

    }

    public void authDialog(){

        //ask customer to enter password

        //password verified with BANK

        //Bank will return accept or reject from

        //if accepted then call transactionDialog() method
    }

    public void transactionDialog(){

        //let customer enter amount

        //check if amount > MAX_AMOUNT, if so display error

        //else, start transaction by sending request to bank

            //logic is conducted in Bank.java file

        //Bank sends back success message

        //money dispensed from ATM

    }

    public String getATM_name() {
        return ATM_name;
    }
}
