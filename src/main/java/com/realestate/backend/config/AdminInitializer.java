package com.realestate.backend.config;

import com.realestate.backend.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final UserService userService;

    @Value("${APP_ADMIN_NAME:}")
    private String adminName;

    @Value("${APP_ADMIN_EMAIL:}")
    private String adminEmail;

    @Value("${APP_ADMIN_PASSWORD:}")
    private String adminPassword;

    @Value("${APP_ADMIN_PHONE:}")
    private String adminPhone;

    public AdminInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        if (adminEmail.isBlank() || adminPassword.isBlank()) {
            System.out.println(
                "Admin initialization skipped: credentials are not configured."
            );
            return;
        }

        userService.createAdminIfNotExists(
            adminName.isBlank() ? "Administrator" : adminName,
            adminEmail.trim(),
            adminPassword,
            adminPhone
        );

        System.out.println("Admin account initialization completed.");
    }
}