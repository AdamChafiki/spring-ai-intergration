package com.backend.aiknowledge.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j

public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    public ChatResponse chat(
            @RequestParam(defaultValue = "Hi") String message) {
        var reply = chatClient
                .prompt()
                .user(message)
                .call()
                .content();
        log.info("Response from Gemini: {}", reply);

        return new ChatResponse(message, reply);
    }

    public record ChatResponse(String prompt, String reply) {}
}