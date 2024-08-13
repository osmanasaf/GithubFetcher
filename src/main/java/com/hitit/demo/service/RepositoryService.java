package com.hitit.demo.service;

import com.hitit.demo.data.entity.Repository;
import com.hitit.demo.data.response.RepositoryResponse;
import com.hitit.demo.mapper.RepositoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hitit.demo.repository.RepositoryRepository;

import java.util.List;

@Service
public class RepositoryService {

    private final GitHubClient gitHubClient;
    private final RepositoryRepository repositoryRepository;
    private final RepositoryMapper repositoryMapper;

    @Autowired
    public RepositoryService(GitHubClient gitHubClient,
                             RepositoryRepository repositoryRepository,
                             RepositoryMapper repositoryMapper) {
        this.gitHubClient = gitHubClient;
        this.repositoryRepository = repositoryRepository;
        this.repositoryMapper = repositoryMapper;
    }

    public List<RepositoryResponse> getTopRepositories() {
        return gitHubClient.fetchTopRepositories();
    }

    @Transactional
    public Repository saveRepository(RepositoryResponse repositoryResponse) {
            Repository repository = repositoryMapper.mapToRepository(repositoryResponse);
            repositoryRepository.save(repository);
            return repository;
    }
    
    public Repository findByName(String name) {
        return repositoryRepository.findByName(name);
    }
}
