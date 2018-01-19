package com.tombigun.spi.impl;

import com.tombigun.spi.PersonService;

/**
 * @author tombigun
 * @version 1.0
 * @date 2018/1/19
 */
public class TeacherServiceImpl implements PersonService{
    @Override
    public String getName() {
        return "我是老师";
    }
}
