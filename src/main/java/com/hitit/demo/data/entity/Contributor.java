package com.hitit.demo.data.entity;

import com.hitit.demo.data.base.BaseEntity;
import com.hitit.demo.listener.AuditListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@EntityListeners(AuditListener.class)
public class Contributor extends BaseEntity {

    private int contributions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repository_id")
    private Repository repository;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public String toString() {
        return "repo: " + repository.getName() +
                ", user: " + user.getUsername() +
                ", location: " + (user.getLocation() != null ? user.getLocation() : "N/A") +
                ", company: " + (user.getCompany() != null ? user.getCompany() : "N/A") +
                ", contributions: " + contributions;
    }


}
