package com.hcl.simple_nms.service;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileWriterService {

    private static final String FILE_PATH = "router-data.log";

    public void writeToFile(String message) {

        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {

            writer.write(message + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}