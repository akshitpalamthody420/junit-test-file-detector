package com.akshit.testdetector;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Writes scan results to a CSV file that can be opened in Excel, Sheets, or analysed by Python.
 */
public class CsvReportWriter {

    public void write(Path outputFile, List<DetectionResult> results) throws IOException {
        Path parent = outputFile.toAbsolutePath().getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(outputFile)) {
            writer.write("file_path,is_test_file,test_method_count,framework,status");
            writer.newLine();

            for (DetectionResult result : results) {
                writer.write(toCsvRow(result));
                writer.newLine();
            }
        }
    }

    private String toCsvRow(DetectionResult result) {
        return String.join(",",
                escape(result.filePath()),
                String.valueOf(result.testFile()),
                String.valueOf(result.testMethodCount()),
                escape(result.framework()),
                escape(result.status())
        );
    }

    private String escape(String value) {
        String safeValue = value == null ? "" : value;
        return "\"" + safeValue.replace("\"", "\"\"") + "\"";
    }
}
