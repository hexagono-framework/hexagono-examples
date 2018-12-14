package com.github.hexagonoframework.example.webapplication.infrastructure.jpa;

import javax.enterprise.context.Dependent;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Dependent
@Repository
public interface JpaLogRepository extends EntityRepository<JpaLog, String> {

}
