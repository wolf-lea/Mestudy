package com.tecsun.sisp.adapter.modules.finance.bankUtils;

import java.util.HashMap;
import java.util.Map;

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
    // 解析出来的每个域的值，key:为域的编号,value:为域的值，以字符串存储
    public Map<String, String> valueMap = new HashMap<String, String>();
    // 报文（不包含前2byte）的 16进制字符串
    public String bodyHexStr = "";
//     银行完整报文
    public String hexStr = "";
    //备用
    public Long id = 0l;

}
