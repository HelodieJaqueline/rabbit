package com.zhangrui.rabbit.consumer.domain;

import com.alibaba.fastjson.JSON;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZhangRui
 * @Date: Created at 2019-11-22-15:12
 * @Description:
 * @Modified: By
 */
@Data
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String phone;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
