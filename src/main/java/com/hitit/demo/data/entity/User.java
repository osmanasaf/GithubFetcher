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
@Table(name = "user_table")
@EntityListeners(AuditListener.class)
public class User extends BaseEntity {

    private String username;
    private String location;
    private String company;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contributor> contributions;

    public String toString() {
        return "Username: " + username +
                ", Location: " + (location != null ? location : "N/A") +
                ", Company: " + (company != null ? company : "N/A");
    }


}
