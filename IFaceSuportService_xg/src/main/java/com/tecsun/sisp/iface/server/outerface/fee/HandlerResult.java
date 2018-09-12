package com.tecsun.sisp.iface.server.outerface.fee;

/**
 * result 编码: 200-成功; 300-超时; 999-其他异常
 * Created by Ivan on 15-6-19.
 */
public class HandlerResult {
    // 是否处理完成
    public boolean processOver = false;
    // 结果编码
    public int result = 200;
    // 结果描述
    public String message = "";
    // 报文字符串
    public String bodyStr = "";

}
