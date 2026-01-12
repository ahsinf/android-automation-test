# Android Automation Test – QA Engineer Technical Test

This repository contains Android automation tests developed as part of the QA Engineer Technical Test.
The goal of this project is to demonstrate test strategy, scenario selection, and clean automation implementation
using Java, Appium, and Cucumber.

---

## 🧪 Scope of Automation

The automation focuses on **high-value and high-risk user journeys**, rather than covering all possible UI interactions.

### Automated Scenarios
- End-to-End Happy Path Checkout
- Add multiple products to cart and verify cart consistency
- Remove Item from Cart (assume 1 product on cart)
- Add more than 1 pcs with same product
- Sort products by Price - Ascending
- Verify form login (mandatory field and credential login)
- Checkout form validation (Shipping Address & Payment Method)

These scenarios were selected because they represent **core business flows** and common risk areas
such as data consistency, validation, and order completion.

### Non-Automated Scenarios (Documented Only)
- Sort products by Name - Ascending
- Sort products by Name - Descending
- Sort products by Price - Descending
- UI cosmetic checks

These scenarios were intentionally **not automated** to avoid flaky tests and low return on investment.
They are better suited for manual exploratory testing or API-level validation.

---

## 🛠️ Environment Setup
Make sure the following tools are installed:
- Programming Language: **Java 11**
```bash
  java -version
````
- Automation Framework: **Appium**
```bash
  npm install -g appium
  appium driver install uiautomator2
````
- BDD Framework: **Cucumber**
- Test Runner: **JUnit**
- Build Tool: **Maven**
```bash
  mvn -version
```
- Device: **Real Android Device**
- OS: **macOS**

---
## 📥 Clone Repository

Clone this repository to your local machine:
```bash
git clone https://github.com/ahsinf/android-automation-test.git
cd android-automation-test
```

## 🏗️ Project Structure
```
src/test
├── java
│ ├── base
│ │ ├── DriverManager.java
│ │ └── BaseTest.java
│ ├── pages
│ ├── stepdefinitions
│ ├── utils
│ │ └── WaitUtils.java
│ └── runners
│ └── TestRunner.java
└── resources
└── features
```

- **Page Object Model (POM)** is used to separate UI interactions from test logic.
- Explicit waits are implemented to ensure test stability and reduce flakiness.

---

## ⚙️ Prerequisites

- Java 11
- Android Studio & Android SDK
- Appium Server
- Node.js
- Real Android device with USB Debugging enabled

Verify device connection:
```bash
adb devices
```

▶️ How to Run the Tests

1. Start Appium server
2. Connect Android device
3. Run the following command:
```bash
mvn clean test
```
Test execution reports will be generated after the run.

📸 Test Evidence

Test execution evidence (screenshots / recordings) is provided to demonstrate successful test runs
and can be found in the repository.

