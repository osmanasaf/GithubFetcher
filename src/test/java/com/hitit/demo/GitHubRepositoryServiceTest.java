package com.hitit.demo;

import com.hitit.demo.data.entity.Repository;
import com.hitit.demo.data.response.RepositoryResponse;
import com.hitit.demo.mapper.RepositoryMapper;
import com.hitit.demo.repository.RepositoryRepository;
import com.hitit.demo.service.GitHubClient;
import com.hitit.demo.service.GitHubRepositoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GitHubRepositoryServiceTest {

    @Mock
    private GitHubClient gitHubClient;

    @Mock
    private RepositoryRepository repositoryRepository;

    @Mock
    private RepositoryMapper repositoryMapper;

    @InjectMocks
    private GitHubRepositoryService gitHubRepositoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTopRepositories() {
        RepositoryResponse repoResponse = new RepositoryResponse();
        repoResponse.setName("repo1");
        when(gitHubClient.fetchTopRepositories()).thenReturn(List.of(repoResponse));

        List<RepositoryResponse> repositories = gitHubRepositoryService.getTopRepositories();

        assertEquals(1, repositories.size());
        verify(gitHubClient, times(1)).fetchTopRepositories();
    }

    @Test
    void testSaveRepository() {
        RepositoryResponse repoResponse = new RepositoryResponse();
        repoResponse.setName("repo1");

        Repository repository = new Repository();
        repository.setName("repo1");

        when(repositoryMapper.mapToRepository(repoResponse)).thenReturn(repository);
        when(repositoryRepository.save(repository)).thenReturn(repository);

        Repository savedRepository = gitHubRepositoryService.saveRepository(repoResponse);

        assertEquals("repo1", savedRepository.getName());
        verify(repositoryRepository, times(1)).save(repository);
    }
}
