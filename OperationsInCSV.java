import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
// import Utility;

class Operations {
    public void features(BufferedReader file, BufferedWriter writer, String choice) throws IOException{
        SortedMap<String, Float> sortedMap = new TreeMap<String, Float>();
        Map<String, Float> convertedMap = new LinkedHashMap<String, Float>();
        String line;

        while((line = file.readLine()) != null){
            String[] fields = line.split(",");

            String countryName = fields[0];
            float temperature = Float.parseFloat(fields[1]);

            if(choice.equals("sort")){
                sortedMap.put(countryName, temperature);
            }
            else if(choice.equals("convert")){
                Float clTemp = (Float) temperature;
                Float fhTemp = (clTemp * 9/5) + 32;
                convertedMap.put(countryName, fhTemp);
            }
            else{
                System.err.println("Incorrect choice!!");
            }
            
        }

        // When operation is sorting
        if(choice.equals("sort")){
            writer.write("Country Name");
            writer.write("      Temperature in Degrees(C)");
            writer.newLine();
            for (String key : sortedMap.keySet()) {
                writer.write(key);
                writer.write("                  ");
                writer.write(Float.toString(sortedMap.get(key)));
                writer.newLine();
                
            }
            
        }

        // When operation is convertion
        else if(choice.equals("convert")){
            writer.write("Country Name");
            writer.write("      Temperature in Degrees(F)");
            writer.newLine();
            for (String key : convertedMap.keySet()) {
                writer.write(key);
                writer.write("                  ");
                writer.write(Float.toString(convertedMap.get(key)));
                writer.newLine();
                
            }
            
        }
    }

}


