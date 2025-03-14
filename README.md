# Banking GUI Automation Tests

## Project Overview
This project is a test automation framework for validating the functionality of a banking web application. It uses **Selenium WebDriver**, **Cucumber**, and **TestNG**, with **Allure Reporting** for test execution reports.

## Technologies Used
- **Java** (Programming Language)
- **Selenium WebDriver** (Browser Automation)
- **Cucumber** (BDD Testing Framework)
- **TestNG** (Test Execution)
- **Allure Reporting** (Test Reporting)
- **Maven** (Dependency Management)

## Folder Structure
```
📦 banking-gui-automation
 ┣ 📂 .idea                    # Project configuration files
 ┣ 📂 main
 ┃ ┣ 📂 config                 # Configuration settings
 ┃ ┣ 📂 pages                  # Page Object Model (POM) classes
 ┃ ┣ 📂 resources              # Additional resources
 ┃ ┣ 📂 utils                  # Utility classes for common functionalities
 ┣ 📂 tests
 ┃ ┣ 📂 hooks                  # Cucumber Hooks for setup & teardown
 ┃ ┣ 📂 resources              # Test data and supporting files
 ┃ ┣ 📂 runners                # Test runner classes
 ┃ ┣ 📂 stepDefinitions        # Step definitions for Cucumber scenarios
 ┣ 📜 banking-gui-tests.iml    # IntelliJ Project File
 ┣ 📜 pom.xml                  # Maven Project File
 ┣ 📜 requirements.txt         # Dependencies list
 ┣ 📜 README.md                # Project Documentation
```

## How to Run Tests
1. **Clone the Repository**
   ```sh
   git clone https://github.com/your-repo/banking-gui-automation.git
   ```
2. **Navigate to the Project Directory**
   ```sh
   cd banking-gui-automation
   ```
3. **Run Tests using Maven**
   ```sh
   mvn clean test
   ```
4. **View Allure Reports** (After running tests)
   ```sh
   mvn allure:serve
   ```

## Reporting
- The project integrates **Allure Reporting**.
- Run the following command to generate a report:
  ```sh
  mvn allure:serve
  ```

## Contribution
Feel free to fork the repository, make changes, and submit a pull request. Contributions are welcome!

## License
This project is licensed under the MIT License.

