package com.wh.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-08-03
 */
@Entity
@Table(name = "shop_tx_log")
@Data
public class TxLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long txLogId;

    private String content;

    private LocalDateTime gmtCreate;
}
