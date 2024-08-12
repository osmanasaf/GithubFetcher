package com.hitit.demo.mapper;

import com.hitit.demo.data.entity.Repository;
import com.hitit.demo.data.response.RepositoryResponse;
import org.springframework.stereotype.Component;

@Component
public class RepositoryMapper {

    public Repository mapToRepository(RepositoryResponse response) {
        Repository repository = new Repository();
        repository.setName(response.getName());
        repository.setStargazerCount(response.getStargazersCount());
        repository.setWatchersCount(response.getWatchersCount());
        repository.setLanguage(response.getLanguage());
        repository.setOpenIssuesCount(response.getOpenIssuesCount());
        return repository;
    }
}
