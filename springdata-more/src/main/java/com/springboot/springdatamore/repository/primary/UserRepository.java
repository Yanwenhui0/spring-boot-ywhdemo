package com.springboot.springdatamore.repository.primary;

import com.springboot.springdatamore.entity.primary.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/7/29
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
