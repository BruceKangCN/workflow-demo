package org.bkcloud.fleet.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * an abstract DTO class
 * @param <T> the POJO class to fill the data field
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractDto<T> {

    private int code;
    private String msg;
    private T data;

    public AbstractDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }
}
