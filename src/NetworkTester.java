import java.util.Scanner;

public class NetworkTester {

    public static void main(String [] arg){

        Bank A = new Bank("A");                         //Bank A
        ATM ATM1_A =new ATM("ATM1_A");                  //Bank A has two ATMs
        ATM ATM2_A = new ATM("ATM2_A");

        Customer A_cust1 =new Customer("Bob", "Bob123", "A 11");       //Bank A has two customers
        Customer A_cust2 = new Customer("Joe", "Joe123", "A 12");

        Bank B = new Bank("B");                        //Bank B
        ATM ATM1_B =new ATM("ATM1_B");                  //Bank B has two ATMs
        ATM ATM2_B =new ATM("ATM2_B");

        Customer B_cust3 =new Customer("Mike", "Mike123", "B 111");       //Bank B has two customers
        Customer B_cust4 = new Customer("John", "John123", "B 122");


        ///////////////////////////////User interface/////////////////////////////////////////////////////////////////////////////////////////

        System.out.println();
        A.StateOfBank();

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter your choice of ATM");
        String userATM = input.nextLine();

        if(userATM.equals(ATM1_A.getATM_name()) || userATM.equals(ATM2_A.getATM_name())){

            System.out.println("Enter your card (Bank A associated ATM)"); //example: 'A 11' or 'A 12'
            String card = input.nextLine();
            ATM1_A.cardValidation(card, A);

        }else if(userATM.equals(ATM1_B.getATM_name()) || userATM.equals(ATM2_B.getATM_name())){
            System.out.println("Enter your card (Bank B associated ATM)");
            String card= input.nextLine();
            ATM1_B.cardValidation(card,B);

        }else {
            //this is not a valid ATM name
            System.out.println("Error: not a valid ATM name");
        }


    }
}
