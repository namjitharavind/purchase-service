package com.rakbank.purchaseservice.config;



import com.rakbank.purchaseservice.restclient.properties.FeeServiceProperties;
import com.rakbank.purchaseservice.restclient.properties.StudentServiceProperties;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.time.Duration;


@Configuration
public class RestClientConfig {

    @Bean("student-service-rest-client")
    RestClient studentServiceApiClient(StudentServiceProperties properties, RestClient.Builder restClientBuilder) {
        return restClientBuilder
                .baseUrl(properties.getBaseUrl())
                .requestFactory(ClientHttpRequestFactories.get(
                        ClientHttpRequestFactorySettings.DEFAULTS
                                .withConnectTimeout(Duration.ofSeconds(5))
                                .withReadTimeout(Duration.ofSeconds(20)))
                )
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE).build();
    }

    @Bean("fee-service-rest-client")
    RestClient feeServiceApiClient(FeeServiceProperties properties, RestClient.Builder restClientBuilder) {
        return restClientBuilder
                .baseUrl(properties.getBaseUrl())
                .requestFactory(ClientHttpRequestFactories.get(
                        ClientHttpRequestFactorySettings.DEFAULTS
                                .withConnectTimeout(Duration.ofSeconds(5))
                                .withReadTimeout(Duration.ofSeconds(20)))
                )
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE).build();
    }
}