public class OperationsInCSV {
    public static void main(String[] args) {
        try {
            System.out.println("Do you want to take a lead and configure input/outout path or go with the default value? (Y/N)");
            Scanner scn = new Scanner(System.in);
            String conf = scn.nextLine();
            String inputFile = "";
            String outputFile = "";
            
            while (!conf.equalsIgnoreCase("y") && !conf.equalsIgnoreCase("n")) {
                System.out.println("You have to answer with Y/N. Please try again!");
                conf = scn.nextLine();
            }
            
            Utility util = new Utility();
            if(conf.equalsIgnoreCase("y")){
                inputFile = util.getInLoc();
                outputFile = util.getOutLoc();
            }
            else if(conf.equalsIgnoreCase("n")){
                inputFile = "asg1_sample.csv";
                outputFile = "output.txt";
                System.out.println("\nInput file is set to \"" + inputFile +"\" All the data will taken from this csv file. Make sure you have this file in your present directory. :)");
                System.out.println("\nOutput file is set to \"" + outputFile + "\" All the output will be stored in this file.");
            }

            while(true){
                int choice = util.getOpType();
                switch (choice) {
                    // Adding the data
                    case 1:
                        System.out.println("For adding the data to the csv file, you have to give the Country name & Temperature (C) respectevely");
                        Scanner addData = new Scanner(System.in);
                        String country = "";
                        float temperature = 0;
                        boolean tempr = false;
                        while(country.trim().isEmpty()){
                            country = addData.nextLine().trim();
                            if(country.isEmpty()){
                                System.out.println("Country name can not be empty! Give me a valid country name.");
                            }
                        }
                        while(!tempr){
                            temperature = addData.nextFloat();
                            System.out.println("Temperature can not be empty!");
                            tempr = true;
                        }

                        BufferedWriter addBuff = new BufferedWriter(new FileWriter(inputFile, true));
                        addBuff.write(country + "," + temperature);
                        addBuff.newLine();
                        addBuff.flush();
                        System.out.println("Successfully added the data.");
                        break;
                
                    // Editting the data
                    case 2:
                        System.out.print("Now, choose your option to modify country name, temperature, or both? (c/t/both) ");
                        Scanner scanner = new Scanner(System.in);
                        String ch = "";
                        while (!ch.equalsIgnoreCase("c") && !ch.equalsIgnoreCase("t") && !ch.equalsIgnoreCase("both")) {
                            ch = scanner.nextLine().trim();
                            if (!ch.equalsIgnoreCase("c") && !ch.equalsIgnoreCase("t") && !ch.equalsIgnoreCase("both")) {
                                System.out.println("Choose the correct option. Please try again!");
                            }
                        }
                        System.out.println("Enter the country name to modify: ");
                        String countryToModify = "";
                        while(countryToModify.trim().isEmpty()){
                            countryToModify = scanner.nextLine().trim();
                            if(countryToModify.isEmpty()){
                                System.out.println("Country name can not be empty! Give me a valid country name.");
                            }
                        }

                        String newCountryName = "";
                        float newTemperature = 0;
                        boolean isTempr = false;

                        if(ch.equalsIgnoreCase("c")){
                            System.out.println("Enter the new country name: ");
                            while(newCountryName.trim().isEmpty()){
                                newCountryName = scanner.nextLine().trim();
                                if(newCountryName.isEmpty()){
                                    System.out.println("Country name can not be empty! Give me a valid country name.");
                                }
                            }
                        }
                        else if(ch.equalsIgnoreCase("t")){
                            System.out.println("Enter the new temperature in Celsius: ");
                            while(!isTempr){
                                newTemperature = scanner.nextInt();
                                System.out.println("Temperature can not be empty!");
                                isTempr = true;
                            }

                        }
                        else if(ch.equalsIgnoreCase("both")){
                            System.out.println("Enter the new country name: ");
                            while(newCountryName.trim().isEmpty()){
                                newCountryName = scanner.nextLine().trim();
                                if(newCountryName.isEmpty()){
                                    System.out.println("Country name can not be empty! Give me a valid country name.");
                                }
                            }

                            System.out.println("Enter the new temperature in Celsius: ");
                            while(!isTempr){
                                newTemperature = scanner.nextInt();
                                System.out.println("Temperature can not be empty!");
                                isTempr = true;
                            }

                        }

                        try {
                            BufferedReader readerBuff = new BufferedReader(new FileReader(inputFile));
                            String line;
                            List<String> lines = new ArrayList<>();
                            while((line = readerBuff.readLine()) != null){
                                lines.add(line);
                                
                            }
                            readerBuff.close();

                            boolean found = false;      // Make sure you got the correct file name

                            // Modify the specified line
                            for (int i = 1; i < lines.size(); i++) { // Skip the header
                                String[] fields = lines.get(i).split(",");
                                if (fields[0].equalsIgnoreCase(countryToModify)){
                                    if (!newCountryName.isEmpty()) {
                                        fields[0] = newCountryName;
                                    }
                                    if (newTemperature != 0) {
                                        fields[1] = Float.toString(newTemperature);
                                    }

                                    lines.set(i, String.join(",", fields));
                                    found = true;
                                    break;
                                }
                                
                            }

                            if(!found){
                                System.out.println("Doesn't match with any country name!!");
                            }
                            else{
                                // Write the modified data back to the CSV file
                                BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
                                for (String newLine : lines) {
                                    writer.write(newLine);
                                    writer.newLine();
                                }
                                writer.close();

                                System.out.println("Data modified in the CSV file.");
                            }
                        }
                        catch (IOException e) {
                            System.out.println("An error occurred: " + e.getMessage());
                        }
                        break;

                    // Deletion of data
                    case 3:
                        Scanner scanner2 = new Scanner(System.in);
                        System.out.println("Enter the country name to delete: ");
                        String countryToDelete = "";
                        while(countryToDelete.trim().isEmpty()){
                            countryToDelete = scanner2.nextLine().trim();
                            if(countryToDelete.isEmpty()){
                                System.out.println("Country name can not be empty! Give me a valid country name.");
                            }
                        }

                        try {
                            BufferedReader deleteBuff = new BufferedReader(new FileReader(inputFile));
                            String lineNo;
                            List<String> data = new ArrayList<>();
                            while ((lineNo = deleteBuff.readLine()) != null) {
                                data.add(lineNo);
                            }
                            deleteBuff.close();
                
                            boolean found = false;
                
                            // Modify the specified line
                            for(int i = 1; i < data.size(); i++){ // Skip the header
                                String[] parts = data.get(i).split(",");
                                if (parts[0].equalsIgnoreCase(countryToDelete)) {
                                    data.remove(i);
                                    found = true;
                                    break;
                                }
                            }
                
                            if(!found){
                                System.out.println("Country not found in the CSV.");
                            } 
                            else{
                                // Write the modified data back to the CSV file
                                BufferedWriter updater = new BufferedWriter(new FileWriter(inputFile));
                                for (String newLine : data) {
                                    updater.write(newLine);
                                    updater.newLine();
                                }
                                updater.close();
                
                                System.out.println("Data deleted from the CSV file.");
                            }
                        } 
                        catch(IOException e){
                            System.out.println("An error occurred: " + e.getMessage());
                        }
                        break;

                    // Sort the data
                    case 4:
                        BufferedReader csvFileForSort = new BufferedReader(new FileReader(inputFile));
                        BufferedWriter bws = new BufferedWriter(new FileWriter(outputFile));
                        csvFileForSort.readLine();     // Skip the header
                        Operations sorting = new Operations();
                        sorting.features(csvFileForSort, bws, "sort");
                        csvFileForSort.close();
                        bws.flush();
                        System.out.println("Sorted the csv file based on country name & stored in " + outputFile + " file..");
                        break;

                    // Convertion of temperature
                    case 5:
                        BufferedReader csvFileForConvert = new BufferedReader(new FileReader(inputFile));
                        BufferedWriter bwc = new BufferedWriter(new FileWriter(outputFile));
                        csvFileForConvert.readLine(); // Skip the header
                        Operations convert = new Operations();
                        convert.features(csvFileForConvert, bwc, "convert");
                        csvFileForConvert.close();
                        bwc.flush();
                        System.out.println("Converted the temperature & stored in " + outputFile + " file..");
                        break;

                    case 6:
                        System.out.println("Exiting from the program. Thank you!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Please choose a right option.");
                        break;
                }   

                if(choice == 1 || choice == 2 || choice == 3){
                    System.out.println("\nAfter modifying, the updated input file has following contents.");
                    BufferedReader inpReader = new BufferedReader(new FileReader(inputFile));
                    String line;
                    
                    while((line = inpReader.readLine()) != null){
                        System.out.println(line);
                        
                    }
                    inpReader.close();
                }
                else if (choice == 4 || choice == 5){
                    System.out.println("\nThe output file " + outputFile + " has the following content inside it.");
                    BufferedReader outReader = new BufferedReader(new FileReader(outputFile));
                    String line;
                    
                    while((line = outReader.readLine()) != null){
                        System.out.println(line);
                        
                    }
                    outReader.close();
                }
            }
        }
        catch(Exception e){
            System.out.println("There is an error : " + e.getMessage());
        }

    }
}
