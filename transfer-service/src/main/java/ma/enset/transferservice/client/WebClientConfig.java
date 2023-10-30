package ma.enset.transferservice.client;


import ma.enset.configservice.exception.ApiClientException;
import ma.enset.configservice.exception.InternalErrorException;
import ma.enset.configservice.exception.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Slf4j
@Configuration(proxyBeanMethods = false)
public class WebClientConfig {
    private final String WALLET_SERVICE_NAME = "WALLET-SERVICE";

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .defaultStatusHandler(
                        HttpStatusCode::is4xxClientError,
                        clientResponse -> clientResponse
                                .bodyToMono(ExceptionResponse.class)
                                .map(ApiClientException::new)
                )
                .defaultStatusHandler(
                        HttpStatusCode::is5xxServerError,
                        clientResponse -> clientResponse
                                .createException()
                                .map(exception -> new InternalErrorException(
                                        exception.getMessage(), exception.getCause()
                                ))
                );
    }


    @Bean
    public WalletClient filiereClient(WebClient.Builder builder) {
        WebClient filiereWebClient = builder
                .baseUrl("http://" + WALLET_SERVICE_NAME)
                .build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(filiereWebClient))
                .build();

        return factory.createClient(WalletClient.class);
    }
}
