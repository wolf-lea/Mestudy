package com.tecsun.sisp.adapter.modules.finance.bankUtils;

/**
 * 用于银行数据转换通用类
 * Created by Ivan on 15-6-20.
 */
public class BKUtils {

    /**
     * 将 16进制字符串转为字节数组
     *
     * @param str
     * @return
     */
    public static byte[] hexStr_to_bytes(String str) {
        if (str.length() % 2 == 0) {
            byte[] returnB = new byte[str.length() / 2];

            int i = 0;
            while (i < str.length() / 2) {
                String t = str.substring(i * 2, i * 2 + 2);
                int aa = Integer.parseInt(t, 16);
                returnB[i] = (byte) aa;
                i++;
            }
            return returnB;
        }
        return null;
    }
    /**
     * 左靠bcd码，右补零
     * 十六进制字符串 转换为对应的 十六进制值数组(BCD) 输入：String asc
     * 给定的字符数组的长度 返回值：给定的字符数组对应的十六进制数组 例： "9aB" --> 0x09 0xAB
     *
     */
    public static byte[] str2Bcd(String asc) {
        if ( asc == null ) {
            return null;
        }

        int len = asc.length();
        int mod = len % 2;
        if (mod != 0) {
            asc += "0";
            len = asc.length();
        }
        byte abt[] = new byte[len];
        if (len >= 2) {
            len = len / 2;
        }
        byte bbt[] = new byte[len];
        abt = asc.getBytes();
        int j, k;
        for (int p = 0; p < asc.length() / 2; p++) {
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
                j = abt[2 * p] - '0';
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else {
                j = abt[2 * p] - 'A' + 0x0a;
            }
            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
                k = abt[2 * p + 1] - '0';
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            }
            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
    }
    /**
     * 将字符串转为 16进制字符串
     *
     * @param str
     * @return
     */
    public static String ascStrToHexStr(String str) {
        char[] chars = str.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (char aChar : chars) {
            hex.append(Integer.toHexString((int) aChar));
        }
        return hex.toString();
    }

    /**
     * 将 byte 数组转换成 16进制的字符串
     *
     * @param bs
     * @return
     */
    public static String bytes_to_hexStr(byte[] bs) {
        StringBuffer rs = new StringBuffer();
        for (byte b : bs) {
            String ts = Integer.toHexString(b & 0xff);
            rs.append((ts.length() == 1) ? "0" + ts : ts);
        }
        return rs.toString();
    }

    /**
     * 给字符串 前后补 0
     *
     * @param src  原字符串
     * @param tl   需要的长度
     * @param type 1-前补0 ; 2-后补0
     * @return
     */
    public static String add0(String src, int tl, int type) {
        int sl = src.length();
        if (sl < tl) {
            int i = 0;
            while (i < tl - sl) {
                if (type == 1) {
                    src = "0" + src;
                } else {
                    src = src + "0";
                }
                i++;
            }
        }
        return src;
    }
    /**
     * 将short转成byte[2]
     * @param a
     * @return
     */
    public static byte[] short2Byte(short a){
        byte[] b = new byte[2];

        b[0] = (byte) (a >> 8);
        b[1] = (byte) (a);

        return b;
    }
}
