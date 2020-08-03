package com.springboot.springdatamore.repository.second;

import com.springboot.springdatamore.entity.second.MoreUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/7/29
 */
@Repository
public interface MoreUserRepository extends JpaRepository<MoreUser, Integer> {
}
