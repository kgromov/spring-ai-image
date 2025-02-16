package guru.springframework.springaiimage;

import org.kgromov.observability.EnableMetadataObservabilityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMetadataObservabilityConfig
@SpringBootApplication
public class SpringAiImageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiImageApplication.class, args);
    }

}
