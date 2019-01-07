package com.tecsun.sisp.adapter.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanValidatorUtil {

	private static Logger logger = LoggerFactory
			.getLogger(BeanValidatorUtil.class);

	public static void getFieldGetterNames(Object o) {

		if (o == null)
			return;

		Map<String, Class> map = BeanValidatorUtil.getClassFields(o.getClass(),
				true);
		for (Map.Entry<String, Class> entry : map.entrySet()) {
			// System.out.println("Key = " + entry.getKey() + ", Value = " +
			// entry.getValue());
			String key = entry.getKey();
			if (key == null || key.isEmpty())
				continue;
			key = key.substring(key.lastIndexOf(".") + 1);

			String value = (String) getFieldValueByName(key, o, true);
			if (value == null || value.isEmpty()) {

				String defaultValue = getFieldAnnotation(o, key);
				ReflectionUtils.setFieldValue(o, key, defaultValue);
			}
		}
	}

	private static String getFieldAnnotation(Object o, String fieldName) {
		StringBuffer sb = new StringBuffer();

		Field[] fields = o.getClass().getDeclaredFields();
		for (Field field : fields) {
			com.tecsun.sisp.adapter.common.annotation.Field fieldInfo = field
					.getAnnotation(com.tecsun.sisp.adapter.common.annotation.Field.class);
			if (fieldInfo != null) {
				sb.append(Modifier.toString(field.getModifiers())).append(" ")
						.append(field.getType().getSimpleName()).append(" ")
						.append(field.getName()).append("\n");

				sb.append("注解值: ").append(fieldInfo.empty()).append("\n\n");
				if (field.getName().equalsIgnoreCase(fieldName)) {
					return fieldInfo.empty();
				}
			}
		}

//		System.out.println(sb.toString());
		return null;
	}

	/**
	 * 根据属性名获取属性值
	 * */
	private static Object getFieldValueByName(String fieldName, Object o,
			boolean includeParentClass) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Object value = null;
			try {
				Method method = o.getClass().getMethod(getter, new Class[] {});
				// 抑制Java对方法进行检查,主要是针对私有方法而言
				// method.setAccessible(true);
				value = method.invoke(o, new Object[] {});
			} catch (Exception ex) {

			}

			// if (value == null && includeParentClass)
			// value = getFieldValueByName(fieldName, o.getClass()
			// .getSuperclass(), includeParentClass);

			return value;
		} catch (Exception e) {
			logger.error("获取属性值错误", e);
			return null;
		}
	}

	/**
	 * 获取属性名数组
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Map getFiledName(Class clazz, boolean includeParentClass) {
		if (clazz == null)
			return null;
		// clazz = o.getClass();
		Field[] fields = clazz.getDeclaredFields();
		// String[] fieldNames = new String[fields.length];
		Map fieldNames = new HashMap();
		for (int i = 0; i < fields.length; i++) {
			System.out
					.println(fields[i].getName() + "  " + fields[i].getType());
			fieldNames.put(fields[i].getName(), fields[i].getType());
		}

		if (includeParentClass) {
			Map fieldNamesTemp = getFiledName(clazz.getSuperclass(),
					includeParentClass);
			if (fieldNamesTemp == null)
				return fieldNames;

			fieldNames.putAll(fieldNamesTemp);
		}

		return fieldNames;
	}

	/**
	 * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
	 * */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private static List getFiledsInfo(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		// String[] fieldNames = new String[fields.length];
		List list = new ArrayList();
		Map infoMap = null;
		for (int i = 0; i < fields.length; i++) {
			infoMap = new HashMap();
			infoMap.put("type", fields[i].getType().toString());
			infoMap.put("name", fields[i].getName());
			infoMap.put("value",
					getFieldValueByName(fields[i].getName(), o, false));
			list.add(infoMap);
		}
		return list;
	}

	/**
	 * 获取对象的所有属性值，返回一个对象数组
	 **/
	public static Object[] getFiledValues(Object o, boolean includeParentClass) {
		// List fieldNames = getFiledName(o.getClass(), true);
		// Object[] value = new Object[fieldNames.size()];
		// for (int i = 0; i < fieldNames.size(); i++) {
		// String fieldName = String.valueOf(fieldNames.get(i));
		// value[i] = getFieldValueByName(fieldName, o, includeParentClass);
		// }
		// return value;
		return null;
	}

	/**
	 * 循环向上转型, 获取对象的 DeclaredMethod
	 * 
	 * @param object
	 *            : 子类对象
	 * @param methodName
	 *            : 父类中的方法名
	 * @param parameterTypes
	 *            : 父类中的方法参数类型
	 * @return 父类中的方法对象
	 */
	public static Method getDeclaredMethod(Object object, String methodName,
			Class<?>... parameterTypes) {
		Method method = null;

		for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz
				.getSuperclass()) {
			try {
				method = clazz.getDeclaredMethod(methodName, parameterTypes);
				return method;
			} catch (Exception e) {
				// 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
				// 如果这里的异常打印或者往外抛，则就不会执行clazz =
				// clazz.getSuperclass(),最后就不会进入到父类中了

			}
		}

		return null;
	}

	/**
	 * 直接调用对象方法, 而忽略修饰符(private, protected, default)
	 * 
	 * @param object
	 *            : 子类对象
	 * @param methodName
	 *            : 父类中的方法名
	 * @param parameterTypes
	 *            : 父类中的方法参数类型
	 * @param parameters
	 *            : 父类中的方法参数
	 * @return 父类中方法的执行结果
	 */

	public static Object invokeMethod(Object object, String methodName,
			Class<?>[] parameterTypes, Object[] parameters) {
		// 根据 对象、方法名和对应的方法参数 通过反射 调用上面的方法获取 Method 对象
		Method method = getDeclaredMethod(object, methodName, parameterTypes);
		if (method != null) {
			// 抑制Java对方法进行检查,主要是针对私有方法而言
			method.setAccessible(true);
		}
		try {
			if (null != method) {

				// 调用object 的 method 所代表的方法，其方法的参数是 parameters
				return method.invoke(object, parameters);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 循环向上转型, 获取对象的 DeclaredField
	 * 
	 * @param object
	 *            : 子类对象
	 * @param fieldName
	 *            : 父类中的属性名
	 * @return 父类中的属性对象
	 */

	public static Field getDeclaredField(Object object, String fieldName) {
		Field field = null;

		Class<?> clazz = object.getClass();

		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				field = clazz.getDeclaredField(fieldName);
				return field;
			} catch (Exception e) {
				// 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
				// 如果这里的异常打印或者往外抛，则就不会执行clazz =
				// clazz.getSuperclass(),最后就不会进入到父类中了

			}
		}

		return null;
	}

	/**
	 * 直接读取对象的属性值, 忽略 private/protected 修饰符, 也不经过 getter
	 * 
	 * @param object
	 *            : 子类对象
	 * @param fieldName
	 *            : 父类中的属性名
	 * @return : 父类中的属性值
	 */

	public static Object getFieldValue(Object object, String fieldName) {

		// 根据 对象和属性名通过反射 调用上面的方法获取 Field对象
		Field field = getDeclaredField(object, fieldName);

		// 抑制Java对其的检查
		field.setAccessible(true);

		try {
			// 获取 object 中 field 所代表的属性值
			return field.get(object);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 直接设置对象属性值, 忽略 private/protected 修饰符, 也不经过 setter
	 * 
	 * @param object
	 *            : 子类对象
	 * @param fieldName
	 *            : 父类中的属性名
	 * @param value
	 *            : 将要设置的值
	 */

	public static void setFieldValue(Object object, String fieldName,
			Object value) {

		// 根据 对象和属性名通过反射 调用上面的方法获取 Field对象
		Field field = getDeclaredField(object, fieldName);

		// 抑制Java对其的检查
		field.setAccessible(true);

		try {
			// 将 object 中 field 所代表的值 设置为 value
			field.set(object, value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取类实例的属性值
	 * 
	 * @param clazz
	 *            类名
	 * @param includeParentClass
	 *            是否包括父类的属性值
	 * @return 类名.属性名=属性类型
	 */
	public static Map<String, Class> getClassFields(Class clazz,
			boolean includeParentClass) {
		Map<String, Class> map = new HashMap<String, Class>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			map.put(clazz.getName() + "." + field.getName(), field.getType());
		}
		if (includeParentClass)
			getParentClassFields(map, clazz.getSuperclass());
		return map;
	}

	/**
	 * 获取类实例的父类的属性值
	 * 
	 * @param map
	 *            类实例的属性值Map
	 * @param clazz
	 *            类名
	 * @return 类名.属性名=属性类型
	 */
	private static Map<String, Class> getParentClassFields(
			Map<String, Class> map, Class clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			map.put(clazz.getName() + "." + field.getName(), field.getType());
		}
		if (clazz.getSuperclass() == null) {
			return map;
		}
		getParentClassFields(map, clazz.getSuperclass());
		return map;
	}

}
