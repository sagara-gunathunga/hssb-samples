package com.bpb.hssb.ch6.bookclub.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.web.client.RestClient;

@Configuration
public class BookclubConfiguration {

    @Value("${bookclub.api.baseurl}")
    private String baseurl;

    @Bean
    public RestClient restClient() {

        return RestClient.builder()
                .baseUrl(baseurl)
                .build();
    }

    @Bean
    public HttpSyncGraphQlClient httpSyncGraphQlClient(RestClient restClient) {

        return HttpSyncGraphQlClient.create(restClient);
    }
}