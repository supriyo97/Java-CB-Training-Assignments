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
        SortedMap<String, Integer> sortedMap = new TreeMap<String, Integer>();
        Map<String, Double> convertedMap = new LinkedHashMap<String, Double>();
        String line;

        while((line = file.readLine()) != null){
            String[] fields = line.split(",");

            String countryName = fields[0];
            int temperature = Integer.parseInt(fields[1]);

            if(choice.equals("sort")){
                sortedMap.put(countryName, temperature);
            }
            else if(choice.equals("convert")){
                Double clTemp = (double) temperature;
                Double fhTemp = (clTemp * 9/5) + 32;
                convertedMap.put(countryName, fhTemp);
            }
            else{
                System.err.println("Incorrect choice!!");
            }
            
        }

        // Overwrite the data inside the output file
        writer.write("Country Name");
        writer.write("      Temperature in Degrees");
        writer.newLine();

        // When operation is sorting
        if(choice.equals("sort")){
            for (String key : sortedMap.keySet()) {
                writer.write(key);
                writer.write("                  ");
                writer.write(Integer.toString(sortedMap.get(key)));
                writer.newLine();
                
            }
            System.out.println("Sorted the csv file based on country name & stored in SortedCSV.txt file..");
        }

        // When operation is convertion
        else if(choice.equals("convert")){
            for (String key : convertedMap.keySet()) {
                writer.write(key);
                writer.write("                  ");
                writer.write(Double.toString(convertedMap.get(key)));
                writer.newLine();
                
            }
            System.out.println("Converted the temperature & stored in ConvertedCSV.txt file..");
        }
    }

}


public class OperationsInCSV {
    public static void main(String[] args) {
        try {
            Utility util = new Utility();
            String inputFile = util.getInLoc();
            String outputFile = util.getOutLoc();
            String choice = util.getOpType();

            switch (choice) {
                // Adding the data
                case "1":
                    System.out.println("For adding the data to the csv file, you have to give the Country name & Temperature respectevely");
                    Scanner addData = new Scanner(System.in);
                    String country = addData.nextLine();
                    String temperature = addData.nextLine();
                    addData.close();
                    BufferedWriter addBuff = new BufferedWriter(new FileWriter(inputFile, true));
                    addBuff.write(country + "," + temperature);
                    addBuff.newLine();
                    addBuff.flush();
                    System.out.println("Successfully added the data.");
                    break;
            
                // Editting the data
                case "2":
                    System.out.print("Now, choose your option to modify country name, temperature or both? (c/t/both) ");
                    Scanner scanner = new Scanner(System.in);
                    String ch = scanner.nextLine();
                    System.out.println("Enter the country name to modify: ");
                    String countryToModify = scanner.nextLine();
                    String newCountryName = "";
                    int newTemperature = 0;

                    if(ch.equals("c")){
                        System.out.println("Enter the new country name: ");
                        newCountryName = scanner.nextLine();

                    }
                    else if(ch.equals("t")){
                        System.out.println("Enter the new temperature in Celsius: ");
                        newTemperature = scanner.nextInt();

                    }
                    else if(ch.equals("both")){
                        System.out.println("Enter the new country name: ");
                        newCountryName = scanner.nextLine();

                        System.out.println("Enter the new temperature in Celsius: ");
                        newTemperature = scanner.nextInt();

                    }
                    else{
                        System.out.println("Choose the correct option");
                        break;
                    }
                    scanner.close();

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
                                    fields[1] = Integer.toString(newTemperature);
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
                case "3":
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.println("Enter the country name to delete: ");
                    String countryToDelete = scanner2.nextLine();
                    scanner2.close();

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
                case "4":
                    BufferedReader csvFileForSort = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter bws = new BufferedWriter(new FileWriter(outputFile));
                    csvFileForSort.readLine();     // Skip the header
                    Operations sorting = new Operations();
                    sorting.features(csvFileForSort, bws, "sort");
                    csvFileForSort.close();
                    bws.flush();
                    break;

                // Convertion of temperature
                case "5":
                    BufferedReader csvFileForConvert = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter bwc = new BufferedWriter(new FileWriter(outputFile));
                    csvFileForConvert.readLine(); // Skip the header
                    Operations convert = new Operations();
                    convert.features(csvFileForConvert, bwc, "convert");
                    csvFileForConvert.close();
                    bwc.flush();
                    break;

                default:
                    break;
            }

            if(choice.equals("1") || choice.equals("2") || choice.equals("3")){
                System.out.println("\nAfter modifying, the updated input file has following contents.");
                BufferedReader inpReader = new BufferedReader(new FileReader(inputFile));
                String line;
                
                while((line = inpReader.readLine()) != null){
                    System.out.println(line);
                    
                }
                inpReader.close();
            }
            else if (choice.equals("4") || choice.equals("5")){
                System.out.println("\nWe got new file " + outputFile + " file. Here is the content inside it.");
                BufferedReader outReader = new BufferedReader(new FileReader(outputFile));
                String line;
                
                while((line = outReader.readLine()) != null){
                    System.out.println(line);
                    
                }
                outReader.close();
            }

        }
        catch(Exception e){
            System.out.println("There is an error : " + e.getMessage());
        }

    }
}