package com.tecsun.sisp.adapter.modules.finance.bankUtils.decoder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.tecsun.sisp.adapter.modules.finance.bankUtils.BKUtils;
import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;


/**
 * 此类 返回报文解析:64域
 */
public class CCBBankPkgDecoder {
    public static final Logger logger = Logger.getLogger(CCBBankPkgDecoder.class);
    
    private Map<String, String> valueMap;
    private ChannelBuffer msg;

    public CCBBankPkgDecoder() {}
    

    
    /**
     * 解包
     *
     * @param msg      已经去掉 2byte 的长度
     * @param valueMap 结果集
     */
    public void decode(ChannelBuffer msg, Map<String, String> valueMap) {
        this.msg = msg;
        this.valueMap = valueMap;

        // TPDU
        byte[] next_bytes = new byte[5];
        msg.readBytes(next_bytes, 0, next_bytes.length);
        String next_bytes_str = "";
        for (byte next_byte : next_bytes) {
            String s = Integer.toHexString(next_byte & 0xff);
            next_bytes_str += ((s.length() == 1) ? "0" + s : s);
        }
        valueMap.put("tpdu", next_bytes_str);

        // 版本
        next_bytes = new byte[6];
        msg.readBytes(next_bytes, 0, next_bytes.length);
        next_bytes_str = "";
        for (byte next_byte : next_bytes) {
            String s = Integer.toHexString(next_byte & 0xff);
            next_bytes_str += ((s.length() == 1) ? "0" + s : s);
        }
        valueMap.put("verson", next_bytes_str);

        // 以下是 8583 报文解析
        // 0域: 消息类型
        next_bytes = new byte[2];
        msg.readBytes(next_bytes, 0, next_bytes.length);
        next_bytes_str = "";
        for (byte next_byte : next_bytes) {
            String s = Integer.toHexString(next_byte & 0xff);
            next_bytes_str += ((s.length() == 1) ? "0" + s : s);
        }
        valueMap.put("msgtype", next_bytes_str);

        // 1域: 位图
        next_bytes = new byte[8];
        msg.readBytes(next_bytes, 0, next_bytes.length);
        next_bytes_str = "";
        for (byte next_byte : next_bytes) {
            String s = Integer.toBinaryString(next_byte & 0xff);
            String ss = BKUtils.add0(s, 8, 1);
            next_bytes_str += ss;
        }
        valueMap.put("bitmap", next_bytes_str);

        Class clazz = this.getClass();
        int j = 1;
        while (j <= next_bytes_str.length()) {
            if (next_bytes_str.substring(j - 1, j).equals("1")) {
                Method domainX = null;
                try {
                    domainX = clazz.getDeclaredMethod("domain" + j);
                    if (domainX != null) {
                        domainX.invoke(this);
                    }
                } catch (NoSuchMethodException e1) {
                    logger.error("decode domain " + j + " error - NoSuchMethodException:" + e1);
                } catch (SecurityException e1) {
                    logger.error("decode domain " + j + " error - SecurityException:" + e1);
                } catch (InvocationTargetException e) {
                    logger.error("decode domain " + j + " error - InvocationTargetException:" + e);
                } catch (IllegalAccessException e) {
                    logger.error("decode domain " + j + " error - IllegalAccessException:" + e);
                } catch (Exception e) {
                    logger.error("decode domain " + j + " error - unknow Exception:" + e);
                }
            }
            j++;
        }
        logger.info("报文解析（64域） decode value map =" + valueMap);
    }
    
    
    /**
     * 2  主账号  n..19  LLVAR BCD
     *
     * @return
     */
    public void domain2() {
        // 1、先获取一位的长度 LL ，用于决定银行卡号的位数
        byte len = msg.readByte();
        String s = Integer.toHexString(len & 0xff);
        int l = Integer.parseInt(s);
        int bl = l / 2;
        if (l % 2 != 0) bl++; // byte 的位数 
        
        // 2、读取 byte 的位数，获取银行卡号
        byte[] bs = new byte[bl];
        msg.readBytes(bs, 0, bs.length);
        String rs = "";
        for (byte b : bs) {
            String ts = Integer.toHexString(b & 0xff);
            rs += ((ts.length() == 1) ? "0" + ts : ts);
        }
        if (l != bl * 2) {
            rs = rs.substring(0, rs.length() - 1);
        }
        valueMap.put("2", rs);
    }

