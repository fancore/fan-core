package fan.core.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import fan.core.exception.ExecutetimeException;
/**
 * <p> <b> @描述：</b> 字段域常用操作工具类
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-06-15
 * <p> <b> @since 0.1.0 </b>
 */
public class FieldUtil {

	private FieldUtil(){}
	
	/**
	 * <p><des> 设置对象(或类)成员属性(或类属性)字段的值 </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Foobar {
	 * 
	 *     private int bar;
	 *     private String baz;
	 *     private static double version;
	 * 
	 *     <b>@</b>Override
	 *     public String toString() {
	 *         return bar + "  " + baz + "  [" + version + "]";
	 *     }
	 * 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * FieldUtil.setFieldValue(Foobar.class, "version", 1.1);
	 * Foobar foobar = new Foobar();
	 * FieldUtil.setFieldValue(foobar, "bar", 1);
	 * FieldUtil.setFieldValue(foobar, "baz", "Hi there");
	 * Testing.printlnObject(foobar);
	 * <b><em>output look like：</em></b>
	 * <output>1  Hi there  [1.1]</output>
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static void setFieldValue(Object object, String fieldName, Object value) {
		Field field = null;
		try {
			field = getAccessibleField(object, fieldName);
			field.set(object, value);
		} catch (Throwable e) {
			if(field != null)
				throw new ExecutetimeException(e);
			String classname = ClassUtil.getSimpleClassName(object);
			throw new ExecutetimeException(new NoSuchFieldException(
				StringUtil.parsePlaceholder("field name '?' can not be found in the class ?", fieldName, classname)
			));
		}
	}
	
	/**
	 * <p><des> 获取对象(或类)成员属性(或类属性)字段的值 </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Foobar {
	 * 
	 *     private int bar;
	 *     private String baz;
	 *     private static double version;
	 * 
	 *     <b>@</b>Override
	 *     public String toString() {
	 *         return bar + "  " + baz + "  [" + version + "]";
	 *     }
	 * 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * FieldUtil.setFieldValue(Foobar.class, "version", 1.1);
	 * Foobar foobar = new Foobar();
	 * FieldUtil.setFieldValue(foobar, "baz", "Hi there");
	 * double version = FieldUtil.getFieldValue(Foobar.class, "version");
	 * String baz = FieldUtil.getFieldValue(foobar, "baz");
	 * Testing.printlnObject(version);
	 * Testing.printlnObject(baz);
	 * </pre></p>
	 * @since 0.1.0
	 */
	@SuppressWarnings("unchecked")
	public static <E> E getFieldValue(Object object, String fieldName) {
		try {
			return (E) getAccessibleField(object, fieldName).get(object);
		} catch (ClassCastException e) { // must catch ClassCastException
			throw e;
		} catch (Throwable e) {
			String classname = ClassUtil.getSimpleClassName(object);
			throw new ExecutetimeException(new NoSuchFieldException(
				StringUtil.parsePlaceholder("field name '?' can not be found in the class ?", fieldName, classname)
			));
		}
	}
	
