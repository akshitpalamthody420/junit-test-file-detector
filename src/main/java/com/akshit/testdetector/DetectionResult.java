package com.akshit.testdetector;

/**
 * Immutable result for one scanned Java file.
 */
public record DetectionResult(
        String filePath,
        boolean testFile,
        int testMethodCount,
        String framework,
        String status
) {
    public static DetectionResult parseError(String filePath) {
        return new DetectionResult(filePath, false, 0, "UNKNOWN", "PARSE_ERROR");
    }
}
