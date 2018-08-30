package com.tecsun.sisp.adapter.modules.finance.bankUtils.decoder;

import com.tecsun.sisp.adapter.modules.finance.bankUtils.BKUtils;
import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 此类 返回报文解析:128域
 */
public class CUPBankPkgDecoder {
    public static final Logger logger = Logger.getLogger(CUPBankPkgDecoder.class);

    private Map<String, String> valueMap;
    private ChannelBuffer msg;

    public CUPBankPkgDecoder() {
    }

    /**
     * 解包
     *
     * @param msg      已经去掉 2byte 的长度
     * @param valueMap 结果集
     */
    public void decode(ChannelBuffer msg, Map<String, String> valueMap) {
        this.msg = msg;
        this.valueMap = valueMap;

        // 以下是 8583 报文解析
        // 0域: 消息类型
        byte[] bs = new byte[4];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("msgtype", rs);

        // 1域: 位图
        byte[] next_bytes = new byte[16];
        msg.readBytes(next_bytes, 0, next_bytes.length);
        String next_bytes_str = "";
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
        logger.info("报文解析（128域）decode value map =" + valueMap);
    }

    /**
     * 域标识码(无需实现)，为1说明此返回报文是128域，为0是64域。这里是1
     */
    public void domain1() {

    }

    /**
     * 2  主账号  n..19  LLVAR BCD
     *
     * @return
     */
    public void domain2() {
        // 1、先获取LLL位的长度
        byte[] len = new byte[2];
        msg.readBytes(len, 0, len.length);
        String s = new String(len);
        int ls = Integer.parseInt(s);
        //2、根据长度获取数据
        byte[] bs = new byte[ls];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("2", rs);
    }

    /**
     * 3  交易处理码  n6  BCD
     *
     * @return
     */
    public void domain3() {
        byte[] bs = new byte[6];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("3", rs);
    }

    /**
     * 4	交易金额	N12		BCD
     */
    public void domain4() {
        byte[] bs = new byte[12];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("4", rs.toString());
    }

   /**
     * 7	交易日期时间	n10		BCD
     */
    public void domain7() {
        byte[] bs = new byte[10];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("7", rs.toString());
    }

    /**
     * 11  流水号 n6     M  M
     *
     * @return
     */
    public void domain11() {
        byte[] bs = new byte[6];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("11", rs);
    }

    /**
     * 12  传输时间 n6  hhmmss    M
     *
     * @return
     */
    public void domain12() {
        byte[] bs = new byte[6];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("12", rs);
    }

    /**
     * 13  传输日期	n4	M	M
     *
     * @return
     */
    public void domain13() {
        byte[] bs = new byte[4];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("13", rs);
    }

    /**
     * 14 有效期	n4		C	YYMM
     */
    public void domain14() {
        byte[] bs = new byte[4];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("14", rs);
    }

    /**
     * 15	清算日期	n4		M
     */
    public void domain15() {
        byte[] bs = new byte[4];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("15", rs.toString());
    }

    /**
     * 18	商户类型	n4	M	M
     */
    public void domain18() {
        byte[] bs = new byte[4];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("18", rs.toString());
    }

    /**
     * 22  输入方式	n3	M
     */
    public void domain22() {
        byte[] bs = new byte[3];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("22", rs.toString());
    }

    /**
     * 23	卡片序列号	n3	C	C	C:当POS能够获得该值时存在
     *
     * @return
     */
    public void domain23() {
        byte[] bs = new byte[3];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("23", rs);
    }

    /**
     * 25  服务条件码	n2	M	M
     *
     * @return
     */
    public void domain25() {
        byte[] bs = new byte[2];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("25", rs);
    }

    /**
     * 26 服务点PIN获取码	n2	C
     */
    public void domain26() {
        byte[] bs = new byte[2];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("26", rs);
    }

    /**
     * 32  代理机构标识码	n..11(LLVAR)	M	M
     *
     * @return
     */
    public void domain32() {
        // 1、先获取LLL位的长度
        byte[] len = new byte[2];
        msg.readBytes(len, 0, len.length);
        String s = new String(len);
        int ls = Integer.parseInt(s);
        //2、根据长度获取数据
        byte[] bs = new byte[ls];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("32", rs);
    }

    /**
     * 33  发送机构标识码	n..11(LLVAR)	M	M
     *
     * @return
     */
    public void domain33() {
        // 1、先获取LLL位的长度
        byte[] len = new byte[2];
        msg.readBytes(len, 0, len.length);
        String s = new String(len);
        int ls = Integer.parseInt(s);
        //2、根据长度获取数据
        byte[] bs = new byte[ls];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("33", rs);
    }

    /**
     * 35 二磁信息	z..37(LLVAR)	C1
     */
    public void domain35() {
        // 1、先获取LLL位的长度
        byte[] len = new byte[2];
        msg.readBytes(len, 0, len.length);
        String s = new String(len);
        int ls = Integer.parseInt(s);
        //2、根据长度获取数据
        byte[] bs = new byte[ls];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("35", rs);
    }

    /**
     * 36	三磁信息	z...104(LLLVAR)	C2
     */
    public void domain36() {
        // 1、先获取LLL位的长度
        byte[] len = new byte[3];
        msg.readBytes(len, 0, len.length);
        String s = new String(len);
        int ls = Integer.parseInt(s);
        //2、根据长度获取数据
        byte[] bs = new byte[ls];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("36", rs);
    }

    /**
     * 37  系统参考号	an12		M
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
     * 38	授权码	an6		O
     */
    public void domain38() {
        byte[] bs = new byte[6];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("38", rs);
    }

