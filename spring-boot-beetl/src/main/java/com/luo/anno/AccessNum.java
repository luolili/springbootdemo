package com.luo.anno;

import java.lang.annotation.*;

/**
 * 统计 接口访问的次数
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessNum {
    String prefix();
}
