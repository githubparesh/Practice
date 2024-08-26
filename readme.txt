-----------------------------------Data Validation Framework------------------------------
Overview
This project includes a data validation framework for verifying API responses against expected values from a CSV file. The framework is designed to ensure that the data returned by the API matches the data specified in a CSV file. The framework consists of:

Java Code: Utilizes JUnit and RestAssured for API data validation.
Bash Script: Validates API responses against CSV data using curl and jq.
CSV File: Contains test data used for validation.

-------Prerequisites:
Java: Version 11 or higher
Maven: For managing Java project dependencies
Curl: Command-line tool for fetching data from APIs
jq: Command-line tool for processing JSON
Bash: Available by default on Linux and macOS; can be installed on Windows via WSL (Windows Subsystem for Linux)

-------Installation Instructions:

Install Java:
macOS: brew install openjdk@11

Install Maven:
macOS: brew install maven

Install Curl:
macOS: brew install curl

Install jq:
macOS: brew install jq

---------Bash Script for Test Cases
1)The Bash script fetches data from an API, compares it with expected values from a CSV file, and outputs the validation results
BashScript location=/Users/pareshviradiya/Documents/gitlab/Practice/src/test/resources/Scripts/validate_data.sh

--------How to Run the Script

1) Go to the /Users/pareshviradiya/Documents/gitlab/Practice/src/test/resources/Scripts
2) execute ./validate_data.sh


______________________Continuous Integration / Continuous Deployment (CI/CD) Solution
To integrate the validation framework into a CI/CD pipeline, you can use GitHub Actions or any other CI/CD tool. Here’s an example GitHub Actions workflow configuration.

GitHub Actions Workflow (.github/workflows/ci.yml)
yaml
name: Data Validation Pipeline

on: [push]

jobs:
  validate-data:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout code
        uses: actions/checkout@v2

      - name: Set up Java
        uses: actions/setup-java@v2
        with:
          java-version: '11'

      - name: Install Maven
        run: |
          sudo apt-get update
          sudo apt-get install -y maven

      - name: Install Curl and jq
        run: |
          sudo apt-get update
          sudo apt-get install -y curl jq

      - name: Install dependencies
        run: |
          mvn install

      - name: Run Java tests
        run: |
          mvn test

      - name: Run Bash script
        run: |
          chmod +x validate_data.sh
          ./validate_data.sh

 ------------Steps in the Workflow
 Checkout Code: Retrieves the code from the repository.
 Set Up Java: Configures the Java environment.
 Install Maven: Installs Maven for managing Java project dependencies.
 Install Curl and jq: Installs curl and jq required for the Bash script.
 Install Dependencies: Installs Java project dependencies.
 Run Java Tests: Executes Java tests.
 Run Bash Script: Executes the Bash script to validate API responses.

 Note: I have also included the Junit framework to run the same tests with the Junit framework.
 in order to run with the Junit you need to use the maven command or run button or you can directly run the DataValidationTest.java class.