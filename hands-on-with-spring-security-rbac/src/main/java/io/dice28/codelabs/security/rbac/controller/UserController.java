package io.dice28.codelabs.security.rbac.controller;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Value("${admin.token.prefix}")
    private String adminTokenPrefix;

    @GetMapping("/public/start")
    public ResponseEntity<String> publicWelcomeMessage(@RequestParam String name) {
        /* some data that any USER can access */
        return ResponseEntity.ok("Hello " + name);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/start")
    public ResponseEntity<String> adminWelcomeMessage(@RequestParam String name) {
        /* some data that only ADMIN user is authorized to access */
        return ResponseEntity.ok("Hello " + name + " - " + adminTokenPrefix + UUID.randomUUID());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/start")
    public ResponseEntity<String> userWelcomeMessage(@RequestParam String name) {
        String userToken = Integer.toString(new Random().nextInt(10000, 99999));
        return ResponseEntity.ok("Hello " + name + " - " + userToken);
    }
}
