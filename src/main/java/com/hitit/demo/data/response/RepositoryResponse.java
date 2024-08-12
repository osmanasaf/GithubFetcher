package com.hitit.demo.data.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepositoryResponse {
    private Long id;
    private String name;

    @JsonProperty("stargazers_count")
    private int stargazersCount;

    @JsonProperty( "watchers_count")
    private int watchersCount;

    private String language;

    @JsonProperty("open_issues_count")
    private int openIssuesCount;


    private String description;

}
