package com.siqisoft.stone.develop.utils;

import java.lang.reflect.Field;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.siqisource.stone.exceptions.ProgramException;

public class JdbcUtils {

	private static Map<Integer, String> TYPE_MAP = new HashMap<Integer, String>();

	static {
		TYPE_MAP.put(Types.NUMERIC, Integer.class.getName());
		TYPE_MAP.put(Types.DECIMAL, Float.class.getName());
		TYPE_MAP.put(Types.INTEGER, Integer.class.getName());
		TYPE_MAP.put(Types.SMALLINT, Integer.class.getName());
		TYPE_MAP.put(Types.VARCHAR, String.class.getName());
		TYPE_MAP.put(Types.BOOLEAN, Boolean.class.getName());
		TYPE_MAP.put(Types.BIT, Boolean.class.getName());
		TYPE_MAP.put(Types.DATE, Date.class.getName());
		TYPE_MAP.put(Types.TIME, Date.class.getName());
		TYPE_MAP.put(Types.TIMESTAMP, Date.class.getName());
		TYPE_MAP.put(Types.FLOAT, Float.class.getName());
		TYPE_MAP.put(Types.DOUBLE, Double.class.getName());
		TYPE_MAP.put(Types.CHAR, Boolean.class.getName());
	}

	public static String getJavaType(Integer dataType,String javaType) {
		if("NUMBER".equals(javaType)){
			return TYPE_MAP.get(Types.NUMERIC);
		}
		return TYPE_MAP.get(dataType);
	}

	public static String getJdbcType(Integer dataType,String javaType) {
		if (dataType == Types.BIT) {
			dataType = Types.BOOLEAN;
		}
		Field[] fields = Types.class.getFields();
		for (Field field : fields) {
			try {
				if (dataType == field.getInt(null)) {
					return field.getName();
				}
			} catch (Exception e) {
				throw new ProgramException("根据jdbcType找到对应的字符时出错了");
			}
		}
		return "";
	}
}
