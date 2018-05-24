package com.mfish.utils;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import com.sun.xml.internal.ws.util.UtilException;
import org.springframework.util.FastByteArrayOutputStream;

/**
 * 一些通用的函数
 */
public class ObjectUtil {

	/**
	 * 比较两个对象是否相等。<br>
	 * 相同的条件有两个，满足其一即可：<br>
	 * <ol>
	 * <li>obj1 == null &amp;&amp; obj2 == null</li>
	 * <li>obj1.equals(obj2)</li>
	 * </ol>
	 * 1. obj1 == null &amp;&amp; obj2 == null 2. obj1.equals(obj2)
	 * 
	 * @param obj1 对象1
	 * @param obj2 对象2
	 * @return 是否相等
	 */
	public static boolean equal(Object obj1, Object obj2) {
		return (obj1 == obj2) || (obj1 != null && obj1.equals(obj2));
	}

	/**
	 * 比较两个对象是否不相等。<br>
	 * 
	 * @param obj1 对象1
	 * @param obj2 对象2
	 * @return 是否不等
	 * @since 3.0.7
	 */
	public static boolean notEqual(Object obj1, Object obj2) {
		return false == equal(obj1, obj2);
	}


	/**
	 * 对象中是否包含元素<br>
	 * 支持的对象类型包括：
	 * <ul>
	 * <li>String</li>
	 * <li>Collection</li>
	 * <li>Map</li>
	 * <li>Iterator</li>
	 * <li>Enumeration</li>
	 * <li>Array</li>
	 * </ul>
	 * 
	 * @param obj 对象
	 * @param element 元素
	 * @return 是否包含
	 */
	public static boolean contains(Object obj, Object element) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof String) {
			if (element == null) {
				return false;
			}
			return ((String) obj).contains(element.toString());
		}
		if (obj instanceof Collection) {
			return ((Collection<?>) obj).contains(element);
		}
		if (obj instanceof Map) {
			return ((Map<?, ?>) obj).values().contains(element);
		}

		if (obj instanceof Iterator) {
			Iterator<?> iter = (Iterator<?>) obj;
			while (iter.hasNext()) {
				Object o = iter.next();
				if (equal(o, element)) {
					return true;
				}
			}
			return false;
		}
		if (obj instanceof Enumeration) {
			Enumeration<?> enumeration = (Enumeration<?>) obj;
			while (enumeration.hasMoreElements()) {
				Object o = enumeration.nextElement();
				if (equal(o, element)) {
					return true;
				}
			}
			return false;
		}
		if (obj.getClass().isArray() == true) {
			int len = Array.getLength(obj);
			for (int i = 0; i < len; i++) {
				Object o = Array.get(obj, i);
				if (equal(o, element)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 检查对象是否为null<br>
	 * 判断标准为：
	 * <pre>
	 * 1. == null
	 * 2. equals(null)
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 是否为null
	 */
	public static boolean isNull(Object obj) {
		return null == obj || obj.equals(null);
	}

	/**
	 * 检查对象是否不为null
	 * 
	 * @param obj 对象
	 * @return 是否为null
	 */
	public static boolean isNotNull(Object obj) {
		return null != obj && false == obj.equals(null);
	}

	/**
	 * 如果给定对象为{@code null}返回默认值
	 *
	 * <pre>
	 * ObjectUtil.defaultIfNull(null, null)      = null
	 * ObjectUtil.defaultIfNull(null, "")        = ""
	 * ObjectUtil.defaultIfNull(null, "zz")      = "zz"
	 * ObjectUtil.defaultIfNull("abc", *)        = "abc"
	 * ObjectUtil.defaultIfNull(Boolean.TRUE, *) = Boolean.TRUE
	 * </pre>
	 *
	 * @param <T> 对象类型
	 * @param object 被检查对象，可能为{@code null}
	 * @param defaultValue 被检查对象为{@code null}返回的默认值，可以为{@code null}
	 * @return 被检查对象为{@code null}返回默认值，否则返回原值
	 * @since 3.0.7
	 */
	public static <T> T defaultIfNull(final T object, final T defaultValue) {
		return (null != object) ? object : defaultValue;
	}





	/**
	 * 序列化<br>
	 * 对象必须实现Serializable接口
	 * 
	 * @param <T> 对象类型
	 * @param obj 要被序列化的对象
	 * @return 序列化后的字节码
	 */
	public static <T> byte[] serialize(T obj) {
		if (null == obj || false == (obj instanceof Serializable)) {
			return null;
		}

		FastByteArrayOutputStream byteOut = new FastByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(byteOut);
			oos.writeObject(obj);
			oos.flush();
		} catch (Exception e) {
			throw new UtilException(e);
		} finally {
			try {
				if(oos != null) oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return byteOut.toByteArray();
	}

	/**
	 * 反序列化<br>
	 * 对象必须实现Serializable接口
	 * 
	 * @param <T> 对象类型
	 * @param bytes 反序列化的字节码
	 * @return 反序列化后的对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unSerialize(byte[] bytes) {
		ObjectInputStream ois = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			return (T) ois.readObject();
		} catch (Exception e) {
			throw new UtilException(e);
		}
	}

}
