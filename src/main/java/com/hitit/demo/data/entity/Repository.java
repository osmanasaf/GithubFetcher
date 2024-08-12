package com.hitit.demo.data.entity;

import com.hitit.demo.data.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Repository extends BaseEntity {

    private String name;
    private int stargazerCount;
    private int watchersCount;
    private String language;
    private int openIssuesCount;

    @OneToMany(mappedBy = "repository", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contributor> contributors;

}
