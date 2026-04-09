package com.healthapp.app.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class MedicineService {

    private final ChatClient chatClient;

    public MedicineService(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("You are a medical expert...")
                // Yahan model ko force kar do
                .defaultOptions(org.springframework.ai.google.genai.GoogleGenAiChatOptions.builder()
                        .model("gemini-2.5-flash")
                        .build())
                .build();
    }

    public String getMedicineInfo(String medicineName) {
        try {
            return chatClient.prompt()
                    .user("""
    Analyze this medicine: %s

    Return ONLY JSON:
    {
      "name": "",
      "type": "",
      "uses": ""
    }

    Example:
    {
      "name": "Paracetamol",
      "type": "Pain reliever",
      "uses": "Fever and mild pain"
    }
    """.formatted(medicineName))
                    .call()
                    .content();
        } catch (Exception e) {
            // Isse aapko exact error console pe dikhega
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
