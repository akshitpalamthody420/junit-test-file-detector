# JUnit Test File Detector

A small Java static-analysis command-line tool inspired by the idea behind `TestSmells/TestFileDetector`.

It scans a Java project, detects files containing JUnit test methods, and writes a CSV report.

This is a good starter project for a Fixate-style application because it shows:

- Java development
- static source-code analysis
- AST parsing with JavaParser
- test-suite discovery
- CSV output for later Python/dashboard analysis

## What it detects

A Java file is marked as a test file if it contains a method annotated with one of:

- `@Test`
- `@ParameterizedTest`
- `@RepeatedTest`
- `@TestFactory`
- `@TestTemplate`

## Folder structure

```text
junit-test-file-detector/
  pom.xml
  src/main/java/com/akshit/testdetector/
    Main.java
    ProjectScanner.java
    TestFileDetector.java
    DetectionResult.java
    CsvReportWriter.java
  src/test/java/com/akshit/testdetector/
    TestFileDetectorTest.java
  sample-project/
    src/main/java/com/example/
      Calculator.java
      UserService.java
    src/test/java/com/example/
      CalculatorTest.java
      UserServiceTest.java
```

## Requirements

Install:

- Java 17+
- Maven 3+

Check your versions:

```bash
java -version
mvn -version
```

## Build

From the project root:

```bash
mvn clean package
```

This creates a runnable jar in `target/`.

## Run on the included sample project

```bash
java -jar target/junit-test-file-detector-1.0.0.jar sample-project reports/sample-report.csv
```

Expected terminal output:

```text
Scanned 4 Java files.
Detected 2 JUnit test files.
Parse errors: 0
Report written to: .../reports/sample-report.csv
```

Open the generated report:

```bash
cat reports/sample-report.csv
```

Expected CSV contents will look like this:

```csv
file_path,is_test_file,test_method_count,framework,status
"sample-project/src/main/java/com/example/Calculator.java",false,0,"NONE","OK"
"sample-project/src/main/java/com/example/UserService.java",false,0,"NONE","OK"
"sample-project/src/test/java/com/example/CalculatorTest.java",true,2,"JUNIT","OK"
"sample-project/src/test/java/com/example/UserServiceTest.java",true,1,"JUNIT","OK"
```

## Run unit tests

```bash
mvn test
```

## How it works

The pipeline is:

```text
Input project folder
        ↓
ProjectScanner finds all .java files
        ↓
TestFileDetector parses each file using JavaParser
        ↓
It finds method declarations
        ↓
It checks whether each method has a JUnit annotation
        ↓
CsvReportWriter writes the report
```

The main static-analysis logic is in:

```text
src/main/java/com/akshit/testdetector/TestFileDetector.java
```
