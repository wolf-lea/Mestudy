package com.tecsun.sisp.iface.server.outerface.fee;

import com.tecsun.sisp.iface.common.vo.PayApplyBean;
import com.tecsun.sisp.iface.common.vo.PayApplyDetailBean;
import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

import java.io.UnsupportedEncodingException;

/**
 * 银行返回报文处理器
 * Created by Ivan on 15-6-19.
 */
public class FeeMsgHandler extends SimpleChannelHandler {
    public static final Logger logger = Logger.getLogger(FeeMsgHandler.class);

    int bodyLen = 0;
    boolean isGetBodyLen = false;
    private ChannelBuffer cacheBuffer = ChannelBuffers.dynamicBuffer();

    byte[] reqBs;
    HandlerResult result;

    public FeeMsgHandler(byte[] reqBs, HandlerResult result) {
        this.reqBs = reqBs;
        this.result = result;
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        Channel c = ctx.getChannel();
        ChannelBuffer bf = ChannelBuffers.dynamicBuffer(reqBs.length);
        bf.writeBytes(reqBs);
        c.write(bf);
        logger.debug(">>>>>>>>> send to bank bytes[" + reqBs.length + "]:" + new String(reqBs, "GBK"));
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        ChannelBuffer msg = (ChannelBuffer) e.getMessage();

        // 合并字节
        int messageSize = msg.readableBytes() + cacheBuffer.readableBytes();
        ChannelBuffer combinedBf = ChannelBuffers.dynamicBuffer(messageSize);
        cacheBuffer.readBytes(combinedBf, cacheBuffer.readableBytes());
        msg.readBytes(combinedBf, msg.readableBytes());
        cacheBuffer = combinedBf;

        // 1、判断是否已经读取到 报文长度
        if (!isGetBodyLen) {
            if (cacheBuffer.readableBytes() >= 4) {
                bodyLen = cacheBuffer.readInt();
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
        result.message = "unknow exception:" + e.getCause().getMessage();
        try {
            e.getChannel().disconnect();
            e.getChannel().close();
        } catch (Exception e1) {
            logger.error("Channel close error!!!!", e1);
        }
    }

    private void processAllBody(ChannelBuffer msg, Channel channel) throws UnsupportedEncodingException {
        byte[] bs = new byte[bodyLen];
        msg.readBytes(bs, 0, bs.length);
        result.bodyStr = new String(bs, "GBK");
        String body = result.bodyStr;
        //申报查询 9021
        if(body.substring(54, 60).equals("9021")){
        	getPayApplyBean(body);
        }
        
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

    public Object getPayApplyBean(String body){
        PayApplyBean bean = new PayApplyBean();
        String statuCode = body.substring(0, 4);
    	//交易成功
        if(statuCode.equals("0000")){
        	bean.setDesc(body.substring(4, 54));
        	bean.setDealCode(body.substring(54, 60));
        	bean.setRegistNo(body.substring(60, 80));
        	bean.setCernum(body.substring(80, 100));
        	bean.setName(body.substring(100, 120));
            bean.setDealDate(body.substring(120, 134));
        	bean.setBankSeq(body.substring(134, 154));
        	bean.setBankNetNo(body.substring(154, 174));
            bean.setBankAcceptNo(body.substring(174, 194));
            bean.setBankTailNo(body.substring(194, 214));
            bean.setEaraCode1(body.substring(214, 234));
            bean.setFee1(body.substring(234, 236));
            bean.setApplyData(body.substring(236, 239));
        	int i = Integer.parseInt(body.substring(236, 239).trim());
            String detail = body.substring(239, body.length());  //申报明细
            PayApplyDetailBean[] beanArray =new PayApplyDetailBean[i];
            int k = 1;
            for(int j=0;j<i;j++){
            	beanArray[i].setStartDate(detail.substring(k, (j+1)*6+1));
            	k=(j+1)*6+1;
            }	
            for(int j=0;j<i;j++){
            	beanArray[i].setEndDate(detail.substring(k, k+(j+1)*6));
            	k=(j+1)*6;
            }
            for(int j=0;j<i;j++){
            	beanArray[i].setApplyClass(detail.substring(k, k+(j+1)*10));
            	k=(j+1)*10;
            }
            for(int j=0;j<i;j++){
            	beanArray[i].setApplyDate(detail.substring(k, (j+1)*8));
            	k=(j+1)*8;
            }
            for(int j=0;j<i;j++){
            	beanArray[i].setMoney(detail.substring(k, (j+1)*14));
            	k=(j+1)*14;
            }
            for(int j=0;j<i;j++){
            	beanArray[i].setRate(detail.substring(k, (j+1)*5));
            	k=(j+1)*5;
            }
            for(int j=0;j<i;j++){
            	beanArray[i].setFeeType(detail.substring(k, (j+1)*3));
            	k=(j+1)*3;
            }
            for(int j=0;j<i;j++){
            	beanArray[i].setFee2(detail.substring(k, (j+1)*10));
            	k=(j+1)*10;
            }
            for(int j=0;j<i;j++){
            	beanArray[i].setBudgetSubject(detail.substring(k, (j+1)*10));
            	k=(j+1)*10;
            }
            for(int j=0;j<i;j++){
            	beanArray[i].setOrg(detail.substring(k, (j+1)*6));
            	k=(j+1)*6;
            }
            for(int j=0;j<i;j++){
            	beanArray[i].setEaraCode2(detail.substring(k, (j+1)*20));
            	k=(j+1)*20;
            }
            bean.setBeans(beanArray);
          }else{
        	  bean.setStatuCode("0000");
          }
       return bean;
    }
}
