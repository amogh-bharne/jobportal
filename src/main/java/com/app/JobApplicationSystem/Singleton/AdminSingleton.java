package com.app.JobApplicationSystem.Singleton;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.JobApplicationSystem.entities.Admin;

public class AdminSingleton {

    private static Admin instance;
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    private AdminSingleton() {
        // Private constructor to prevent instantiation
    }

    public static synchronized Admin getInstance() {
        if (instance == null) {
            // Create a new Admin instance only if it hasn't been created yet
            String email = "amogh@gmail.com";
            String fname = "amogh";
            String lname = "bharne";
            String password = passwordEncoder.encode("123450");

            

            String encryptedPassword = passwordEncoder.encode(password);
            instance = new Admin((long)1, email,fname, lname, encryptedPassword );
        }
        return instance;
    }
}
