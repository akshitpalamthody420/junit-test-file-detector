package com.akshit.testdetector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Finds Java source files below a project directory.
 */
public class ProjectScanner {

    public List<Path> findJavaFiles(Path rootDirectory) throws IOException {
        if (!Files.exists(rootDirectory)) {
            throw new IOException("Input directory does not exist: " + rootDirectory);
        }
        if (!Files.isDirectory(rootDirectory)) {
            throw new IOException("Input path is not a directory: " + rootDirectory);
        }

        try (var paths = Files.walk(rootDirectory)) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .sorted()
                    .toList();
        }
    }
}
