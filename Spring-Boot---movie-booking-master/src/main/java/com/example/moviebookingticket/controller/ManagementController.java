package com.example.moviebookingticket.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@PreAuthorize("hasRole('USER')")
public class ManagementController {

    @GetMapping
    public String get() {
        return "GET :: User controller";
    }

    @PostMapping
    public String post() {
        return "POST :: User controller";
    }

    @PutMapping
    public String put() {
        return "PUT :: User controller";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE :: User controller";
    }
}
