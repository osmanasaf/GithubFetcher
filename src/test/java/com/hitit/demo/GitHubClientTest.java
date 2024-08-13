package com.hitit.demo;

import com.hitit.demo.data.response.ContributorResponse;
import com.hitit.demo.data.response.RepositoryResponse;
import com.hitit.demo.data.response.UserResponse;
import com.hitit.demo.service.GitHubClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class GitHubClientTest {

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @Mock
    private RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RequestHeadersSpec requestHeadersSpec;

    @Mock
    private ResponseSpec responseSpec;

    private GitHubClient gitHubClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
        when(webClientBuilder.defaultHeader(eq(HttpHeaders.AUTHORIZATION), anyString())).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);

        gitHubClient = new GitHubClient(webClientBuilder, "");
    }

    @Test
    void fetchTopRepositories_shouldReturnRepositories() {
        RepositoryResponse mockResponse = new RepositoryResponse();
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToFlux(RepositoryResponse.class)).thenReturn(Flux.just(mockResponse));

        List<RepositoryResponse> result = gitHubClient.fetchTopRepositories();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockResponse, result.get(0));
    }

    @Test
    void fetchContributors_shouldReturnContributors() {
        ContributorResponse mockResponse = new ContributorResponse();
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), (Object) any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToFlux(ContributorResponse.class)).thenReturn(Flux.just(mockResponse));

        List<ContributorResponse> result = gitHubClient.fetchContributors("test-repo");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockResponse, result.get(0));
    }

    @Test
    void fetchUserDetails_shouldReturnUserDetails() {
        UserResponse mockResponse = new UserResponse();
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), (Object) any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(UserResponse.class)).thenReturn(Mono.just(mockResponse));

        UserResponse result = gitHubClient.fetchUserDetails("test-user");

        assertNotNull(result);
        assertEquals(mockResponse, result);
    }
}
