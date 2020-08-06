package com.wh.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-27
 */
@Data
@Entity
@Table(name = "shop_user")
public class User {

    /**
     * -AUTO        主键由程序控制, 是默认选项 ,不设置就是这个
     * -IDENTITY    主键由数据库生成, 采用数据库自增长, Oracle不支持这种方式
     * -SEQUENCE    通过数据库的序列产生主键, MYSQL  不支持
     * -TABLE       提供特定的数据库产生主键, 该方式更有利于数据库的移植
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
}
