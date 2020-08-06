package com.wh.user.mapper;

import com.wh.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-27
 */
public interface UserMapper extends JpaRepository<User, Long> {
}
