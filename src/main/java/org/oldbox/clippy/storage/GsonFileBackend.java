package org.oldbox.clippy.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.oldbox.clippy.Category;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.FileSystemException;
import java.util.Map;

public class GsonFileBackend implements StorageBackend {

    private static final String SAVE_DIRECTORY_NAME = ".clippy";
    private static final String SAVE_FILE_NAME = "save.json";


    public GsonFileBackend() throws FileSystemException {
        this.checkSaveDirPath();
    }

    private static String getSaveDirPath() {
        String homePath = System.getProperty("user.home");

        return homePath + File.separator + SAVE_DIRECTORY_NAME;
    }

    private static String getSaveFilePath() {
        return getSaveDirPath() + File.separator + SAVE_FILE_NAME;
    }

    private void checkSaveDirPath() throws FileSystemException {
        File dir = new File(getSaveDirPath());
        if(!dir.exists() || !dir.isDirectory()) {
            if (!dir.mkdir()) {
                throw new FileSystemException(dir.toString(), null, "Unable to create directory for save file");
            }
        }
    }

    @Override
    public void saveDatabase(Map<String, Category> database) throws FileSystemException {
        Gson gson = new Gson();
        String saveFilePath = getSaveFilePath();
        String json = gson.toJson(database);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveFilePath));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileSystemException(saveFilePath, null, "Unable to open save file for writing");
        }
    }

    @Override
    public Map<String, Category> loadDatabase() throws FileNotFoundException {
        Gson gson = new Gson();
        String saveFilePath = getSaveFilePath();

        BufferedReader reader = new BufferedReader(new FileReader(saveFilePath));

        Type typeOfHashMap = new TypeToken<Map<String, Category>>() { }.getType();
        Map<String, Category> database = gson.fromJson(reader, typeOfHashMap);

        return database;
    }
}
