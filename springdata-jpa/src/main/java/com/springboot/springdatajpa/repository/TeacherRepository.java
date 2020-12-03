package com.springboot.springdatajpa.repository;

import com.springboot.springdatajpa.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/12/2
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
