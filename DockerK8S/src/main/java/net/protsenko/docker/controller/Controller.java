package net.protsenko.docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class Controller {
    @GetMapping
    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }
}