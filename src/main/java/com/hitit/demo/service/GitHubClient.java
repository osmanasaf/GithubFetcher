package com.hitit.demo.service;

import com.hitit.demo.data.response.ContributorResponse;
import com.hitit.demo.data.response.RepositoryResponse;
import com.hitit.demo.data.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@PropertySource("application.properties")
public class GitHubClient {

    private final WebClient webClient;


    @Autowired
    public GitHubClient(WebClient.Builder webClientBuilder, @Value("${github.api.token}") String githubToken) {
        WebClient.Builder builder = webClientBuilder.baseUrl("https://api.github.com");

        if (StringUtils.hasText(githubToken)) {
            builder.defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + githubToken);
        }

        this.webClient = builder.build();
    }

    public List<RepositoryResponse> fetchTopRepositories() {
        return webClient.get()
                .uri("/orgs/apache/repos?per_page=100&sort=updated")
                .retrieve()
                .bodyToFlux(RepositoryResponse.class)
                .collectList()
                .block();
    }

    public List<ContributorResponse> fetchContributors(String repoName) {
        return webClient.get()
                .uri("/repos/apache/{repoName}/contributors?per_page=10", repoName)
                .retrieve()
                .bodyToFlux(ContributorResponse.class)
                .collectList()
                .block();
    }

    public UserResponse fetchUserDetails(String username) {
        return webClient.get()
                .uri("/users/{username}", username)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
    }
}
