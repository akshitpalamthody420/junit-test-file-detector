package com.akshit.testdetector;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestFileDetectorTest {

    @Test
    void detectsFileWithJunitTestAnnotation() throws Exception {
        var tempFile = Files.createTempFile("CalculatorTest", ".java");
        Files.writeString(tempFile, """
                import org.junit.jupiter.api.Test;
                class CalculatorTest {
                    @Test
                    void addsNumbers() {}
                }
                """);

        DetectionResult result = new TestFileDetector().analyse(tempFile);

        assertTrue(result.testFile());
        assertEquals(1, result.testMethodCount());
        assertEquals("JUNIT", result.framework());
    }
}
