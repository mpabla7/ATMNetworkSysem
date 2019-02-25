
public class Customer {

    private String name;            //bob
    private String password;        //bob123
    private String cardNumber;       //'A 11' or 'B 111'
    private checkingAccount account; //which bank is associated with account? It depends on first letter of card number


    Customer(String name, String password, String cardNumber){
        this.name=name;
        this.password=password;
        this.cardNumber=cardNumber;
        this.account = new checkingAccount(password, cardNumber);       //create a new checking account for customer
    }


    //customer may open 0 or 1 account with Bank A or Bank B

    //each customer has a cashcard

    //cashcard may be returned back to customer

}
