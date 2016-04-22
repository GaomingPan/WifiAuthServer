package cn.com.octodata.auth.util;

import java.security.MessageDigest;

/**
 * Created by Xiang Wei Kong on 2015/12/18.
 */
public class MD5Util {
    private MD5Util() {
    }

    public static String getMD5(String plainText) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(plainText.getBytes());
        byte[] bytes = messageDigest.digest();
        int iByte;
        StringBuilder stringBuffer = new StringBuilder("");
        for (byte aByte : bytes) {
            iByte = aByte;
            if (iByte < 0) {
                iByte += 256;
            }
            if (iByte < 16) {
                stringBuffer.append("0");
            }
            stringBuffer.append(Integer.toHexString(iByte));
        }
        return stringBuffer.toString();
    }
}
