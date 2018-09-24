package com.github.hexagonoframework.example.webapplication.infrastructure.jpa;

import javax.enterprise.context.Dependent;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Dependent
@Repository
public interface JpaBookmarkRepositoryDelegate extends EntityRepository<JpaBookmark, String> {

    JpaBookmark findAnyByName(String name);

    JpaBookmark findAnyByUrl(String url);

}
