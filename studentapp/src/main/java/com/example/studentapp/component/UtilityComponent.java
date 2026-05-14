package com.example.studentapp.component;

import org.springframework.stereotype.Component;

@Component
public class UtilityComponent {

    public String formatMessage(String message) {
        return ">>> " + message.toUpperCase();
    }
}