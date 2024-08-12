package com.hitit.demo.data.entity;

import com.hitit.demo.data.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "user_table")
public class User extends BaseEntity {

    private String username;
    private String location;
    private String company;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contributor> contributions;

}
