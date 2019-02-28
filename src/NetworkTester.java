/**
 * @author Mandeep Pabla
 * @version 1.0 2/27/2019
 */

/**
 * @author Mandeep Pabla
 * @version 1.0 2/27/2019
 */

import java.util.HashMap;
import java.util.Scanner;

/**
 * NetworkTester contains a hash map that keeps track of all ATM objects. This class creates multiple customers and banks.
 * The state of the banks and ATMs is shown here and the user is prompted to enter an ATM of their choice from where
 * the UI continues on in the ATM class.
 */
public class NetworkTester {

    public static void main(String [] arg){

        HashMap<String, ATM> atms = new HashMap<>();

        Bank A = new Bank("A");                         //Bank A

        atms.put("ATM1_A", new ATM("ATM1_A",A));            //Bank A has two ATMs
        atms.put("ATM2_A", new ATM("ATM2_A",A));

        checkingAccount A_cust1 = new checkingAccount("Bob", "Bob123", "A 11", A);
        checkingAccount A_cust2 = new checkingAccount("Joe", "Joe123", "A 12", A);

        Bank B = new Bank("B");                         //Bank B

        atms.put("ATM1_B", new ATM("ATM1_B",B));            //Bank B has two ATMs
        atms.put("ATM2_B", new ATM("ATM2_B",B));

        //Bank B has three customers
        checkingAccount B_cust3 = new checkingAccount("Mike", "Mike123", "B 111", B);
        checkingAccount B_cust4 = new checkingAccount("John", "John123", "B 122", B);
        checkingAccount B_cust5 = new checkingAccount("Jose", "Jose123", "B 133", B);


        //User interface

        System.out.println("State of two banks:\n");
        System.out.println("Assume all accounts have $250 preloaded.  \n");
        A.StateOfBank();
        System.out.println();
        B.StateOfBank();

        System.out.println("\nStates of four ATMs:\n");

        for(int i = 0; i < atms.size(); i++){

            System.out.println(atms.keySet().toArray()[i]);
            atms.get(atms.keySet().toArray()[i]).ATMinfo();
        }

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
