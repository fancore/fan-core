package fan.core.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import fan.core.exception.ExecutetimeException;
/**
 * <p> <b> @描述：</b> 方法常用操作工具类
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-06-18
 * <p> <b> @since 0.1.0 </b>
 */
public class MethodUtil {

	private MethodUtil(){}
	
	/**
	 * <p><des> 调用对象(或类)的setter成员方法(或类方法) </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Bar {
	 * 
	 *     private int baz;
	 *     private static double var;
	 * 
	 *     void setBaz(int baz){
	 *         this.baz = baz;
	 *     }
	 *     
	 *     static void setVar(double var) {
	 *         Bar.var = var;
	 *     }
	 * 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * MethodUtil.invokeSetterMethod(Bar.class, "var", 1.1);
	 * Bar bar = new Bar();
	 * MethodUtil.invokeSetterMethod(bar, "baz", 1);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static void invokeSetterMethod(Object object, String fieldName, Object value) {
		String method = "set" + StringUtil.toFirstLetterUpperCase(fieldName);
		Object[] args = {value};
		Class<?>[] types = {FieldUtil.getFieldType(object, fieldName)};
		invokeMethod(object, method, args, types);
	}
	
	/**
	 * <p><des> 调用对象(或类)的getter成员方法(或类方法) </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Bar {
	 * 
	 *     private int baz;
	 *     private static double var;
	 * 
	 *     int getBaz(){
	 *         return this.baz;
	 *     }
	 *     
	 *     static double getVar() {
	 *         return var;
	 *     }
	 * 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * double var = MethodUtil.invokeGetterMethod(Bar.class, "var");
	 * Bar bar = new Bar();
	 * int baz = MethodUtil.invokeGetterMethod(bar, "baz");
	 * Testing.printlnObject(var);
	 * Testing.printlnObject(baz);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <E> E invokeGetterMethod(Object object, String fieldName) {
		String method = "get" + StringUtil.toFirstLetterUpperCase(fieldName);
		return invokeMethod(object, method, null, null);
	}
	
	/**
	 * <p><des> 调用构造方法返还一个实例, 特别注意, 调该方法要求构造方法参数必须不能有基本数据类型存在 </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Bar {
	 * 
	 *     private int baz;
	 *     private String qux;
	 * 
	 *     public Bar(String qux){
	 *         this(1001, qux);
	 *     }
	 * 
	 *     private Bar(int baz, String qux){
	 *         this.baz = baz;
	 *         this.qux = qux;
	 *     }
	 * 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * <notes>// success</notes>
	 * Bar bar1 = MethodUtil.invokeConstructor(Bar.class, "Hi there");
	 * <notes>// fail, if you really do that, you will get a NoShcuMethodException</notes>
	 * Bar bar2 = MethodUtil.invokeConstructor(Bar.class, 13800, "Hi there");
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.MethodUtil#invokeConstructor(Class, Object[], Class[])
	 */
	public static <E> E invokeConstructor(Class<E> clazz, Object... argValues) {
		return invokeConstructor(clazz, argValues, ClassUtil.getObjectTypes(argValues));
	}
	
