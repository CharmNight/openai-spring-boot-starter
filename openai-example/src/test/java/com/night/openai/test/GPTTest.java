package com.night.openai.test;

import com.night.openai.api.completion.chat.*;
import com.night.openai.example.ExampleApplication;
import com.night.openai.service.server.ai.openai.OpenAiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.http.PUT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

@SpringBootTest(classes = ExampleApplication.class)
@RunWith(SpringRunner.class)
public class GPTTest {

    @Autowired
    private OpenAiService openaiAi;

    @Test
    public void createGPT(){
        while (true) {
            System.out.println("---");
            Scanner input = new Scanner(System.in);
            String next = input.next().trim();
            if (next.equals("q")) {
                break;
            }
            final List<ChatMessage> messages = new ArrayList<>();
            ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), next);
            messages.add(systemMessage);
            ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                    .builder()
                    .model("gpt-3.5-turbo")
                    .messages(messages)
                    .n(1)
                    .maxTokens(50)
                    .logitBias(new HashMap<>())
                    .build();
            ChatCompletionResult chatCompletion = openaiAi.createChatCompletion(chatCompletionRequest);
            for (ChatCompletionChoice choice : chatCompletion.getChoices()) {
                ChatMessage message = choice.getMessage();
                messages.add(message);
                System.out.println(message.getContent());
            }
        }
    }
    @Test
    public void createGPTSteam(){
        while (true) {
            System.out.println("---");
            Scanner input = new Scanner(System.in);
            String next = input.next().trim();
            if (next.equals("q")) {
                break;
            }
            final List<ChatMessage> messages = new ArrayList<>();
            ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), next);
            messages.add(systemMessage);
            ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                    .builder()
                    .model("gpt-3.5-turbo")
                    .messages(messages)
                    .n(1)
                    .maxTokens(50)
                    .logitBias(new HashMap<>())
                    .build();

            openaiAi.streamChatCompletion(chatCompletionRequest)
                    .doOnError(Throwable::printStackTrace)
                    .blockingForEach(System.out::println);

        }
    }
}
