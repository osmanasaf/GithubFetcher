package com.hitit.demo.service;

import com.hitit.demo.data.entity.Contributor;
import com.hitit.demo.data.entity.Repository;
import com.hitit.demo.data.entity.User;
import com.hitit.demo.data.response.ContributorResponse;
import com.hitit.demo.mapper.ContributorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hitit.demo.repository.ContributorRepository;

import java.util.List;

@Service
public class ContributorService {

    private final GitHubClient gitHubClient;
    private final ContributorRepository contributorRepository;
    private final UserService userService;
    private final ContributorMapper contributorMapper;

    @Autowired
    public ContributorService(GitHubClient gitHubClient,
                              ContributorRepository contributorRepository,
                              UserService userService,
                              ContributorMapper contributorMapper) {
        this.gitHubClient = gitHubClient;
        this.contributorRepository = contributorRepository;
        this.userService = userService;
        this.contributorMapper = contributorMapper;
    }

    public List<ContributorResponse> fetchContributors(String repoName) {
        return gitHubClient.fetchContributors(repoName);
    }

    public void saveContributors(List<ContributorResponse> contributors, Repository repository) {
        for (ContributorResponse contributorResponse : contributors) {
            User user = userService.fetchUser(contributorResponse.getLogin());

            Contributor contributor = contributorMapper.mapToContributor(contributorResponse, user, repository);
            contributorRepository.save(contributor);
        }
    }
}
