package com.akshit.testdetector;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar junit-test-file-detector-1.0.0.jar <project-directory> <output-csv>");
            System.out.println("Example: java -jar target/junit-test-file-detector-1.0.0.jar sample-project reports/report.csv");
            System.exit(1);
        }

        Path projectDirectory = Path.of(args[0]);
        Path outputCsv = Path.of(args[1]);

        ProjectScanner scanner = new ProjectScanner();
        TestFileDetector detector = new TestFileDetector();
        CsvReportWriter writer = new CsvReportWriter();

        try {
            List<Path> javaFiles = scanner.findJavaFiles(projectDirectory);
            List<DetectionResult> results = new ArrayList<>();

            for (Path file : javaFiles) {
                results.add(detector.analyse(file));
            }

            writer.write(outputCsv, results);

            long testFileCount = results.stream().filter(DetectionResult::testFile).count();
            long parseErrors = results.stream().filter(result -> "PARSE_ERROR".equals(result.status())).count();

            System.out.println("Scanned " + javaFiles.size() + " Java files.");
            System.out.println("Detected " + testFileCount + " JUnit test files.");
            System.out.println("Parse errors: " + parseErrors);
            System.out.println("Report written to: " + outputCsv.toAbsolutePath());
        } catch (Exception exception) {
            System.err.println("Failed to scan project: " + exception.getMessage());
            System.exit(1);
        }
    }
}
