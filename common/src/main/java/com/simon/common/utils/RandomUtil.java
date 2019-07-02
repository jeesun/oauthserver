package com.simon.common.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机工具
 *
 * @author simon
 * @date 2018-12-07
 **/

public class RandomUtil {

    /**
     * 生成指定长度的随机的字母数字串
     * @param length 长度
     * @return 指定长度的随机的字母数字串
     */
    public static String randomCharAndNum(int length) {
        StringBuilder sb = new StringBuilder();
        //随机用以下三个随机生成器
        Random rand = ThreadLocalRandom.current();
        Random randData = ThreadLocalRandom.current();
        int data;
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(3);
            //目的是随机选择生成数字，大小写字母
            switch (index) {
                case 0:
                    //仅仅会生成0~9
                    data = randData.nextInt(10);
                    sb.append(data);
                    break;
                case 1:
                    //保证只会产生65~90之间的整数
                    data = randData.nextInt(26) + 65;
                    sb.append((char) data);
                    break;
                case 2:
                    //保证只会产生97~122之间的整数
                    data = randData.nextInt(26) + 97;
                    sb.append((char) data);
                    break;
                default:
                    break;
            }
        }
        return sb.toString();
    }
}
