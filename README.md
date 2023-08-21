# CSV File Manipulation Application

This branch has the solution of Assignment 1 of Java training. This application allows you to perform various operations on a CSV file containing country names and temperatures. You can add, edit, delete, sort, and convert temperature data using this application.

## Files Included

- `asg1_sample.csv`: Sample CSV file containing initial data (Country Name, Temperature in degrees).
- `ConvertedCSV.txt`: Output file containing converted temperature data in Fahrenheit.
- `Manifest.txt`: Manifest file required for creating the JAR file.
- `MyCSVApp.jar`: JAR file of the application that can be run directly.
- `OperationsInCSV.java`: Contains the main logic for CSV file operations.
- `SortedCSV.txt`: Output file containing sorted CSV data.
- `Utility.java`: Contains utility methods for input and choices.

## Getting Started

1. Make sure you have Java installed on your system.
2. Download or clone this repository to your local machine.
3. Open a terminal or command prompt and navigate to the directory where the files are located.

### Compiling and Running Manually

1. Navigate to the directory containing the `.java` files.
2. Compile the Java files using the following command: ``` javac Utility.java OperationsInCSV.java ```
3. Run the application using the following command: ``` java OperationsInCSV ```

### Using the JAR File

1. Open a terminal or command prompt.
2. Navigate to the directory containing the JAR file (`MyCSVApp.jar`).
3. Run the application using the following command: ``` java -jar MyCSVApp.jar ```

## Usage Instructions

1. The application will prompt you to provide the input file location (CSV file path).
2. It will then ask if you want to save data to another file. Provide the output file location (or press Enter to skip).
3. Choose an operation by entering the corresponding number:
- `1`: Add data to the CSV file.
- `2`: Edit existing data in the CSV file.
- `3`: Delete data from the CSV file.
- `4`: Sort the CSV data based on country name and save to a file.
- `5`: Convert temperature to Fahrenheit and save to a file.
4. Follow the on-screen instructions for each operation.

## Note

- The application uses the `Utility.java` class to get user input and choices.
- The application works with the `asg1_sample.csv` file provided. Make sure to keep it in the same directory.
- You can review and modify the source code in `OperationsInCSV.java` as needed.
