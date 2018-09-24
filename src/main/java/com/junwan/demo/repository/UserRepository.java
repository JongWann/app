package com.junwan.demo.repository;

import com.junwan.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 根据用户名查询
     * @param name
     * @return
     */
    List<User> findByName(String name);
}