    /**
     * 3  交易处理码  n6  BCD
     *
     * @return
     */
    public void domain3() {
        byte[] bs = new byte[3];
        msg.readBytes(bs, 0, bs.length);
        String rs = "";
        for (byte b : bs) {
            String ts = Integer.toHexString(b & 0xff);
            rs += ((ts.length() == 1) ? "0" + ts : ts);
        }
        valueMap.put("3", rs);
    }

    /**
     * 4	交易金额	N12		BCD
     */
    public void domain4() {
        byte[] bs = new byte[6];
        msg.readBytes(bs, 0, bs.length);
        StringBuffer rs = new StringBuffer();
        for (byte b : bs) {
            String ts = Integer.toHexString(b & 0xff);
            rs.append((ts.length() == 1) ? "0" + ts : ts);
        }
        valueMap.put("4", rs.toString());
    }

    /**
     * 11  受卡方系统跟踪号 n6  BCD  M  M  POS 终端交易流水号
     *
     * @return
     */
    public void domain11() {
        byte[] bs = new byte[3];
        msg.readBytes(bs, 0, bs.length);
        String rs = "";
        for (byte b : bs) {
            String ts = Integer.toHexString(b & 0xff);
            rs += ((ts.length() == 1) ? "0" + ts : ts);
        }
        valueMap.put("11", rs);
    }

    /**
     * 12  受卡方所在地时间 n6  hhmmss BCD  M
     *
     * @return
     */
    public void domain12() {
        byte[] bs = new byte[3];
        msg.readBytes(bs, 0, bs.length);
        String rs = "";
        for (byte b : bs) {
            String ts = Integer.toHexString(b & 0xff);
            rs += ((ts.length() == 1) ? "0" + ts : ts);
        }
        valueMap.put("12", rs);
    }

    /**
     * 13  受卡方所在地日期 n4  MMDD BCD  M
     *
     * @return
     */
    public void domain13() {
        byte[] bs = new byte[2];
        msg.readBytes(bs, 0, bs.length);
        String rs = "";
        for (byte b : bs) {
            String ts = Integer.toHexString(b & 0xff);
            rs += ((ts.length() == 1) ? "0" + ts : ts);
        }
        valueMap.put("13", rs);
    }

    /**
     * 14 卡有效期 n4 YYMM BCD C
     */
    public void domain14() {
        byte[] bs = new byte[2];
        msg.readBytes(bs, 0, bs.length);
        String rs = "";
        for (byte b : bs) {
            String ts = Integer.toHexString(b & 0xff);
            rs += ((ts.length() == 1) ? "0" + ts : ts);
        }
        valueMap.put("14", rs);
    }

    /**
     * 15	清算日期	n4	MMDD	BCD		M
     */
    public void domain15() {
        byte[] bs = new byte[2];
        msg.readBytes(bs, 0, bs.length);
        StringBuffer rs = new StringBuffer();
        for (byte b : bs) {
            String ts = Integer.toHexString(b & 0xff);
            rs.append((ts.length() == 1) ? "0" + ts : ts);
        }
        valueMap.put("15", rs.toString());
    }

    /**
     * 22  服务点输入方式码  N3 BCD
     */
    public void domain22() {
        byte[] bs = new byte[2];
        msg.readBytes(bs, 0, bs.length);
        StringBuffer rs = new StringBuffer();
        for (byte b : bs) {
            String ts = Integer.toHexString(b & 0xff);
            rs.append((ts.length() == 1) ? "0" + ts : ts);
        }
        valueMap.put("22", rs.toString());
    }

