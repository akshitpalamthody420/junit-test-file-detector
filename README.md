# JUnit Test File Detector

A small Java command-line tool that scans a Java project, identifies files containing JUnit test methods, and writes the results to a CSV report.

The tool uses JavaParser to parse source files and inspect method annotations, rather than relying only on file names or folder paths.

## What it detects

A Java file is marked as a test file if it contains at least one method annotated with one of:

- `@Test`
- `@ParameterizedTest`
- `@RepeatedTest`
- `@TestFactory`
- `@TestTemplate`

## Why this is useful

Before analysing test quality, a tool first needs to identify where the tests are. This project focuses on that discovery step and produces structured output that could be used by later analysis or reporting tools.

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