	/**
	 * <p><des> 获取对象(或类)成员属性(或类属性)可访问的字段对象 </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Foobar {
	 * 
	 *     private int bar;
	 *     private String baz;
	 *     private static double version;
	 * 
	 *     <b>@</b>Override
	 *     public String toString() {
	 *         return bar + "  " + baz + "  [" + version + "]";
	 *     }
	 * 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * Field versionField = FieldUtil.getAccessibleField(Foobar.class, "version");
	 * Foobar foobar = new Foobar();
	 * Field barField = FieldUtil.getAccessibleField(foobar, "bar");
	 * <notes>// you do not need to create a instance, in fact.</notes>
	 * Field bazField = FieldUtil.getAccessibleField(Foobar.class, "baz");
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static Field getAccessibleField(Object object, String fieldName){
		Class<?> entityClass = object instanceof Class ? (Class<?>) object : object.getClass();
		while(entityClass != null){
			try {
				Field field = entityClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				return field;
			} catch (Throwable e) { /* ignore the thrown exception */ }
			/* go to the super class */
			entityClass = entityClass.getSuperclass();
		}
		return null;
	}
	
	/**
	 * <p><des> 获取对象(或类)成员属性(或类属性)字段的类型 </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Foobar {
	 * 
	 *     private int bar;
	 *     private String baz;
	 *     private static double version;
	 * 
	 *     <b>@</b>Override
	 *     public String toString() {
	 *         return bar + "  " + baz + "  [" + version + "]";
	 *     }
	 * 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * Class<?> versionType = FieldUtil.getFieldType(Foobar.class, "version");
	 * Foobar foobar = new Foobar();
	 * Class<?>  barType = FieldUtil.getFieldType(foobar, "bar");
	 * <notes>// you do not need to create a instance, in fact.</notes>
	 * Class<?>  bazType = FieldUtil.getFieldType(Foobar.class, "baz");
	 * Testing.printlnObject(versionType);
	 * Testing.printlnObject(barType);
	 * Testing.printlnObject(bazType);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static Class<?> getFieldType(Object object, String fieldName) {
		try {
			return getAccessibleField(object, fieldName).getType();
		} catch (NullPointerException e) { // only NullPointerException could be thrown
			String classname = ClassUtil.getSimpleClassName(object);
			throw new ExecutetimeException(new NoSuchFieldException(
				StringUtil.parsePlaceholder("field name '?' can not be found in the class ?", fieldName, classname)
			));
		}
	}
	
	/**
	 * <p><des> 获取类声明的字段列表集合(不包含父类) </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Foobar {
	 * 
	 *     private int bar;
	 *     private String baz;
	 *     private static double version;
	 * 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * List<Field> fields = FieldUtil.getDeclaredFields(Foobar.class);
	 * Testing.printlnObject(fields);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.FieldUtil#getAllFields(Class)
	 */
	public static List<Field> getDeclaredFields(Class<?> clazz){
		Field[] fields = clazz.getDeclaredFields();
		if(fields.length == 0){
			return null;
		}
		for(Field field : fields){
			field.setAccessible(true);
		}
		return Arrays.asList(fields);
	}
	
	/**
	 * <p><des> 获取类声明的字段名称列表集合(不包含父类) </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Foobar {
	 * 
	 *     private int bar;
	 *     private String baz;
	 *     private static double version;
	 * 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * List<String> fieldNames = FieldUtil.getDeclaredFieldNames(Foobar.class);
	 * Testing.printlnObject(fieldNames);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.FieldUtil#getAllFieldNames(Class)
	 */
	public static List<String> getDeclaredFieldNames(Class<?> clazz){
		Field[] fields = clazz.getDeclaredFields();
		if(fields.length == 0){
			return null;
		}
		List<String> fieldNames = new ArrayList<String>(fields.length);
		for(Field field : fields){
			fieldNames.add(field.getName());
		}
		return fieldNames;
	}
	
	/**
	 * <p><des> 获取类声明的字段列表集合(包含父类) </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Foobar {
	 * 
	 *     private int bar;
	 *     private String baz;
	 *     private static double version;
	 * 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * List<Field> fields = FieldUtil.getAllFields(Foobar.class);
	 * <notes>// Object have no field</notes>
	 * Testing.printlnObject(fields);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.FieldUtil#getDeclaredFields(Class)
	 */
	public static List<Field> getAllFields(Class<?> clazz){
		if(clazz == null){
			throw new ExecutetimeException("clazz must not be null");
		}
		List<Field> fields = new ArrayList<Field>();
		List<Field> thisClassFields;
		while(clazz != null){
			thisClassFields = getDeclaredFields(clazz);
			if(thisClassFields != null){
				fields.addAll(thisClassFields);
			}
			clazz = clazz.getSuperclass();
		}
		return fields.size() == 0 ? null : fields;
	}
	
	/**
	 * <p><des> 获取类声明的字段名称列表集合(包含父类) </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Foobar {
	 * 
	 *     private int bar;
	 *     private String baz;
	 *     private static double version;
	 * 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * List<String> fieldNames = FieldUtil.getAllFieldNames(Foobar.class);
	 * <notes>// Object have no field</notes>
	 * Testing.printlnObject(fieldNames);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.FieldUtil#getDeclaredFieldNames(Class)
	 */
	public static List<String> getAllFieldNames(Class<?> clazz){
		if(clazz == null){
			throw new ExecutetimeException("clazz must not be null");
		}
		List<Field> fields = getAllFields(clazz);
		if(fields == null){
			return null;
		}
		List<String> fieldNames = new ArrayList<String>(fields.size());
		for(Field field : fields){
			fieldNames.add(field.getName());
		}
		return fieldNames;
	}
	
}