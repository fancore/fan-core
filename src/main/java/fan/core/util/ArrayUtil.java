package fan.core.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
/**
 * <p> <b> @描述：</b> 数组常用操作工具类
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-06-03
 * <p> <b> @since 0.1.0 </b>
 */
public class ArrayUtil {
	
	private ArrayUtil(){}
	
	/**
	 * <p><des> 将参数转换为数组表示 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String[] strings;
	 * strings = ArrayUtil.asArray("fan", "core", "util");
	 * Testing.printObject(strings);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <E> E[] asArray(E... object){
		return object;
	}

	/**
	 * <p><des> 将参数转换为数组表示 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * List&lt;String&gt; list = new ArrayList&lt;String&gt;();
	 * list.add("fancore");
	 * list.add("util");
	 * String[] strings = ArrayUtil.asArray(list);
	 * Testing.printObject(strings);
	 * </pre></p>
	 * @since 0.1.0
	 */
	@SuppressWarnings("unchecked")
	public static <E> E[] asArray(Collection<E> collection){
		if(collection == null || collection.size() == 0){
			return null;
		}
		Class<E> type = (Class<E>)collection.iterator().next().getClass();
		return collection.toArray((E[])Array.newInstance(type, collection.size()));
	}
	
	/**
	 * <p><des> 判定数组元素中是否包含参数对象, 特别的, 如果数组为null, 则永远返回false </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String[] strings = {"fan", "core", "util"};
	 * <notes>// true</notes>
	 * Testing.printlnObject(ArrayUtil.contains(strings, "core"));
	 * <notes>// false</notes>
	 * Testing.printlnObject(ArrayUtil.contains(strings, "fancore"));
	 * <notes>// false</notes>
	 * Testing.printlnObject(ArrayUtil.contains(strings, null));
	 * <notes>// false</notes>
	 * Testing.printlnObject(ArrayUtil.contains(null, "fancore"));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <E> boolean contains(E[] origin, E target){
		if(origin == null){
			return false;
		}
		if(target == null){
			for(E e : origin){
				if(e == null) return true;
			}
		}else{
			if(origin.getClass().getComponentType().isInstance(target)){
				for(E e : origin){
					if(e == target || e.equals(target)) return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * <p><des> 判定数组元素中是否包含参数值, 特别的, 如果数组为null, 则永远返回false </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * byte[] bytes = {1, 2, 8};
	 * <notes>// true</notes>
	 * Testing.printlnObject(ArrayUtil.contains(bytes, (byte)2));
	 * <notes>// false</notes>
	 * Testing.printlnObject(ArrayUtil.contains(bytes, (byte)3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static boolean contains(byte[] origin, byte target){
		if(origin == null){
			return false;
		}
		for(byte item : origin){
			if(item == target) return true;
		}
		return false;
	}
	
	/**
	 * <p><des> 判定数组元素中是否包含参数值, 特别的, 如果数组为null, 则永远返回false </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * char[] ch = {'a', 'b', 'd'};
	 * <notes>// true</notes>
	 * Testing.printlnObject(ArrayUtil.contains(ch, 'a'));
	 * <notes>// false</notes>
	 * Testing.printlnObject(ArrayUtil.contains(ch, 'c'));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static boolean contains(char[] origin, char target){
		if(origin == null){
			return false;
		}
		for(char item : origin){
			if(item == target) return true;
		}
		return false;
	}
	
	/**
	 * <p><des> 判定数组元素中是否包含参数值, 特别的, 如果数组为null, 则永远返回false </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * short[] sh = {1, 2, 5, 9, 8};
	 * <notes>// true</notes>
	 * Testing.printlnObject(ArrayUtil.contains(sh, (short)2));
	 * <notes>// false</notes>
	 * Testing.printlnObject(ArrayUtil.contains(sh, (short)3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static boolean contains(short[] origin, short target){
		if(origin == null){
			return false;
		}
		for(short item : origin){
			if(item == target) return true;
		}
		return false;
	}
	
	/**
	 * <p><des> 判定数组元素中是否包含参数值, 特别的, 如果数组为null, 则永远返回false </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * int[] ints = {1, 2, 5, 9, 8};
	 * <notes>// true</notes>
	 * Testing.printlnObject(ArrayUtil.contains(ints, 2));
	 * <notes>// false</notes>
	 * Testing.printlnObject(ArrayUtil.contains(ints, 3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static boolean contains(int[] origin, int target){
		if(origin == null){
			return false;
		}
		for(int item : origin){
			if(item == target) return true;
		}
		return false;
	}
	
	/**
	 * <p><des> 判定数组元素中是否包含参数值, 特别的, 如果数组为null, 则永远返回false </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * long[] lon = {1, 2, 5, 9, 8};
	 * <notes>// true</notes>
	 * Testing.printlnObject(ArrayUtil.contains(lon, 2));
	 * <notes>// false</notes>
	 * Testing.printlnObject(ArrayUtil.contains(lon, 3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static boolean contains(long[] origin, long target){
		if(origin == null){
			return false;
		}
		for(long item : origin){
			if(item == target) return true;
		}
		return false;
	}
	
	/**
	 * <p><des> 判定数组元素中是否包含参数值, 特别的, 如果数组为null, 则永远返回false </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * float[] flo = {1, 2.0f, 5.3f, 9, 8};
	 * <notes>// true</notes>
	 * Testing.printlnObject(ArrayUtil.contains(flo, 2.0f));
	 * <notes>// false</notes>
	 * Testing.printlnObject(ArrayUtil.contains(flo, 3.0f));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static boolean contains(float[] origin, float target){
		if(origin == null){
			return false;
		}
		for(float item : origin){
			if(item == target) return true;
		}
		return false;
	}
	
	/**
	 * <p><des> 判定数组元素中是否包含参数值, 特别的, 如果数组为null, 则永远返回false </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * double[] dou = {1, 2.1, 5.3, 9, 8};
	 * <notes>// true</notes>
	 * Testing.printlnObject(ArrayUtil.contains(dou, 2.1));
	 * <notes>// false</notes>
	 * Testing.printlnObject(ArrayUtil.contains(dou, 3.1));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static boolean contains(double[] origin, double target){
		if(origin == null){
			return false;
		}
		for(double item : origin){
			if(item == target) return true;
		}
		return false;
	}

	/**
	 * <p><des> 判定数组元素中是否包含参数值, 特别的, 如果数组为null, 则永远返回false </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * boolean[] bool = {true, true, true};
	 * <notes>// true</notes>
	 * Testing.printlnObject(ArrayUtil.contains(bool, true));
	 * <notes>// false</notes>
	 * Testing.printlnObject(ArrayUtil.contains(bool, false));
	 * </pre></p>
	 */
	public static boolean contains(boolean[] origin, boolean target){
		if(origin == null){
			return false;
		}
		for(boolean item : origin){
			if(item == target) return true;
		}
		return false;
	}
	
