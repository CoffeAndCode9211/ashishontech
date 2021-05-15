# [Karate Testing](https://github.com/intuit/karate)
Covers 2 Test Cases which checks API for Save and Fetch User.

## Run Tests

```bash
 mvn clean test  -DbaseUrl=http://localhost:8080
```

**Note:** Assuming main application is already running at http://localhost:8080

## Report

Reports Will be generated at following path 

**Default Report:** integration-test/target/karate-reports/karate-summary.html
**Cucumber Report:** integration-test/target/cucumber-html-reports/overview-features.html
