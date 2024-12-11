package guru.springframework.springaiimage.services;

import guru.springframework.springaiimage.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.azure.openai.AzureOpenAiImageModel;
import org.springframework.ai.azure.openai.AzureOpenAiImageOptions;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.stereotype.Service;

import java.util.Base64;

import static org.springframework.ai.azure.openai.AzureOpenAiImageOptions.ImageModel.DALL_E_3;


@RequiredArgsConstructor
@Service
public class OpenAIService {
    // TODO: doesn't work with free models - dall-e-3 is not exposed
    private final AzureOpenAiImageModel imageModel;

    public byte[] getImage(Question question) {
        var extendedOptions = AzureOpenAiImageOptions.builder()
                .withHeight(1024).withWidth(1792)
                .withResponseFormat("b64_json")
                .withModel(DALL_E_3.getValue())
                .withStyle("natural") //default vivid
                .build();

        var options = ImageOptionsBuilder.builder()
                .withHeight(1024).withWidth(1024)
                .withModel(DALL_E_3.getValue())
                .withResponseFormat("b64_json")
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(question.question(), options);
        var imageResponse = imageModel.call(imagePrompt);
        return Base64.getDecoder().decode(imageResponse.getResult().getOutput().getB64Json());
    }
}