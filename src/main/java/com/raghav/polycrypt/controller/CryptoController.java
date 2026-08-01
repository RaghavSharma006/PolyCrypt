package com.raghav.polycrypt.controller;

import com.raghav.polycrypt.CryptoBridge;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Controller
public class CryptoController {

    private final CryptoBridge bridge = new CryptoBridge();

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/encrypt")
    public String encryptPage() {
        return "encrypt";
    }

    @GetMapping("/decrypt")
    public String decryptPage() {
        return "decrypt";
    }

    @GetMapping("/learn")
    public String learnPage() {
        return "learn";
    }

    @PostMapping("/encrypt")
    public String encrypt(
            @RequestParam("message") ArrayList<String> messages,
            @RequestParam("key") ArrayList<String> keys,
            Model model) {

        System.out.println("\n================ ENCRYPT REQUEST RECEIVED ================");

        System.out.println("Messages Count : " + messages.size());
        System.out.println("Keys Count     : " + keys.size());

        System.out.println("\n------------ RAW REQUEST ------------");

        for (int i = 0; i < messages.size(); i++) {
            System.out.println("Message[" + i + "] = '" + messages.get(i) + "'");
        }

        for (int i = 0; i < keys.size(); i++) {
            System.out.println("Key[" + i + "] = '" + keys.get(i) + "'");
        }

        if (messages.size() != keys.size()) {
            System.out.println("\n******** ERROR ********");
            System.out.println("Messages and Keys count DO NOT MATCH!");
            System.out.println("Messages = " + messages.size());
            System.out.println("Keys     = " + keys.size());
            System.out.println("************************");
        }

        ArrayList<String[]> input = new ArrayList<>();

        System.out.println("\n------------ INPUT TO CRYPTOBRIDGE ------------");

        for (int i = 0; i < Math.min(messages.size(), keys.size()); i++) {

            String message = messages.get(i);
            String key = keys.get(i);

            System.out.println("--------------------------------");
            System.out.println("Pair " + i);
            System.out.println("Message : '" + message + "'");
            System.out.println("Length  : " + message.length());
            System.out.println("Key     : '" + key + "'");
            System.out.println("Length  : " + key.length());

            input.add(new String[]{message, key});
        }

        System.out.println("--------------------------------");
        System.out.println("Input Size = " + input.size());

        System.out.println("\nCalling CryptoBridge.encrypt()...\n");

        String ciphertext = bridge.encrypt(input);

        System.out.println("\n------------ RESULT ------------");
        System.out.println(ciphertext);
        System.out.println("===============================================\n");

        model.addAttribute("ciphertext", ciphertext);

        return "encrypt";
    }

    @PostMapping("/decrypt")
    public String decrypt(
            @RequestParam(required = false) String ciphertext,
            @RequestParam(required = false) MultipartFile file,
            @RequestParam String key,
            Model model) throws IOException {

        // If a file was uploaded, use it instead of the textarea
        if (file != null && !file.isEmpty()) {
            ciphertext = new String(file.getBytes(), StandardCharsets.UTF_8);
        }

        String message = bridge.decrypt(ciphertext, key);

        model.addAttribute("message", message);

        return "decrypt";
    }

}