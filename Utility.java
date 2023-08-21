import java.util.Scanner;

public class Utility {
    public String getInLoc(){
        System.out.println("First of all, provide me the input file location");
        Scanner sc = new Scanner(System.in);
        String inLoc = sc.nextLine();
        return inLoc;
    }

    public String getOutLoc(){
        System.out.println("If you want to save the data to other file then provide me the output file location");
        Scanner sc = new Scanner(System.in);
        String outLoc = sc.nextLine();
        return outLoc;
    }

    public String getOpType(){
        System.out.println("You can perform following operations to the CSV file.");
        System.out.println("1. Add data to it.");
        System.out.println("2. Edit the data.");
        System.out.println("3. Delete the data.");
        System.out.println("4. Sort the data based on country name & save the data to other file.");
        System.out.println("5. Convert the temperature to Fahrenheit & save the data to other file.");
        System.out.print("Enter your choice : ");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        return choice;
    }
}