    /**
     * 23 卡片序列号 n3 BCD
     */
    public void domain23() {
        byte[] bs = new byte[2];
        msg.readBytes(bs, 0, bs.length);
        String rs = "";
        for (byte b : bs) {
            String ts = Integer.toHexString(b & 0xff);
            rs += ((ts.length() == 1) ? "0" + ts : ts);
        }
        valueMap.put("23", rs);
    }

    /**
     * 25  服务点条件码  n2  BCD  M  M  普通余额查询填写 00，联盟积分查询填写 65
     *
     * @return
     */
    public void domain25() {
        byte b = msg.readByte();
        String rs = Integer.toHexString(b & 0xff);
        rs = ((rs.length() == 1) ? "0" + rs : rs);
        valueMap.put("25", rs);
    }

    /**
     * 26 服务点PIN获取码 n2 BCD
     */
    public void domain26() {
        byte b = msg.readByte();
        String rs = Integer.toHexString(b & 0xff);
        rs = ((rs.length() == 1) ? "0" + rs : rs);
        valueMap.put("26", rs);
    }

    /**
     * 32  受理方标识码  n..11  LLVAR BCD  M
     *
     * @return
     */
    public void domain32() {
        // 1、先获取一位的长度 LL ，用于决定后续内容的长度的位数
        byte len = msg.readByte();
        String s = Integer.toHexString(len & 0xff);
        int l = Integer.parseInt(s);
        int bl = l / 2;
        if (l % 2 != 0) bl++; // byte 的位数 一定是偶数

        // 2、读取 byte 的位数，获取内容
        byte[] bs = new byte[bl];
        msg.readBytes(bs, 0, bs.length);
        String rs = "";
        for (byte b : bs) {
            String ts = Integer.toHexString(b & 0xff);
            rs += ((ts.length() == 1) ? "0" + ts : ts);
        }
        if (l != bl * 2) {
            rs = rs.substring(0, rs.length() - 2);
        }
        valueMap.put("32", rs);
    }

    /**
     * 35 2磁道数据 z..37 LLVAR BCD
     */
    public void domain35() {
        byte len = msg.readByte();
        String s = Integer.toHexString(len & 0xff);
        int l = Integer.parseInt(s);
        int bl = l / 2;
        if (l % 2 != 0) bl++; // byte 的位数 一定是偶数

        byte[] bs = new byte[bl];
        msg.readBytes(bs, 0, bs.length);
        String rs = "";
        for (byte b : bs) {
            String ts = Integer.toHexString(b & 0xff);
            rs += ((ts.length() == 1) ? "0" + ts : ts);
        }
        if (l != bl * 2) {
            rs = rs.substring(0, rs.length() - 2);
        }
        valueMap.put("35", rs);
    }

    /**
     * 36	3磁道数据	z...104	LLLVAR	BCD	C
     */
    public void domain36() {
        short len = msg.readShort();
        String s = Integer.toHexString(len & 0xffff);
        int l = Integer.parseInt(s);
        int bl = l / 2;
        if (l % 2 != 0) bl++; // byte 的位数 一定是偶数

        byte[] bs = new byte[bl];
        msg.readBytes(bs, 0, bs.length);
        String rs = "";
        for (byte b : bs) {
            String ts = Integer.toHexString(b & 0xff);
            rs += ((ts.length() == 1) ? "0" + ts : ts);
        }
        if (l != bl * 2) {
            rs = rs.substring(0, rs.length() - 2);
        }
        valueMap.put("36", rs);
    }

    /**
     * 37  检索参考号  an12  ASCII  M  POS 中心流水号
     *
     * @return
     */
    public void domain37() {
        byte[] bs = new byte[12];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("37", rs);
    }

    /**
     * 38	授权码	an6		ASCII
     */
    public void domain38() {
        byte[] bs = new byte[6];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("38", rs);
    }

    /**
     * 39  应答码  an2  ASCII  M
     *
     * @return
     */
    public void domain39() {
        byte[] bs = new byte[2];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("39", rs);
    }

    /**
     * 41  受卡机终端标识码 ans8  ASCII  M  M  POS 终端代码
     *
     * @return
     */
    public void domain41() {
        byte[] bs = new byte[8];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("41", rs);
    }

