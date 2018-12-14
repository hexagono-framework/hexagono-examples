package com.github.hexagonoframework.example.bookmark;

import javax.enterprise.context.Dependent;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Dependent
@Repository
interface JpaBookmarkRepositoryDelegate extends EntityRepository<JpaBookmark, String> {

    JpaBookmark findAnyByName(String name);

    JpaBookmark findAnyByUrl(String url);

}
