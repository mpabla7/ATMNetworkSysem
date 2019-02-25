import java.util.HashMap;

public class Bank {


    private String bank_id;                      //Can be 'A' or 'B' depending on Bank
    private String accountNumber;                //Can be '11' or '111'

    private String cardNumber;                   //Can be 'A 11' or 'B 111'

    private String name; //temp name

    Bank(String bank_name){
        this.name=bank_name;


    }

    public boolean checkCardValidation(String cardNumber){  //'A' 11


        return false;
    }

}
