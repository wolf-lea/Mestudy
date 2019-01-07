package com.tecsun.sisp.adapter.modules.finance.bankUtils.handler;


import com.tecsun.sisp.adapter.modules.finance.bankUtils.BKUtils;
import com.tecsun.sisp.adapter.modules.finance.bankUtils.HandlerResult;
import com.tecsun.sisp.adapter.modules.finance.bankUtils.decoder.CUPBankPkgDecoder;
import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

/**
 * 银行返回报文处理器 :128域
 * Created by Ivan on 15-6-19.
 */
public class CUPBankPkgDecodeHandler extends SimpleChannelHandler {
    public static final Logger logger = Logger.getLogger(CUPBankPkgDecodeHandler.class);

    // 报文定义的 报文长度字节数
    private static final Integer BODYLENBYTESC = 4;
    int bodyLen = 0;
    boolean isGetBodyLen = false;
    public String hexStr = "";
    private ChannelBuffer cacheBuffer = ChannelBuffers.dynamicBuffer();

    byte[] reqBs;
    HandlerResult result;

    public CUPBankPkgDecodeHandler(byte[] reqBs, HandlerResult result) {
        this.reqBs = reqBs;
        this.result = result;
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        Channel c = ctx.getChannel();
        ChannelBuffer bf = ChannelBuffers.dynamicBuffer(reqBs.length);
        bf.writeBytes(reqBs);
        c.write(bf);
        logger.info(">>>>>>>>> send to bank bytes[" + reqBs.length + "]:" + BKUtils.bytes_to_hexStr(reqBs));
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        ChannelBuffer msg = (ChannelBuffer) e.getMessage();
        logger.info(">>>>>>>>>>>> 接收到银行（128域）的响应报文，RCC-Response byteSize:" + msg.readableBytes());

        // 合并字节
        int messageSize = msg.readableBytes() + cacheBuffer.readableBytes();
        ChannelBuffer combinedBf = ChannelBuffers.dynamicBuffer(messageSize);
        cacheBuffer.readBytes(combinedBf, cacheBuffer.readableBytes());
        msg.readBytes(combinedBf, msg.readableBytes());
        cacheBuffer = combinedBf;
        // 1、判断是否已经读取到 报文长度
        if (!isGetBodyLen) {
            if (cacheBuffer.readableBytes() >= BODYLENBYTESC) {
                byte[] bs = new byte[BODYLENBYTESC];
                cacheBuffer.readBytes(bs, 0, bs.length);
                hexStr=BKUtils.bytes_to_hexStr(bs);
                bodyLen = Integer.parseInt(new String(bs));
                isGetBodyLen = true;
                // 如果报文长度的字节数已经读取完毕，则处理
                if (cacheBuffer.readableBytes() >= bodyLen) {
                    processAllBody(cacheBuffer, e.getChannel());
                }
            }
        } else if (cacheBuffer.readableBytes() < bodyLen) {// 包还没完全返回
        } else {
            processAllBody(cacheBuffer, e.getChannel());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        logger.error("exceptionCaught: ", e.getCause());

        result.processOver = true;
        result.result = 999;
        result.message = "999: unknow exception:" + e.getCause().getMessage();
        try {
            e.getChannel().disconnect();
            e.getChannel().close();
        } catch (Exception e1) {
            logger.error("Channel close error!!!!", e1);
        }
    }

    private void processAllBody(ChannelBuffer msg, Channel channel) {
        byte[] bs = new byte[bodyLen];
        msg.getBytes(msg.readerIndex(), bs, 0, bs.length);
        result.bodyHexStr = BKUtils.bytes_to_hexStr(bs);
        hexStr+= result.bodyHexStr;
        result.hexStr=hexStr;
        logger.info("********* 响应报文（128域）bank return bytes HexStr=" +hexStr);
        result.valueMap.put("totalLen", bodyLen + "");

        // 交易报文解析器
        CUPBankPkgDecoder decoder = new CUPBankPkgDecoder();
        decoder.decode(msg, result.valueMap);
        // 处理结束
        result.processOver = true;
        // 处理成功
        result.result = 200;
        // 关闭连接
        try {
            if (channel != null) {
                channel.disconnect();
                channel.close();
            }
        } catch (Exception e) {
            logger.error("channel.close error " + e);
        }
    }

}