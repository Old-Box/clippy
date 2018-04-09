package org.oldbox.clippy.storage;

import org.oldbox.clippy.Category;

import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.util.Map;

public interface StorageBackend {
    void saveDatabase(Map<String, Category> database) throws FileSystemException;
    Map<String, Category> loadDatabase() throws FileNotFoundException, FileSystemException;
}
