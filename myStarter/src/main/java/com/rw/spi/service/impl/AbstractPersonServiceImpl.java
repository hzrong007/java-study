package com.rw.spi.service.impl;

import com.rw.spi.PersonService;

import java.util.Random;

public abstract class AbstractPersonServiceImpl implements PersonService {
    private final Random random = new Random();
    private static String GIRL = "乖巧可爱女儿";
    private static String BOY = "调皮捣蛋儿子";

    @Override
    public String say(String name) {
        return String.format("您好，%s，感谢您在%s岗位的付出。六一儿童节快到了，特为您%s准备了一份礼物，请注意查收!",
                name, getJob(), random.nextBoolean() ? GIRL : BOY);
    }

    /**
     * 工作岗位
     *
     * @return job
     */
    public abstract String getJob();
}
