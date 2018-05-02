package com.hqg.api.util;

import java.util.Random;

public class StringUtil {
    //生成随机数
    public static String getClient_Sn(int codeLenth){
        while (true) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < codeLenth; i++) {
                if (i == 0)
                    sb.append(new Random().nextInt(9) + 1); // first field will not start with 0.
                else
                    sb.append(new Random().nextInt(10));
            }
            return sb.toString();
        }
    }
}
