package ma.enset.transferservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        ma.enset.configservice.exception.handler.GlobalExceptionHandler.class,
})
public class Config {
}
