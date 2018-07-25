package com.simon.common.utils;

import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * Created by simon on 2016/9/5.
 */
public class ImageUtil {
    public static byte[] convertToBytes(String poster) throws IOException{
        BASE64Decoder decoder = new BASE64Decoder();
        //Base64解码
        byte[] imgBytes = decoder.decodeBuffer(poster);
        for (int i=0; i<imgBytes.length; i++){
            if (imgBytes[i]<0){
                //调整异常数据
                imgBytes[i]+=256;
            }
        }
        return imgBytes;
    }
}
