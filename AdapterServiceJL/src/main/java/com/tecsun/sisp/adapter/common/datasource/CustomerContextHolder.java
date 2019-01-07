package com.tecsun.sisp.adapter.common.datasource;

/**
 * 
 * @author tan
 *
 */
public class CustomerContextHolder {

    public static final String SISP_DATASOURCE = "sispDataSource";
    public static final String CSSP_DATASOURCE = "csspDataSource";
    public static final String SSSM_DATASOURCE = "sssmDataSource";
    public static final String OTHER_DATASOURCE = "otherDataSource";
    public static final String SKCJ_DATASOURCE = "skcjDataSource";
    public static final String SISP_FAKA_DATASOURCE="sispFakaDatasource";

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
