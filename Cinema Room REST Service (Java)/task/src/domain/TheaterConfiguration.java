package domain;

import domain.models.Theater;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TheaterConfiguration {
    @Bean
    public static Theater createDefaultTheater() {
        return new Theater(9, 9);
    }
}
