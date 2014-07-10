package fan.core.util;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import fan.core.util.code.EncodingCode;
/**
 * <p> <b> @描述：</b> 
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-06-23
 * <p> <b> @since 0.1.0 </b>
 */
public class ConvertUtil {

	private ConvertUtil(){}
	
	/** <p><des> primitive boxer mapping </des></p> */
	public static final Map<Class<?>, Class<?>> primitiveBoxerMap;
	/** <p><des> boxer primitive mapping </des></p> */
	public static final Map<Class<?>, Class<?>> boxerPrimitiveMap;
	
	/**
	 * <p><des> 转换字符串为指定类型的值 </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Foo {
	 * 
	 *     private int bar;
	 * 
	 *     <b>@</b>Override
	 *     public String toString() {
	 *         return String.valueOf(bar);
	 *     }
	 * 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * <notes>// 错误的示例</notes>
	 * String value = "10086";
	 * Foo foo = new Foo();
	 * <notes>// you can not do this</notes>
	 * <notes>// if you really do that, you will get a IllegalArgumentException: </notes>
	 * <notes>// Can not set int field fan.core.test.model.Foo.bar to java.lang.String</notes>
	 * FieldUtil.setFieldValue(foo, "bar", value);
	 * Testing.printlnObject(foo);
	 * <b><em>e.g.</em></b>
	 * <notes>// 正确的示例</notes>
	 * String value = "10086";
	 * Foo foo = new Foo();
	 * <notes>// you should do it like this:</notes>
	 * Class&lt;?&gt; type = FieldUtil.getFieldType(foo, "bar");
	 * FieldUtil.setFieldValue(foo, "bar", ConvertUtil.objectValue(value, type));
	 * Testing.printlnObject(foo);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static Object objectValue(String value, Class<?> type){
		// primitive & boxer type
		if(type.isPrimitive() || boxerPrimitiveMap.containsKey(type)){
			Class<?> key = type.isPrimitive() ? type : boxerPrimitiveMap.get(type);
			String methodName = key + "Value";
			return MethodUtil.invokeMethod(ConvertUtil.class, methodName, value);
		}
		// Date type ( try to parse the value pattern )
		if(ClassUtil.isInstanceOf(type, Date.class)){
			return DateUtil.parseDate(value, DateUtil.getPattern(value));
		}
		// byte[] type ( UTF-8 )
		if(ClassUtil.isInstanceOf(type, byte[].class)){
			return value.getBytes(Charset.forName(EncodingCode.UTF_8.toCode()));
		}
		// undefined
		return value;
	}
	
	/**
	 * <p><des> 转换字符串为指定类型的值 </des></p>
	 * @see fan.core.util.ConvertUtil#objectValue(String, Class)
	 * @since 0.1.0
	 */
	public static Object[] objectValues(String[] values, Class<?>[] types){
		Object[] target = new Object[values.length];
		for(int i = 0; i < values.length; i++){
			target[i] = objectValue(values[i], types[i]);
		}
		return target;
	}
	
	// the following method is too simple and there is nothing to describe
	
	public static byte byteValue(String value){
		return Byte.parseByte(value);
	}
	
	public static short shortValue(String value){
		return Short.parseShort(value);
	}
	
	public static int intValue(String value){
		return Integer.parseInt(value);
	}
	
	public static long longValue(String value){
		return Long.parseLong(value);
	}
	
	public static float floatValue(String value){
		return Float.parseFloat(value);
	}
	
	public static double doubleValue(String value){
		return Double.parseDouble(value);
	}
	
	public static char charValue(String value){
		return value.charAt(0);
	}
	
	public static boolean booleanValue(String value){
		return Boolean.parseBoolean(value);
	}
	
	static {
		primitiveBoxerMap = new HashMap<Class<?>, Class<?>>(9, 1);
		primitiveBoxerMap.put(Byte.TYPE, Byte.class);
		primitiveBoxerMap.put(Character.TYPE, Character.class);
		primitiveBoxerMap.put(Short.TYPE, Short.class);
		primitiveBoxerMap.put(Integer.TYPE, Integer.class);
		primitiveBoxerMap.put(Long.TYPE, Long.class);
		primitiveBoxerMap.put(Float.TYPE, Float.class);
		primitiveBoxerMap.put(Double.TYPE, Double.class);
		primitiveBoxerMap.put(Boolean.TYPE, Boolean.class);
		primitiveBoxerMap.put(Void.TYPE, Void.class);
		boxerPrimitiveMap = MapUtil.reverseKeyValue(primitiveBoxerMap);
	}
}