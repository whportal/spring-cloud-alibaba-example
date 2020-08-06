package com.wh.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020/08/04
 */
@Data
public class ProductDTO implements Serializable {

    private Long pid;

    private Integer number;
}
