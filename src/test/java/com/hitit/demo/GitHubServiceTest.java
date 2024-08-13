package com.hitit.demo;

import com.hitit.demo.data.entity.Repository;
import com.hitit.demo.data.response.ContributorResponse;
import com.hitit.demo.data.response.RepositoryResponse;
import com.hitit.demo.service.ContributorService;
import com.hitit.demo.service.GitHubRepositoryService;
import com.hitit.demo.service.GitHubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import static org.mockito.Mockito.*;

class GitHubServiceTest {

    @Mock
    private GitHubRepositoryService gitHubRepositoryService;

    @Mock
    private ContributorService contributorService;

    @InjectMocks
    private GitHubService gitHubService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTopRepositoriesWithContributors() {
        // Arrange
        List<RepositoryResponse> repositories = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            RepositoryResponse repoResponse = new RepositoryResponse();
            repoResponse.setName("repo" + i);
            repoResponse.setStargazersCount(i * 100); // Stargazers from 100 to 1000
            repositories.add(repoResponse);
        }

        List<ContributorResponse> contributors = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ContributorResponse contributorResponse = new ContributorResponse();
            contributorResponse.setLogin("contributor" + i);
            contributors.add(contributorResponse);
        }

        when(gitHubRepositoryService.getTopRepositories()).thenReturn(repositories);
        when(contributorService.fetchContributors(anyString())).thenReturn(contributors);
        when(gitHubRepositoryService.saveRepository(any(RepositoryResponse.class))).thenReturn(new Repository());

        // Act
        gitHubService.saveTopRepositoriesWithContributors();

        // Assert
        verify(gitHubRepositoryService, times(5)).saveRepository(any(RepositoryResponse.class));
        verify(contributorService, times(5)).fetchContributors(anyString());
        verify(contributorService, times(5)).saveContributors(anyList(), any(Repository.class));
    }
}
