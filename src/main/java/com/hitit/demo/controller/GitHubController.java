package com.hitit.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hitit.demo.service.GitHubService;


@RestController
public class GitHubController {

    private final GitHubService gitHubService;

    @Autowired
    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/run-task")
    public String runTask() {
        gitHubService.saveTopRepositoriesWithContributors();
        return "Task Completed";
    }
}
