package com.tecsun.sisp.iface.common.util.datasource;

/**
 * Created by DG on 2015/10/9.
 */
public class CustomerContextHolder {

    public static final String SMS_DATASOURCE = "smsDataSource";
    public static final String SIMIS_DATASOURCE = "simisDataSource";
    public static final String VERSION_DATASOURCE = "versionDataSource";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setCustomerType(String customerType){
        contextHolder.set(customerType);
    }

    public static String getCustomerType(){
        return contextHolder.get();
    }

    public static void removeCustomerType(){
        contextHolder.remove();
    }
}
