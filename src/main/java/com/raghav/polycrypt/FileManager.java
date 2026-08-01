package com.raghav.polycrypt;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileManager {

    public static String openCiphertextFile() {

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Open PolyCrypt Ciphertext");

        int result = chooser.showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION)
            return null;

        File file = chooser.getSelectedFile();

        try {
            return Files.readString(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file.", e);
        }
    }
}