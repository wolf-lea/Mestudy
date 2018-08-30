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

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setCustomerType(String customerType) {
		contextHolder.set(customerType);
	}

	public static String getCustomerType() {
		return contextHolder.get();
	}

	public static void removeCustomerType() {
		contextHolder.remove();
	}
}
