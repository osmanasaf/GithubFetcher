package com.hitit.demo;

import com.hitit.demo.data.entity.Contributor;
import com.hitit.demo.data.entity.Repository;
import com.hitit.demo.data.entity.User;
import com.hitit.demo.data.response.ContributorResponse;
import com.hitit.demo.mapper.ContributorMapper;
import com.hitit.demo.repository.ContributorRepository;
import com.hitit.demo.service.ContributorService;
import com.hitit.demo.service.GitHubClient;
import com.hitit.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ContributorServiceTest {

    @Mock
    private GitHubClient gitHubClient;

    @Mock
    private ContributorRepository contributorRepository;

    @Mock
    private UserService userService;

    @Mock
    private ContributorMapper contributorMapper;

    @InjectMocks
    private ContributorService contributorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchContributors() {
        ContributorResponse contributorResponse = new ContributorResponse();
        contributorResponse.setLogin("user1");
        when(gitHubClient.fetchContributors("repo1")).thenReturn(List.of(contributorResponse));

        List<ContributorResponse> contributors = contributorService.fetchContributors("repo1");

        assertEquals(1, contributors.size());
        verify(gitHubClient, times(1)).fetchContributors("repo1");
    }

    @Test
    void testSaveContributors() {
        ContributorResponse contributorResponse = new ContributorResponse();
        contributorResponse.setLogin("user1");
        User user = new User();
        Repository repository = new Repository();
        Contributor contributor = new Contributor();

        when(userService.fetchUser("user1")).thenReturn(user);
        when(contributorMapper.mapToContributor(contributorResponse, user, repository)).thenReturn(contributor);

        contributorService.saveContributors(List.of(contributorResponse), repository);

        verify(contributorRepository, times(1)).save(contributor);
    }
}
