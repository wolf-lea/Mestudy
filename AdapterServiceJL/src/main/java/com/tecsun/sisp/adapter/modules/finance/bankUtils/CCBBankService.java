package com.tecsun.sisp.adapter.modules.finance.bankUtils;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.tecsun.sisp.adapter.modules.finance.bankUtils.handler.CCBBankPkgDecodeHandler;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.apache.log4j.Logger;

/**
 * 此类用于 建行 的请求交易 (64域报文)
 * copy CUP 2016-05-12
 */
public class CCBBankService {
    public static final Logger logger = Logger.getLogger(CCBBankService.class);

    private String ip = "";
    private int port;
    private int timeOut = 80; //单位 : 秒

    ChannelFactory factory = null;
    ClientBootstrap bootstrap = null;
    HandlerResult result = new HandlerResult();

    public CCBBankService(String ip, int port, int timeOut) {
        this.ip = ip;
        this.port = port;
        this.timeOut = timeOut;
    }

    /**
     * 向银行发起请求，并等待响应结果
     *
     * @param reqBs 字节数组
     * @return
     */
    public HandlerResult sendReq(final byte[] reqBs) {
        factory = new NioClientSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool());
        bootstrap = new ClientBootstrap(factory);
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() {
                return Channels.pipeline(new CCBBankPkgDecodeHandler(reqBs, result));
            }
        });
        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);
        bootstrap.connect(new InetSocketAddress(ip, port));

        // 等待响应结果
        long useTime = 0;
        long step = 50;// 时间步长 毫秒
        while (useTime < timeOut * 1000) {
            if (!result.processOver) { // 尚未处理结束，则继续等待
                useTime += step;
                try {
                    Thread.sleep(step);
                } catch (InterruptedException e) {
                    logger.error(" InterruptedException ", e);
                }
            } else {// 已经处理结束
                try {
                    if (factory != null) factory.releaseExternalResources();
                    if (bootstrap != null) bootstrap.shutdown();
                } catch (Exception e) {
                    logger.error(" bootstrap.shutdown error ", e);
                }
//                logger.info("response bodystr: "+result.bodyHexStr);
//                logger.info("response valueMap: "+result.valueMap);
                return result;
            }
        }

        try {
            if (factory != null) factory.releaseExternalResources();
            if (bootstrap != null) bootstrap.shutdown();
        } catch (Exception e) {
            logger.error(" bootstrap.shutdown error ", e);
        }
        // 如果没有在 while 循环里成功返回，则算是超时
        result.result = 300;
        result.message = "交易超时";
        logger.info(result);
        return result;
    }

    /**
     * 向银行发起请求，并等待响应结果
     *
     * @param hexStr 16进制字符串
     * @return
     */
    public HandlerResult sendReq4Hex(String hexStr) {
        return this.sendReq(BKUtils.str2Bcd(hexStr));
    }

    /**
     * 地税缴费业务，统一签到处理
     *
     * @param seq 序列号
     * @param zdh 终端号
     * @param shh 商户号
     * @return
     */
    public HandlerResult signOnFee(int seq, String zdh, String shh) {
        String pakg_hex_len;

        //  十六进制 字符串，不包含 ox ，此字符串在提交给银行前需要 进行转换为 十六进制字节组
        // 举例： 假设最后提交给的是 0x9a 0x9b 0x9c ,　则此处的 pakg_hex_str = '9a9b9c'
        StringBuffer pakg_hex_str = new StringBuffer();

        // step-1 、 TPDU 固定值
        pakg_hex_str.append("6000680008"); // 即 0x60 0x00 0x18 0x00 0x01

        // step-2 、版本号 固定值
        pakg_hex_str.append("613100990002");

        // step-3   、以下是 8583 报文体
        // step-3.1 、0域-消息类型 0200
        StringBuffer pakg_hex_str_8583 = new StringBuffer("0800");

        // step-3.2 、1域-位图，64位 = 8个字节,位图第一位是0，从第二位开始根据 银联报文协议定义，有值则设置为1 无值则为0
        String[] bitm = {
                "00000000",    //(1~8)
                "00100000",    //(9~16)  11 受卡方系统跟踪号 N6 BCD
                "00000000",    //(17~24)
                "00000000",    //(25~32)
                "00000000",    //(33~40)
                "11000000",    //(41~48) 41 受卡机终端标识码 ans8 ASCII  ,   42 受卡方标识码 ans15 ASCII
                "00000000",    //(49~56)
                "00010010"     //(57~64) 60 自定义域 n…011 LLLVAR BCD ,    63 自定义域 ans...003 LLLVAR ASCII
        };
        pakg_hex_str_8583.append("0020000000c00012");

        // step-3.3 、11 受卡方系统跟踪号 N6 BCD
        pakg_hex_str_8583.append(BKUtils.add0(seq + "", 6, 1));

        // step-3.4 、41 受卡机终端标识码 ans8 ASCII
        pakg_hex_str_8583.append(BKUtils.ascStrToHexStr(BKUtils.add0(zdh, 8, 2)));

        // step-3.5 、42 受卡方标识码 ans15 ASCII
        pakg_hex_str_8583.append(BKUtils.ascStrToHexStr(BKUtils.add0(shh, 15, 2)));

        // step-3.5 、60 自定义域 n…011 LLLVAR BCD,可以设为固定值
        pakg_hex_str_8583.append("0012000008200040");

        // step-3.5 、63 自定义域 ans...003 LLLVAR ASCII
        pakg_hex_str_8583.append("0003303120");


        // 拼装整个报文体
        pakg_hex_str.append(pakg_hex_str_8583);
        pakg_hex_len = Integer.toHexString(pakg_hex_str.length() / 2);

        return this.sendReq4Hex(BKUtils.add0(pakg_hex_len + "", 4, 1) + pakg_hex_str);
    }

}