    /**
     * 39  响应码	an2		M
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
     * 41  终端号	ans8	M	M
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
     * 42  商户号	ans15	M	M
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
     * 43  商户名称	ans40		M
     *
     * @return
     */
    public void domain43() {
        byte[] bs = new byte[40];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("43", rs);
    }

    /**
     * 44  附加响应数据	ans..25(LLVAR)		O
     *
     * @return
     */
    public void domain44() {
        // 1、先获取LLL位的长度
        byte[] len = new byte[2];
        msg.readBytes(len, 0, len.length);
        String s = new String(len);
        int ls = Integer.parseInt(s);
        //2、根据长度获取数据
        byte[] bs = new byte[ls];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("44", rs);
    }

    /**
     * 48  附加数据	Ans…800(LLLVAR)	M	C
     *
     * @return
     */
    public void domain48() {
        // 1、先获取LLL位的长度
        byte[] len = new byte[3];
        msg.readBytes(len, 0, len.length);
        String s = new String(len);
        int ls = Integer.parseInt(s);
        //2、根据长度获取数据
        byte[] bs = new byte[ls];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("48", rs);
    }

    /**
     * 49  货币代码	an3	M	M
     *
     * @return
     */
    public void domain49() {
        byte[] bs = new byte[3];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("49", rs);
    }

    /**
     * 52 个人标识码	b64	O
     */
    public void domain52() {
        byte[] bs = new byte[8];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("52", rs);
    }

    /**
     * 53  安全控制信息	n16	O
     *
     * @return
     */
    public void domain53() {
        byte[] bs = new byte[16];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("53", rs);
    }

    /**
     * 54  实际余额	an...040(LLLVAR)		M
     *
     * @return
     */
    public void domain54() {
        // 1、先获取LLL位的长度
        byte[] len = new byte[3];
        msg.readBytes(len, 0, len.length);
        String s = new String(len);
        int ls = Integer.parseInt(s);
        //2、根据长度获取数据
        byte[] bs = new byte[ls];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("54", rs);
    }

    /**
     * 55	IC卡数据域	LLLVAR	C	C	当该交易为IC卡交易时必选（具体子域信息参考银联规范）
     *
     * @return
     */
    public void domain55() {
        // 1、先获取LLL位的长度
        byte[] len = new byte[3];
        msg.readBytes(len, 0, len.length);
        String s = new String(len);
        int ls = Integer.parseInt(s);
        //2、根据长度获取数据
        byte[] bs = new byte[ls];
        msg.readBytes(bs, 0, bs.length);
        StringBuffer rs = new StringBuffer();
        for (byte b : bs) {
            String ts = Integer.toHexString(b & 0xff);
            rs.append((ts.length() == 1) ? "0" + ts : ts);
        }
        valueMap.put("55", rs.toString());
    }

    /**
     * 60	自定义域	n…011	M	M
     *
     * @return
     */
    public void domain60() {
        // 1、先获取LLL位的长度
        byte[] len = new byte[3];
        msg.readBytes(len, 0, len.length);
        String s = new String(len);
        int ls = Integer.parseInt(s);
        //2、根据长度获取数据
        byte[] bs = new byte[ls];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("60", rs);
        valueMap.put("60.1", rs.substring(0, 2));
        valueMap.put("60.2", rs.substring(2,8));
    }

    /**
     * 90	原始数据元	n42	M		原交易信息：消息类型4+流水号6+交易日期时间10+代理机构标识码11（右对齐左补0）+发送机构标识码（右对齐左补0）
     *
     * @return
     */
    public void domain90() {
        byte[] bs = new byte[42];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("90", rs);
    }

    /**
     * 100	接收机构标识码	n..11(LLVAR)		M
     *
     * @return
     */
    public void domain100() {
        // 1、先获取一位的长度 LL
        byte[] len = new byte[2];
        msg.readBytes(len, 0, len.length);
        String s = new String(len);
        int ls = Integer.parseInt(s);
        //2、根据长度获取数据
        byte[] bs = new byte[ls];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("100", rs);
    }

    /**
     * 121	增值平台保留	ans...030(LLLVAR)		C
     *
     * @return
     */
    public void domain121() {
        // 1、先获取一位的长度 LLL
        byte[] len = new byte[3];
        msg.readBytes(len, 0, len.length);
        String s = new String(len);
        int ls = Integer.parseInt(s);
        //2、根据长度获取数据
        byte[] bs = new byte[ls];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("121", rs);
    }

    /**
     * 122	受理方保留	ans...030(LLLVAR)	O
     *
     * @return
     */
    public void domain122() {
        // 1、先获取一位的长度 LLL
        byte[] len = new byte[3];
        msg.readBytes(len, 0, len.length);
        String s = new String(len);
        int ls = Integer.parseInt(s);
        //2、根据长度获取数据
        byte[] bs = new byte[ls];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("122", rs);
    }

    /**
     * 123	发卡方保留	ans...030(LLLVAR)		O
     *
     * @return
     */
    public void domain123() {
        // 1、先获取一位的长度 LLL
        byte[] len = new byte[3];
        msg.readBytes(len, 0, len.length);
        String s = new String(len);
        int ls = Integer.parseInt(s);
        //2、根据长度获取数据
        byte[] bs = new byte[ls];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("123", rs);
    }

    /**
     * 128	报文鉴别码	b64	C9	C9
     *
     * @return
     */
    public void domain128() {
        byte[] bs = new byte[8];
        msg.readBytes(bs, 0, bs.length);
        String rs = new String(bs);
        valueMap.put("128", rs);
    }
}