    /**
     * 42  受卡方标识码  ans15  ASCII  M  M  商户代码
     *
     * @return
     */
    public void domain42() {
        byte[] bs = new byte[15];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("42", rs);
    }

    /**
     * 44  附加响应数据  ans..25  LLVAR ASCII  M  接收/收单机构
     *
     * @return
     */
    public void domain44() {
        // 1、先获取一位的长度 LL ，用于决定银行卡号的位数
        byte len = msg.readByte();
        String s = Integer.toHexString(len & 0xff);
        int l = Integer.parseInt(s);

        byte[] bs = new byte[l];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("44", rs);

    }

    /**
     * 49  交易货币代码  an3  ASCII  M  M
     *
     * @return
     */
    public void domain49() {
        byte[] bs = new byte[3];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("49", Integer.parseInt(rs) + "");
    }

    /**
     * 52 个人标识码数据 B64  BINARY, 也就是密码
     */
    public void domain52() {
        msg.readInt();
        msg.readInt();
    }

    /**
     * 53  安全控制信息  n16  BCD  C  C  有安全要求或磁条信息出现时必选
     *
     * @return
     */
    public void domain53() {
        byte[] bs = new byte[8];
        msg.readBytes(bs, 0, bs.length);
        String rs = "";
        for (byte b : bs) {
            String ts = Integer.toHexString(b & 0xff);
            rs += ((ts.length() == 1) ? "0" + ts : ts);
        }
        valueMap.put("53", rs);

    }

    /**
     * 54  附加金额  an…020  LLLVAR ASCII  C  39 域为“00”时必选
     *
     * @return
     */
    public void domain54() {
        // 扔掉前2个字节(长度)
        msg.readByte();
        msg.readByte();

        // 扔掉前8个字节
        msg.readInt();
        msg.readInt();

        byte[] bs = new byte[12];
        msg.readBytes(bs, 0, bs.length);
        valueMap.put("54", Integer.parseInt(new String(bs)) + "");

    }

    /**
     * 55  IC卡数据域,最大255 字节数据  LLLVAR  包含多个子域
     */
    public void domain55() {
        short len = msg.readShort();
        String s = Integer.toHexString(len & 0xffff);
        int l = Integer.parseInt(s);
//        int bl = l / 2;
//        if (l % 2 != 0) bl++; // byte 的位数 一定是偶数

        // 2、读取 byte 的位数
        byte[] bs = new byte[l];
        msg.readBytes(bs, 0, bs.length);
        String patchNo = "";
        for (int i = 1; i <= 3; i++) {
            String ts = Integer.toHexString(bs[i] & 0xff);
            patchNo += ((ts.length() == 1) ? "0" + ts : ts);
        }
        valueMap.put("55", patchNo);
    }

    /**
     * 60  自定义域  n…011  LLLVAR BCD  M
     * 处理逻辑：
     * 1、当签到交易时需要获取批次号
     *
     * @return
     */
    public void domain60() {
        // 1、先获取2位的长度 LLL ，用于决定后续内容的位数
        short len = msg.readShort();
        String s = Integer.toHexString(len & 0xffff);
        int l = Integer.parseInt(s);
        int bl = l / 2;
        if (l % 2 != 0) bl++; // byte 的位数 一定是偶数

        // 2、读取 byte 的位数
        byte[] bs = new byte[bl];
        msg.readBytes(bs, 0, bs.length);
        String patchNo = "";
        for (int i = 1; i <= 3; i++) {
            String ts = Integer.toHexString(bs[i] & 0xff);
            patchNo += ((ts.length() == 1) ? "0" + ts : ts);
        }
        valueMap.put("60.2", patchNo);
    }

