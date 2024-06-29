package guru.springframework.springaiimage.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ImageModelController {
    private final ChatModel chatModel;

    @GetMapping("/describe")
    public String describe() {
        var imageResource = new ClassPathResource("/images/drugs.jpg");
        UserMessage userMessage = new UserMessage("""
                The following is a screenshot of drugs.
                Can you do your best to provide a name and expiration date?
                Answer in format: name - date(MM/yyyy)
                """,
                List.of(new Media(MimeTypeUtils.IMAGE_JPEG, imageResource)));
        var response = chatModel.call(new Prompt(userMessage));
        return response.getResult().getOutput().getContent();
    }
}
