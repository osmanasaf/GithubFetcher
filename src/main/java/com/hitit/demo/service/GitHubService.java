package com.hitit.demo.service;

import com.hitit.demo.data.entity.Repository;
import com.hitit.demo.data.response.ContributorResponse;
import com.hitit.demo.data.response.RepositoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class GitHubService {

    private final RepositoryService gitHubRepositoryService;
    private final ContributorService contributorService;

    @Autowired
    public GitHubService(RepositoryService gitHubRepositoryService,
                         ContributorService contributorService) {
        this.gitHubRepositoryService = gitHubRepositoryService;
        this.contributorService = contributorService;
    }


    @Transactional
    public void saveTopRepositoriesWithContributors() {
        List<RepositoryResponse> repositories = gitHubRepositoryService.getTopRepositories();

        PriorityQueue<RepositoryResponse> topRepositories = new PriorityQueue<>(Comparator.comparingInt(RepositoryResponse::getStargazersCount));

            for (RepositoryResponse repo : repositories) {
                topRepositories.offer(repo);
                if (topRepositories.size() > 5) {
                    topRepositories.poll();
                }
            }

        for (RepositoryResponse repositoryResponse : topRepositories) {
            Repository repository = gitHubRepositoryService.saveRepository(repositoryResponse);
            List<ContributorResponse> contributors = contributorService.fetchContributors(repositoryResponse.getName());
            contributorService.saveContributors(contributors, repository);
        }
    }
}