	/**
	 * <p><des> 获取数组元素的数据类型, 特别的, 如果数组为null, 则返回null </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Object[] objects = new Object[2];
	 * String[] strings = {"fan", "core", "util"};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.getArrayElementType(null));
	 * <notes>// class java.lang.String</notes>
	 * Testing.printlnObject(ArrayUtil.getArrayElementType(strings));
	 * <notes>// class java.lang.Object</notes>
	 * Testing.printlnObject(ArrayUtil.getArrayElementType(objects));
	 * </pre></p>
	 * @since 0.1.0
	 */
	@SuppressWarnings("unchecked")
	public static <E> Class<E> getArrayElementType(E[] origin){
		return origin == null ? null : (Class<E>) origin.getClass().getComponentType();
	}
	
	/**
	 * <p><des> 返还一个表示该数组的一个字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Object[] objects = null;
	 * Double[] doubles = new Double[2];
	 * String[] strings = {"fan", "core", "util"};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.toString(objects));
	 * <notes>// [null, null]</notes>
	 * Testing.printlnObject(ArrayUtil.toString(doubles));
	 * <notes>// [fan, core, util]</notes>
	 * Testing.printlnObject(ArrayUtil.toString(strings));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toString(Object[] origin){
		return Arrays.toString(origin);
	}
	
	/**
	 * <p><des> 返还一个表示该数组的一个字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * byte[] bytes1 = null;
	 * byte[] bytes2 = new byte[2];
	 * byte[] bytes3 = {1, 2, 7};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.toString(bytes1));
	 * <notes>// [0, 0]</notes>
	 * Testing.printlnObject(ArrayUtil.toString(bytes2));
	 * <notes>// [1, 2, 7]</notes>
	 * Testing.printlnObject(ArrayUtil.toString(bytes3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toString(byte[] origin){
		return Arrays.toString(origin);
	}

	
	/**
	 * <p><des> 返还一个表示该数组的一个字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * char[] chars1 = null;
	 * char[] chars2 = new char[2];
	 * char[] chars3 = {'a', 'b', 'c'};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.toString(chars1));
	 * <notes>// [, ]</notes>
	 * Testing.printlnObject(ArrayUtil.toString(chars2));
	 * <notes>// [a, b, c]</notes>
	 * Testing.printlnObject(ArrayUtil.toString(chars3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toString(char[] origin){
		return Arrays.toString(origin);
	}
	
	/**
	 * <p><des> 返还一个表示该数组的一个字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * short[] shorts1 = null;
	 * short[] shorts2 = new short[2];
	 * short[] shorts3 = {1, 2, 7};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.toString(shorts1));
	 * <notes>// [0, 0]</notes>
	 * Testing.printlnObject(ArrayUtil.toString(shorts2));
	 * <notes>// [1, 2, 7]</notes>
	 * Testing.printlnObject(ArrayUtil.toString(shorts3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toString(short[] origin){
		return Arrays.toString(origin);
	}
	
	/**
	 * <p><des> 返还一个表示该数组的一个字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * int[] ints1 = null;
	 * int[] ints2 = new int[2];
	 * int[] ints3 = {1, 2, 7};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.toString(ints1));
	 * <notes>// [0, 0]</notes>
	 * Testing.printlnObject(ArrayUtil.toString(ints2));
	 * <notes>// [1, 2, 7]</notes>
	 * Testing.printlnObject(ArrayUtil.toString(ints3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toString(int[] origin){
		return Arrays.toString(origin);
	}
	
	/**
	 * <p><des> 返还一个表示该数组的一个字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * long[] longs1 = null;
	 * long[] longs2 = new long[2];
	 * long[] longs3 = {1, 2, 7};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.toString(longs1));
	 * <notes>// [0, 0]</notes>
	 * Testing.printlnObject(ArrayUtil.toString(longs2));
	 * <notes>// [1, 2, 7]</notes>
	 * Testing.printlnObject(ArrayUtil.toString(longs3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toString(long[] origin){
		return Arrays.toString(origin);
	}
	
	/**
	 * <p><des> 返还一个表示该数组的一个字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * float[] floats1 = null;
	 * float[] floats2 = new float[2];
	 * float[] floats3 = {1.1f, 2.2f, 7.7f};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.toString(floats1));
	 * <notes>// [0.0, 0.0]</notes>
	 * Testing.printlnObject(ArrayUtil.toString(floats2));
	 * <notes>// [1.1, 2.2, 7.7]</notes>
	 * Testing.printlnObject(ArrayUtil.toString(floats3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toString(float[] origin){
		return Arrays.toString(origin);
	}
	
	/**
	 * <p><des> 返还一个表示该数组的一个字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * double[] doubles1 = null;
	 * double[] doubles2 = new double[2];
	 * double[] doubles3 = {1.1, 2.2, 7.7};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.toString(doubles1));
	 * <notes>// [0.0, 0.0]</notes>
	 * Testing.printlnObject(ArrayUtil.toString(doubles2));
	 * <notes>// [1.1, 2.2, 7.7]</notes>
	 * Testing.printlnObject(ArrayUtil.toString(doubles3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toString(double[] origin){
		return Arrays.toString(origin);
	}
	
	/**
	 * <p><des> 返还一个表示该数组的一个字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * boolean[] booleans1 = null;
	 * boolean[] booleans2 = new boolean[2];
	 * boolean[] booleans3 = {true, false, false};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.toString(booleans1));
	 * <notes>// [false, false]</notes>
	 * Testing.printlnObject(ArrayUtil.toString(booleans2));
	 * <notes>// [true, false, false]</notes>
	 * Testing.printlnObject(ArrayUtil.toString(booleans3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toString(boolean[] origin){
		return Arrays.toString(origin);
	}
	
	/**
	 * <p><des> 返还一个表示该数组的一个字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Object[] objects = null;
	 * Double[] doubles = new Double[2];
	 * String[] strings = {"fan", "core", "util"};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(objects));
	 * <notes>// null, null</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(doubles));
	 * <notes>// fan, core, util</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(strings));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toSimpleString(Object[] origin){
		if(origin == null) return "null";
		String string = Arrays.toString(origin);
		return string.substring(1, string.length() - 1);
	}

	/**
	 * <p><des> 返还一个表示该数组的一个字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * byte[] bytes1 = null;
	 * byte[] bytes2 = new byte[2];
	 * byte[] bytes3 = {1, 2, 7};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(bytes1));
	 * <notes>// 0, 0</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(bytes2));
	 * <notes>// 1, 2, 7</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(bytes3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toSimpleString(byte[] origin){
		if(origin == null) return "null";
		String string = Arrays.toString(origin);
		return string.substring(1, string.length() - 1);
	}

	/**
	 * <p><des> 返还一个表示该数组的一个字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * char[] chars1 = null;
	 * char[] chars2 = new char[2];
	 * char[] chars3 = {'a', 'b', 'c'};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(chars1));
	 * <notes>// , </notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(chars2));
	 * <notes>// a, b, c</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(chars3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toSimpleString(char[] origin){
		if(origin == null) return "null";
		String string = Arrays.toString(origin);
		return string.substring(1, string.length() - 1);
	}

	/**
	 * <p><des> 返还一个表示该数组的一个字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * short[] shorts1 = null;
	 * short[] shorts2 = new short[2];
	 * short[] shorts3 = {1, 2, 7};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(shorts1));
	 * <notes>// 0, 0</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(shorts2));
	 * <notes>// 1, 2, 7</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(shorts3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toSimpleString(short[] origin){
		if(origin == null) return "null";
		String string = Arrays.toString(origin);
		return string.substring(1, string.length() - 1);
	}

	/**
	 * <p><des> 返还一个表示该数组的一个字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * int[] ints1 = null;
	 * int[] ints2 = new int[2];
	 * int[] ints3 = {1, 2, 7};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(ints1));
	 * <notes>// 0, 0</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(ints2));
	 * <notes>// 1, 2, 7</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(ints3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toSimpleString(int[] origin){
		if(origin == null) return "null";
		String string = Arrays.toString(origin);
		return string.substring(1, string.length() - 1);
	}

	/**
	 * <p><des> 返还一个表示该数组的一个字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * long[] longs1 = null;
	 * long[] longs2 = new long[2];
	 * long[] longs3 = {1, 2, 7};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(longs1));
	 * <notes>// 0, 0</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(longs2));
	 * <notes>// 1, 2, 7</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(longs3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toSimpleString(long[] origin){
		if(origin == null) return "null";
		String string = Arrays.toString(origin);
		return string.substring(1, string.length() - 1);
	}

	/**
	 * <p><des> 返还一个表示该数组的一个字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * float[] floats1 = null;
	 * float[] floats2 = new float[2];
	 * float[] floats3 = {1.1f, 2.2f, 7.7f};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(floats1));
	 * <notes>// 0.0, 0.0</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(floats2));
	 * <notes>// 1.1, 2.2, 7.7</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(floats3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toSimpleString(float[] origin){
		if(origin == null) return "null";
		String string = Arrays.toString(origin);
		return string.substring(1, string.length() - 1);
	}

	/**
	 * <p><des> 返还一个表示该数组的一个字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * double[] doubles1 = null;
	 * double[] doubles2 = new double[2];
	 * double[] doubles3 = {1.1, 2.2, 7.7};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(doubles1));
	 * <notes>// 0.0, 0.0</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(doubles2));
	 * <notes>// 1.1, 2.2, 7.7</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(doubles3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toSimpleString(double[] origin){
		if(origin == null) return "null";
		String string = Arrays.toString(origin);
		return string.substring(1, string.length() - 1);
	}

	/**
	 * <p><des> 返还一个表示该数组的一个字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * boolean[] booleans1 = null;
	 * boolean[] booleans2 = new boolean[2];
	 * boolean[] booleans3 = {true, false, false};
	 * <notes>// null</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(booleans1));
	 * <notes>// false, false</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(booleans2));
	 * <notes>// true, false, false</notes>
	 * Testing.printlnObject(ArrayUtil.toSimpleString(booleans3));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toSimpleString(boolean[] origin){
		if(origin == null) return "null";
		String string = Arrays.toString(origin);
		return string.substring(1, string.length() - 1);
	}
}