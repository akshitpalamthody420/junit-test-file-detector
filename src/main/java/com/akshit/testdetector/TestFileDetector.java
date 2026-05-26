package com.akshit.testdetector;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.nio.file.Path;
import java.util.Set;

/**
 * Parses a Java file and decides whether it contains JUnit test methods.
 *
 * This is static analysis: the source code is inspected without compiling or running it.
 */
public class TestFileDetector {

    private static final Set<String> JUNIT_ANNOTATIONS = Set.of(
            "Test",
            "ParameterizedTest",
            "RepeatedTest",
            "TestFactory",
            "TestTemplate"
    );

    public DetectionResult analyse(Path file) {
        try {
            CompilationUnit compilationUnit = StaticJavaParser.parse(file);

            int testMethodCount = (int) compilationUnit.findAll(MethodDeclaration.class)
                    .stream()
                    .filter(this::isJUnitTestMethod)
                    .count();

            boolean isTestFile = testMethodCount > 0;
            return new DetectionResult(
                    file.toString(),
                    isTestFile,
                    testMethodCount,
                    isTestFile ? "JUNIT" : "NONE",
                    "OK"
            );
        } catch (Exception exception) {
            return DetectionResult.parseError(file.toString());
        }
    }

    private boolean isJUnitTestMethod(MethodDeclaration method) {
        return method.getAnnotations()
                .stream()
                .anyMatch(annotation -> JUNIT_ANNOTATIONS.contains(annotation.getNameAsString()));
    }
}
