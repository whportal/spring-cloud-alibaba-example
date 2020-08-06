package com.wh.order.mapper;

import com.wh.dao.entity.TxLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-08-03
 */
public interface TxLogMapper extends JpaRepository<TxLog, Long> {
}
