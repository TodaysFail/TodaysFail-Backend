package com.todaysfail.common.utils;

import java.security.SecureRandom;

public class RandomUtil {
    public static final SecureRandom random = new SecureRandom();

    public static int getRandomInt(int max) {
        return random.nextInt(max);
    }
}
