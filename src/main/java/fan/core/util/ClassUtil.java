package fan.core.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import fan.core.exception.ExecutetimeException;
/**
 * <p> <b> @描述：</b> Class常用操作工具类
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-06-15
 * <p> <b> @since 0.1.0 </b>
 */
public class ClassUtil {

	private ClassUtil(){}
	
	/**
	 * <p><des> 返还一个新的该Class的一个实例 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Animal animal = ClassUtil.getInstance(Animal.class);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <E> E getInstance(Class<E> clazz){
		try {
			return clazz.newInstance();
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}
	
	/**
	 * <p><des> 返还参数指定的全类名表示的一个Class实例 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Class&lt;?&gt; clazz = ClassUtil.forName("fan.core.util.ClassUtil");
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static Class<?> forName(String className){
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new ExecutetimeException(e);
		}
	}
	
	/**
	 * <p><des> 判定参数1是否是参数2所表示的类(包括子类)或接口的一个实例。特别的, 如果参数2是一个表示基本数据类型的Class, 则永远返回false </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * public class Tiger extends Animal {}
	 * <b><em>e.g.</em></b>
	 * Tiger tiger = new Tiger();
	 * Animal animal = new Animal();
	 * <notes>// true</notes>
	 * Testing.printlnObject(ClassUtil.isInstanceOf(tiger, Tiger.class));
	 * <notes>// true</notes>
	 * Testing.printlnObject(ClassUtil.isInstanceOf(tiger, Animal.class));
	 * <notes>// true</notes>
	 * Testing.printlnObject(ClassUtil.isInstanceOf(animal, Animal.class));
	 * <notes>// false</notes>
	 * Testing.printlnObject(ClassUtil.isInstanceOf(animal, Tiger.class));
	 * <notes>// false</notes>
	 * Testing.printlnObject(ClassUtil.isInstanceOf(10086, int.class));
	 * <notes>// true</notes>
	 * Testing.printlnObject(ClassUtil.isInstanceOf(10086, Integer.class));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static boolean isInstanceOf(Object testInstance, Class<?> targetClass){
		if(testInstance instanceof Class){
			return isInstanceOf((Class<?>)testInstance, targetClass);
		}
		return targetClass.isInstance(testInstance);
	}
	
	/**
	 * <p><des> 判定参数2所表示的类或接口是否与参数1所表示的类或接口相同, 或是否是其超类或超接口 </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * public class Tiger extends Animal {}
	 * <b><em>e.g.</em></b>
	 * <notes>// true</notes>
	 * Testing.printlnObject(ClassUtil.isInstanceOf(Tiger.class, Tiger.class));
	 * <notes>// true</notes>
	 * Testing.printlnObject(ClassUtil.isInstanceOf(Tiger.class, Animal.class));
	 * <notes>// false</notes>
	 * Testing.printlnObject(ClassUtil.isInstanceOf(Animal.class, Tiger.class));
	 * <notes>// true</notes>
	 * Testing.printlnObject(ClassUtil.isInstanceOf(String.class, Object.class));
	 * <notes>// false</notes>
	 * Testing.printlnObject(ClassUtil.isInstanceOf(Object.class, String.class));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static boolean isInstanceOf(Class<?> testClass, Class<?> targetClass){
		return targetClass.isAssignableFrom(testClass);
	}
	
	/**
	 * <p><des> 获取参数指定Class的父类Class列表 </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * public class Tiger extends Animal {}
	 * <b><em>e.g.</em></b>
	 * List&lt;Class&lt;?&gt;&gt; superClasses = ClassUtil.getSuperclasses(Tiger.class);
	 * Testing.printlnObject(superClasses);
	 * <b><em>output look like：</em></b>
	 * class fan.core.test.model.Animal
	 * class java.lang.Object
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static List<Class<?>> getSuperclasses(Class<?> clazz){
		List<Class<?>> classes = new ArrayList<Class<?>>();
		while((clazz = clazz.getSuperclass()) != null){
			classes.add(clazz);
		}
		return classes.size() == 0 ? null : classes;
	}
	
	/**
	 * <p><des> 获取泛型(第一个位置)参数化类型 </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * public class Pet &lt;E&gt; {
	 * 
	 *     public Pet(){
	 *         Testing.printlnObject(toString());
	 *         Class<?> clazz = ClassUtil.getSuperclassGenericType(this.getClass());
	 *         Testing.printlnObject(clazz);
	 *     }
	 * 
	 *     <b>@</b>Override
	 *     public String toString() {
	 *         return "I am not a pet!";
	 *     }
	 * 
	 * }
	 * 
	 * public class Parrot extends Pet&lt;Parrot&gt; {
	 * 
	 *     <b>@</b>Override
	 *     public String toString() {
	 *         return "I am a parrot, i can dance!";
	 *     }
	 * 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * new Parrot();
	 * <b><em>output look like：</em></b>
	 * I am a parrot, i can dance!
	 * class fan.core.test.model.Parrot
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.ClassUtil#getSuperclassGenericType(Class, int)
	 */
	public static Class<?> getSuperclassGenericType(Class<?> clazz){
		return getSuperclassGenericType(clazz, 0);
	}

	
	/**
	 * <p><des> 获取泛型(位置由参数指定, 索引从0开始)参数化类型 </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * public class Pet &lt;E&gt; {
	 * 
	 *     public Pet(){
	 *         Testing.printlnObject(toString());
	 *         Class<?> clazz = ClassUtil.getSuperclassGenericType(this.getClass(), 0);
	 *         Testing.printlnObject(clazz);
	 *     }
	 * 
	 *     <b>@</b>Override
	 *     public String toString() {
	 *         return "I am not a pet!";
	 *     }
	 * 
	 * }
	 * 
	 * public class Parrot extends Pet&lt;Parrot&gt; {
	 * 
	 *     <b>@</b>Override
	 *     public String toString() {
	 *         return "I am a parrot, i can dance!";
	 *     }
	 * 
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * new Parrot();
	 * <b><em>output look like：</em></b>
	 * I am a parrot, i can dance!
	 * class fan.core.test.model.Parrot
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static Class<?> getSuperclassGenericType(Class<?> clazz, int index){
		if(isInstanceOf(Object.class, clazz.getSuperclass())){
			throw new ExecutetimeException("clazz must has a superclass of generic type");
		}
		try {
			Type type = clazz.getGenericSuperclass();
	        Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
	        return (Class<?>) parameterizedType[index];
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}
	
	/**
	 * <p><des> 获取值的实际类型 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Object o1 = "";
	 * Object o2 = 2;
	 * Object o3 = false;
	 * Object o4 = "".getBytes();
	 * Testing.printlnObject(ClassUtil.getObjectTypes(o1, o2, o3, o4));
	 * <b><em>output look like：</em></b>
	 * class java.lang.String
	 * class java.lang.Integer
	 * class java.lang.Boolean
	 * class [B
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static Class<?>[] getObjectTypes(Object... items){
		if(items.length == 0){
			return null;
		}
		Class<?>[] classes = new Class<?>[items.length];
		for(int i = 0; i < items.length; i++){
			classes[i] = items[i].getClass();
		}
		return classes;
	}
	
	/**
	 * <p><des> 获取短类名 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * <notes>// Animal</notes>
	 * Testing.printlnObject(ClassUtil.getSimpleClassName(Animal.class));
	 * <notes>// Animal</notes>
	 * Testing.printlnObject(ClassUtil.getSimpleClassName(new Animal()));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String getSimpleClassName(Object obj){
		if(obj == null){
			return null;
		}
		if(obj instanceof Class){
			return ((Class<?>)obj).getSimpleName();
		}
		return obj.getClass().getSimpleName();
	}
	
}