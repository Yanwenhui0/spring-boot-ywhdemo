package com.springboot.springdatajpa.repository;

import com.springboot.springdatajpa.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/12/2
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
