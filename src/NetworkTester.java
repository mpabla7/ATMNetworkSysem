import java.util.HashMap;
import java.util.Scanner;

public class NetworkTester {

    public static void main(String [] arg){

        HashMap<String, ATM> atms = new HashMap<>();

        Bank A = new Bank("A");                         //Bank A
      //  ATM ATM1_A =new ATM("ATM1_A",A);                  //Bank A has two ATMs
      //  ATM ATM2_A = new ATM("ATM2_A",A);

        atms.put("ATM1_A", new ATM("ATM1_A",A));
        atms.put("ATM2_A", new ATM("ATM2_A",A));

        //String name, String password, String cardNumber, Bank obj

//        Customer A_cust1 =new Customer("Bob", "Bob123", "A 11");       //Bank A has two customers
//        Customer A_cust2 = new Customer("Joe", "Joe123", "A 12");

        checkingAccount A_cust1 = new checkingAccount("Bob", "Bob123", "A 11", A);
        checkingAccount A_cust2 = new checkingAccount("Joe", "Joe123", "A 12", A);


        Bank B = new Bank("B");                        //Bank B
     //   ATM ATM1_B =new ATM("ATM1_B",B);                  //Bank B has two ATMs
      //  ATM ATM2_B =new ATM("ATM2_B",B);

        atms.put("ATM1_B", new ATM("ATM1_B",B));
        atms.put("ATM2_B", new ATM("ATM2_B",B));

//        Customer B_cust3 =new Customer("Mike", "Mike123", "B 111");       //Bank B has two customers
//        Customer B_cust4 = new Customer("John", "John123", "B 122");

        checkingAccount B_cust3 = new checkingAccount("Mike", "Mike123", "B 111", B);
        checkingAccount B_cust4 = new checkingAccount("John", "John123", "B 122", B);


        ///////////////////////////////User interface/////////////////////////////////////////////////////////////////////////////////////////

        System.out.println();
        A.StateOfBank();

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter your choice of ATM");
        String userATM = input.nextLine();

        if(atms.containsKey(userATM)){

            ATM atm = atms.get(userATM);
            System.out.println("Enter your card " + atm.getMyBank().getName()); //example: 'A 11' or 'A 12'
            String card = input.nextLine();
            atm.cardValidation(card);

        }else{
            System.out.println("Error: not a valid ATM name");
        }

    }
}
