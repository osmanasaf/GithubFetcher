package com.hitit.demo.data.entity;

import com.hitit.demo.data.base.BaseEntity;
import com.hitit.demo.listener.AuditListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditListener.class)
public class Repository extends BaseEntity {

    private String name;
    private int stargazerCount;
    private int watchersCount;
    private String language;
    private int openIssuesCount;

    @OneToMany(mappedBy = "repository", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contributor> contributors;

    public String toString() {
        return "Repository Name: " + name +
                ", Stargazers: " + stargazerCount +
                ", Watchers: " + watchersCount +
                ", Language: " + (language != null ? language : "N/A") +
                ", Open Issues: " + openIssuesCount;
    }


}
