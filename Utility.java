import java.util.Scanner;

public class Utility {

    public String getInLoc() {
        String inLoc = "";
        while (inLoc.trim().isEmpty() || !inLoc.toLowerCase().endsWith(".csv")) {
            System.out.println("First of all, provide me the input file location (with .csv extension)");
            Scanner sc = new Scanner(System.in);
            inLoc = sc.nextLine().trim();
            if (inLoc.isEmpty()) {
                System.out.println("Please enter a valid input file location.");
            } else if (!inLoc.toLowerCase().endsWith(".csv")) {
                System.out.println("Please provide a file with .csv extension.");
            }
        }
        return inLoc;
    }

    public String getOutLoc() {
        String outLoc = "";
        while (outLoc.trim().isEmpty() || !outLoc.toLowerCase().endsWith(".txt")) {
            System.out.println("If you want to save the data to another file, provide me the output file location (with .txt extension)");
            Scanner sc = new Scanner(System.in);
            outLoc = sc.nextLine().trim();
            if (outLoc.isEmpty()) {
                System.out.println("Please enter a valid output file location.");
            } else if (!outLoc.toLowerCase().endsWith(".txt")) {
                System.out.println("Please provide a file with .txt extension.");
            }
        }
        return outLoc;
    }

    public int getOpType(){
        System.out.println("\n-------------------------------------------------------");
        System.out.println("You can perform following operations to the CSV file.");
        System.out.println("1. Add data to it.");
        System.out.println("2. Edit the data.");
        System.out.println("3. Delete the data.");
        System.out.println("4. Sort the data based on country name & save the data to output file.");
        System.out.println("5. Convert the temperature to Fahrenheit & save the data to output file.");
        System.out.println("6. Exit");
        System.out.println("-------------------------------------------------------");
        System.out.print("\nEnter your choice : ");
        Scanner sc = new Scanner(System.in);
        int ch = sc.nextInt();
        return ch;
    }
}
