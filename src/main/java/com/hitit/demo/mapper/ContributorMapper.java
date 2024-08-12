package com.hitit.demo.mapper;

import com.hitit.demo.data.entity.Contributor;
import com.hitit.demo.data.entity.Repository;
import com.hitit.demo.data.entity.User;
import com.hitit.demo.data.response.ContributorResponse;
import org.springframework.stereotype.Component;

@Component
public class ContributorMapper {

    public Contributor mapToContributor(ContributorResponse response, User user, Repository repository) {
        Contributor contributor = new Contributor();
        contributor.setContributions(response.getContributions());
        contributor.setUser(user);
        contributor.setRepository(repository);
        return contributor;
    }
}