	/**
	 * <p><des> 调用构造方法返还一个实例 </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Bar {
	 * 
	 *     private int baz;
	 *     private String qux;
	 * 
	 *     public Bar(String qux){
	 *         this(1001, qux);
	 *     }
	 * 
	 *     private Bar(int baz, String qux){
	 *         this.baz = baz;
	 *         this.qux = qux;
	 *     }
	 * 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * Object[] args = {13800, "Hi there"};
	 * Class<?>[] types = {int.class, String.class};
	 * Bar bar = MethodUtil.invokeConstructor(Bar.class, args, types);
	 * Testing.printlnObject(bar);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.MethodUtil#invokeConstructor(Class, Object...)
	 */
	public static <E> E invokeConstructor(Class<E> clazz, Object[] argValues, Class<?>[] argTypes) {
		try {
			Constructor<E> constructor = clazz.getDeclaredConstructor(argTypes);
			constructor.setAccessible(true);
			return constructor.newInstance(argValues);
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}
	
	/**
	 * <p><des> 调用对象(或类)的成员方法(或类方法), 特别注意, 调该方法要求被调方法参数必须不能有基本数据类型存在 </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Bar {
	 * 
	 *     private int baz;
	 *     private String qux;
	 *     private static double var;
	 * 
	 *     void setBaz(int baz){
	 *         this.baz = baz;
	 *     }
	 *     
	 *     static double getVar() {
	 *         return var;
	 *     }
	 *     
	 *     void setQux(String qux) {
	 *         this.qux = qux;
	 *     }
	 * 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * double var = MethodUtil.invokeMethod(Bar.class, "getVar");
	 * Testing.printlnObject(var);
	 * Bar bar = new Bar();
	 * <notes>// success</notes>
	 * MethodUtil.invokeMethod(bar, "setQux", "one piece");
	 * <notes>// fail, if you really do that, you will get a NoShcuMethodException</notes>
	 * MethodUtil.invokeMethod(bar, "setBaz", 10086);
	 * Testing.printlnObject(bar);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.MethodUtil#invokeMethod(Object, String, Object[], Class[])
	 */
	public static <E> E invokeMethod(Object object, String methodName, Object... argValues) {
		return invokeMethod(object, methodName, argValues, ClassUtil.getObjectTypes(argValues));
	}
	
	/**
	 * <p><des> 调用对象(或类)的成员方法(或类方法) </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Bar {
	 * 
	 *     private int baz;
	 * 
	 *     void setBaz(int baz){
	 *         this.baz = baz;
	 *     }
	 * 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * Bar bar = new Bar("Hi there");
	 * Object[] args = {10086};
	 * Class<?>[] types = {int.class};
	 * MethodUtil.invokeMethod(bar, "setBaz", args, types);
	 * Testing.printlnObject(bar);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.MethodUtil#invokeMethod(Object, String, Object...)
	 */
	@SuppressWarnings("unchecked")
	public static <E> E invokeMethod(Object object, String methodName, Object[] argValues, Class<?>[] argTypes) {
		try {
			return (E) getAccessibleMethod(object, methodName, argTypes).invoke(object, argValues);
		} catch (ClassCastException e) { // must catch ClassCastException
			throw e;
		}  catch (NullPointerException e) { // must catch NullPointerException
			String classname = ClassUtil.getSimpleClassName(object);
			String message;
			if(argTypes == null || argTypes.length == 0){
				message = StringUtil.parsePlaceholder("method ?() can not be found in the class ?", methodName, classname);
			}else{
				String types = Arrays.toString(argTypes);
				types = types.substring(1, types.length() - 1);
				message = StringUtil.parsePlaceholder("method ?(?) can not be found in the class ?", methodName, types, classname);
			}
			throw new ExecutetimeException(new NoSuchMethodException(message));
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}
	
	/**
	 * <p><des> 获取对象(或类)成员方法(或类方法)可访问的方法对象 </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Bar {
	 * 
	 *     private int baz;
	 *     private static double var;
	 * 
	 *     void setBaz(int baz){
	 *         this.baz = baz;
	 *     }
	 *     
	 *     static void setVar(double var) {
	 *         Bar.var = var;
	 *     }
	 * 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * Method setVarMethod = MethodUtil.getAccessibleMethod(Bar.class, "setVar", double.class);
	 * Bar bar = new Bar();
	 * Method setBazMethod1 = MethodUtil.getAccessibleMethod(bar, "setBaz", int.class);
	 * <notes>// you do not need to create a instance, in fact.</notes>
	 * Method setBazMethod2 = MethodUtil.getAccessibleMethod(Bar.class, "setBaz", int.class);
	 * Testing.printlnObject(setVarMethod);
	 * Testing.printlnObject(setBazMethod1);
	 * Testing.printlnObject(setBazMethod2);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static Method getAccessibleMethod(Object object, String methodName, Class<?>... types){
		Class<?> entityClass = object instanceof Class ? (Class<?>) object : object.getClass();
		while(entityClass != null){
			try {
				Method target = entityClass.getDeclaredMethod(methodName, types);
				target.setAccessible(true);
				return target;
			} catch (Throwable e) { /* ignore the thrown exception */ }
			/* go to the super class */
			entityClass = entityClass.getSuperclass();
		}
		return null;
	}
	
	/**
	 * <p><des> 获取类声明的方法列表集合(不包含父类) </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Bar {
	 *     . . . . . . 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * List<Method> methods = MethodUtil.getDeclaredMethods(Bar.class);
	 * Testing.printlnObject(methods);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.MethodUtil#getAllMethods(Class)
	 */
	public static List<Method> getDeclaredMethods(Class<?> clazz){
		Method[] methods = clazz.getDeclaredMethods();
		if(methods.length == 0){
			return null;
		}
		for(Method method : methods){
			method.setAccessible(true);
		}
		return Arrays.asList(methods);
	}
	
	/**
	 * <p><des> 获取类声明的方法名称列表集合(不包含父类) </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Bar {
	 *     . . . . . . 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * List<String> methodNames = MethodUtil.getDeclaredMethodNames(Bar.class);
	 * Testing.printlnObject(methodNames);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.MethodUtil#getAllMethodNames(Class)
	 */
	public static List<String> getDeclaredMethodNames(Class<?> clazz){
		Method[] methods = clazz.getDeclaredMethods();
		if(methods.length == 0){
			return null;
		}
		List<String> methodNames = new ArrayList<String>(methods.length);
		for(Method method : methods){
			methodNames.add(method.getName());
		}
		return methodNames;
	}
	
	/**
	 * <p><des> 获取类声明的方法列表集合(包含父类) </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Bar {
	 *     . . . . . . 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * List<Method> methods = MethodUtil.getAllMethods(Bar.class);
	 * Testing.printlnObject(methods);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.MethodUtil#getDeclaredMethods(Class)
	 */
	public static List<Method> getAllMethods(Class<?> clazz){
		if(clazz == null){
			throw new ExecutetimeException("clazz must not be null");
		}
		List<Method> methods = new ArrayList<Method>();
		List<Method> thisClassMethod;
		while(clazz != null){
			thisClassMethod = getDeclaredMethods(clazz);
			if(thisClassMethod != null){
				methods.addAll(thisClassMethod);
			}
			clazz = clazz.getSuperclass();
		}
		return methods.size() == 0 ? null : methods;
	}
	
	/**
	 * <p><des> 获取类声明的方法名称列表集合(包含父类) </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class Bar {
	 *     . . . . . . 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * List<String> methodNames = MethodUtil.getAllMethodNames(Bar.class);
	 * Testing.printlnObject(methodNames);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.MethodUtil#getDeclaredMethodNames(Class)
	 */
	public static List<String> getAllMethodNames(Class<?> clazz){
		if(clazz == null){
			throw new ExecutetimeException("clazz must not be null");
		}
		List<Method> methods = getAllMethods(clazz);
		List<String> methodNames = new ArrayList<String>(methods.size());
		for(Method method : methods){
			methodNames.add(method.getName());
		}
		return methodNames;
	}
	
}