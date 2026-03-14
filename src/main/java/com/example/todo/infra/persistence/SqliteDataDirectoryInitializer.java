package com.example.todo.infra.persistence;

import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.stereotype.Component;

@Component
public class SqliteDataDirectoryInitializer {
  public SqliteDataDirectoryInitializer() {
    try {
      Files.createDirectories(Path.of("data"));
    } catch (Exception ex) {
      throw new IllegalStateException("Failed to create data directory", ex);
    }
  }
}
