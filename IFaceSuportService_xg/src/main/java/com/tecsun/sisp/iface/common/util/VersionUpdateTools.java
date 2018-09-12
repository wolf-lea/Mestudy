package com.tecsun.sisp.iface.common.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by zhongzhiyong on 16-1-7.
 */
public class VersionUpdateTools {
    public static final Logger logger = Logger.getLogger(VersionUpdateTools.class);
    private static int update_total = 0;//当前更新数
    private static int threshold = 0;//延时阀值

    static {
        String thresholdStr = StringUtils.defaultIfBlank(Config.getInstance().get("update_threshold"), "0");
        threshold = Integer.valueOf(thresholdStr);//延时阀值
    }

    /**
     * 获取最新可更新时间
     * @return
     */
    public static Date getDownloadTime() {
        Calendar calendar = Calendar.getInstance();
        try {
            String updateTotalStr = JedisUtil.getValue(Constants.KEY_VERSION_UPDATE_TOTAL);
            if (StringUtils.isBlank(updateTotalStr)) {
                update_total ++;
                JedisUtil.setValue(Constants.KEY_VERSION_UPDATE_TOTAL, String.valueOf(update_total));
            }
            else {
                update_total = Integer.valueOf(updateTotalStr);
                update_total ++;
                JedisUtil.setValue(Constants.KEY_VERSION_UPDATE_TOTAL, String.valueOf(update_total));
            }

            //判断当前待更新数大于延时阀值的时候,计算出更新时间
            if (update_total > threshold) {
                calendar.add(Calendar.SECOND, (update_total-threshold)*2); //每超出阀值一个延时2秒
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        return calendar.getTime();
    }

    /**
     * 减去一个待更新总数
     */
    public static void minusTotal() {
        try {
            String updateTotalStr = JedisUtil.getValue(Constants.KEY_VERSION_UPDATE_TOTAL);
            if (StringUtils.isBlank(updateTotalStr)) {
                update_total --;
                JedisUtil.setValue(Constants.KEY_VERSION_UPDATE_TOTAL, String.valueOf(update_total));
            }
            else {
                update_total = Integer.valueOf(updateTotalStr);
                update_total --;
                JedisUtil.setValue(Constants.KEY_VERSION_UPDATE_TOTAL, String.valueOf(update_total));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
    }


    public static void main(String[] args) throws Exception {
        System.out.println("当前时间:" + DateFormatUtils.format(new Date()));
        System.out.println("请输入当前总数:");
        Scanner scanner = new Scanner(System.in);
        update_total = scanner.nextInt();
        Date date = getDownloadTime();
        System.out.println(DateFormatUtils.format(date));

        System.out.println("当前待更新数：" + JedisUtil.getValue(Constants.KEY_VERSION_UPDATE_TOTAL));
        minusTotal();
        System.out.println("当前待更新数：" + JedisUtil.getValue(Constants.KEY_VERSION_UPDATE_TOTAL));
    }

}
