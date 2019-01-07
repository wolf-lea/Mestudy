package com.tecsun.sisp.adapter.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 身份证编码校验工具类
 *
 * @author 菜鸟
 *
 */
public class IdCardUtil {

	/**
	 * @param id
	 *            身份证号(15位或者18位都行)
	 * @return 返回检测结果 true or false
	 */
	public static boolean checkIdCard(String id) {
		if (id == null || "".equals(id)) {
			return false;
		}
		String idcard = id;
		String[] keys = new String[] { "11", " 12", " 13", "", "14", " 15",
				" 21", " 22", " 23", " 31", " 32", " 33", "34", " 35", " 36",
				" 37", " 41", " 42", " 43", " 44", " 45", " 46", " 50", " 51",
				" 52", " 53", " 54", " 61", "62", " 63", " 64", " 65", " 71",
				" 81", " 82", " 91" };
		String[] values = new String[] { "北京", "天津", "河北", "山西", "内蒙古", "辽宁",
				"吉林", "黑龙江", "上海", "江苏", "浙江", "安徽", "福建", "江西", "山东", "河南",
				"湖北", "湖南", "广东", "广西", "海南", "重庆", "四川", "贵州", "云南", "西藏",
				"陕西", "甘肃", "青海", "宁夏", "新疆", "台湾", "香港", "澳门", "国外" };
		Map<String, String> city = new HashMap<String, String>();
		for (int i = 0; i < keys.length; i++) {
			city.put(keys[i], values[i]);
		}
		// 验证长度
		if (!idcard.matches("/^\\d{17}(\\d|x)$/i")
				&& !idcard.matches("/^\\d{15}$/i")) {
			return false;
		}

		// 验证地区
		if (!city.containsKey(idcard.substring(0, 1))) {
			return false;
		}

		// 15位身份证处理转18位
		if (idcard.length() == 15) {
			idcard = idcard.substring(0, 5) + "19" + idcard.substring(6, 13);
			String bit18 = getVerifyBit(idcard);
			idcard = idcard + bit18;
		}

		// 验证年龄合法性
		int year = Integer.valueOf(idcard.substring(6, 9));
		if (year > 2080 || year < 1900) {
			return false;
		}
		// 验证18位身份证
		String idcardBase = idcard.substring(0, 16);
		if (!idcard.substring(17, 17).equals(getVerifyBit(idcardBase))) {
			return false;
		}
		return true;
	}

	/**
	 * @param idBase
	 *            身份证前17位 (15位身份证则需先转18位) 计算身份证校验码，根据国家标准GB 11643-1999
	 *
	 * @return 身份证对应的校验码
	 */
	public static String getVerifyBit(String idBase) {
		String verifyNumber = "";
		if (idBase.length() == 17) {
			int factor[] = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
					5, 8, 4, 2 };
			String[] verifyNumberList = new String[] { "1", "0", "X", "9", "8",
					"7", "6", "5", "4", "3", "2" };

			int checksum = 0;
			for (int i = 0; i < idBase.length(); i++) {
				checksum += Integer.valueOf(idBase.substring(i, i + 1))
						* factor[i];
			}
			int mod = checksum % 11;
			verifyNumber = verifyNumberList[mod];
		}
		return verifyNumber;
	}

	/**
	 * 根据身份证号计算性别
	 *
	 * @param id
	 *            身份证号
	 * @return 返回数字 0-女;1-男;-1-身份证号不正确
	 */
	public static int getGenderById(String id) {
		if (id == null || "".equals(id)) {
			return -1;
		}
		String gender = "";
		if (id.length() == 18) {
			gender = id.substring(16, 17);
		} else if (id.length() == 15) {
			gender = id.substring(14);
		} else {
			return -1;
		}
		if (Integer.valueOf(gender) % 2 == 0) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * @param id
	 *            身份证号
	 * @return 返回出生年月日 格式: yyyy-mm-dd
	 */
	public static String getBirthdayById(String id) {
		String brith = "";
		if (id.length() == 18) {
			brith += id.substring(6, 9) + "-";
			brith += id.substring(10, 11) + "-";
			brith += id.substring(12, 13);
		} else if (id.length() == 15) {
			brith += "19" + id.substring(6, 7) + "-";
			brith += id.substring(8, 9) + "-";
			brith += id.substring(10, 11);
		}
		return brith;
	}
}
