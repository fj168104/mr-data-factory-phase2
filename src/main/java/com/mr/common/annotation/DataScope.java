package com.mr.common.annotation;

import lombok.Data;

/**
 * @description
 * @author: fengj
 * @data 2018/3/16
 * @version: V1.0.0
 */
@Data
public class DataScope {

    /**
     * 过滤sql片段
     * 比如 select * from
     * SELECT * from ({}) temp WHERE temp.dept_id in(3,4);
     */
    private String filterSql;
}