    /**
     * 61	原始信息域	n…029	LLLVAR	BCD	C		末笔查询使用
     * 61.1  原批次号    61.2  原终端流水号
     */
    public void domain61() throws Exception{
        // 1、先获取2位的长度 LLL ，用于决定后续内容的位数
        short len = msg.readShort();
        String s = Integer.toHexString(len & 0xffff);
        int l = Integer.parseInt(s);
        int bl = l / 2;
        if (l % 2 != 0) bl++; // byte 的位数 一定是偶数

        // 2、读取 byte 的位数
        byte[] bs = new byte[bl];
        msg.readBytes(bs, 0, bs.length);
        String patchNo = "";
        for (int i = 0; i < 3; i++) {
            String ts = Integer.toHexString(bs[i] & 0xff);
            patchNo += ((ts.length() == 1) ? "0" + ts : ts);
        }
        valueMap.put("61.1", patchNo);
        String seqZd = "";
        for (int i = 3; i < 6; i++) {
            String ts = Integer.toHexString(bs[i] & 0xff);
            seqZd += ((ts.length() == 1) ? "0" + ts : ts);
        }
        valueMap.put("61.2", seqZd);
    }

    /**
     * 62 终端信息/终端密钥 b...084 LLLVAR BINARY
     * 处理逻辑：
     * 1、当签到交易时需要获取工作密钥，以 ; 分割， pin;mak;tdk
     */
    public void domain62() {
        if ("0810".equalsIgnoreCase(valueMap.get("msgtype"))) { // 签到交易 返回的 消息码
            msg.readShort(); // 读取前两位，扔掉
            // pin 20byte ,取前16个
            byte[] bs = new byte[16];
            msg.readBytes(bs, 0, bs.length);
            String pin = "";
            for (int i = 0; i < 16; i++) {
                String ts = Integer.toHexString(bs[i] & 0xff);
                pin += ((ts.length() == 1) ? "0" + ts : ts);
            }
            msg.readInt();// 扔掉 4个字节

            // mak 20byte ,取前8个
            bs = new byte[8];
            msg.readBytes(bs, 0, bs.length);
            String mak = "";
            for (int i = 0; i < 8; i++) {
                String ts = Integer.toHexString(bs[i] & 0xff);
                mak += ((ts.length() == 1) ? "0" + ts : ts);
            }
            msg.readLong();// 扔掉 8个字节
            msg.readInt();// 再扔掉 4个字节

            // tdk 20byte ,取前8个
            bs = new byte[16];
            msg.readBytes(bs, 0, bs.length);
            String tdk = "";
            for (int i = 0; i < 16; i++) {
                String ts = Integer.toHexString(bs[i] & 0xff);
                tdk += ((ts.length() == 1) ? "0" + ts : ts);
            }
            msg.readInt();// 扔掉 4个字节

            valueMap.put("62", pin + ";" + mak + ";" + tdk);
        } else
            valueMap.put("62", "");
    }

    /**
     * 63	自定义域	ans...063	LLLVAR	ASCII		M
     */
    public void domain63() {
        short len = msg.readShort();
        String s = Integer.toHexString(len & 0xffff);
        int l = Integer.parseInt(s);

        // 2、读取 byte 的位数
        byte[] bs = new byte[l];
        msg.readBytes(bs, 0, bs.length);
        String patchNo = "";
        for (int i = 0; i <= len; i++) {
            String ts = Integer.toHexString(bs[i] & 0xff);
            patchNo += ((ts.length() == 1) ? "0" + ts : ts);
        }
        valueMap.put("63", patchNo);
    }

    /**
     * 64  MAC  B64  BINARY M  C  响应消息中 39 域为“00”时必选
     *
     * @return
     */
    public void domain64() {
        valueMap.put("64", "");

    }
    public static void main(String[] args) {
        String bodystr="002f999999999999999999999908000020000000c000000000943031303236303531313032343430313630353136303530";
        byte[] reqs = BKUtils.str2Bcd(bodystr);
        Map<String, String> valueMap = new HashMap<String, String>();
        ChannelBuffer msg = ChannelBuffers.directBuffer(reqs.length - 2);
        msg.writeBytes(reqs, 2, reqs.length - 2);
        CCBBankPkgDecoder decoder = new CCBBankPkgDecoder();
        decoder.decode(msg, valueMap);
        System.out.println(valueMap);
        System.out.println("==============解析完成=============");
    }
}
