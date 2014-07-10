package fan.core.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import fan.core.exception.ExecutetimeException;
import fan.core.util.code.SortCode;
/**
 * <p> <b> @描述：</b> 集合常用操作工具类
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-06-08
 * <p> <b> @since 0.1.0 </b>
 */
public class CollectionUtil {

	private CollectionUtil(){}
	
	/**
	 * <p><des> 判定集合是否为空 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * List&lt;String&gt; list1 = new ArrayList&lt;String&gt;();
	 * List&lt;String&gt; list2 = new ArrayList&lt;String&gt;();
	 * list2.add(null);
	 * <notes>// true</notes>
	 * Testing.printlnObject(CollectionUtil.isEmpty(null));
	 * <notes>// true</notes>
	 * Testing.printlnObject(CollectionUtil.isEmpty(list1));
	 * <notes>// false</notes>
	 * Testing.printlnObject(CollectionUtil.isEmpty(list2));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static boolean isEmpty(Collection<?> collection){
		return collection == null || collection.size() == 0;
	}
	
	/**
	 * <p><des> 判定集合是否不为空 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * List&lt;String&gt; list1 = new ArrayList&lt;String&gt;();
	 * List&lt;String&gt; list2 = new ArrayList&lt;String&gt;();
	 * list2.add(null);
	 * <notes>// false</notes>
	 * Testing.printlnObject(CollectionUtil.isNotEmpty(null));
	 * <notes>// false</notes>
	 * Testing.printlnObject(CollectionUtil.isNotEmpty(list1));
	 * <notes>// true</notes>
	 * Testing.printlnObject(CollectionUtil.isNotEmpty(list2));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static boolean isNotEmpty(Collection<?> collection){
		return !isEmpty(collection);
	}
	
	/**
	 * <p><des> 将集合转换为数组表示 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * List&lt;String&gt; list = new ArrayList&lt;String&gt;();
	 * list.add("fancore");
	 * list.add("util");
	 * String[] strings = ArrayUtil.asArray(list);
	 * Testing.printlnObject(strings);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <E> E[] toArray(Collection<E> collection){
		return ArrayUtil.asArray(collection);
	}
	
	/**
	 * <p><des> 将参数转换为List表示 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * List&lt;String&gt; list = CollectionUtil.asList("fan", "core", "util");
	 * Testing.printlnObject(list);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <E> List<E> asList(E... obj){
		return Arrays.asList(obj);
	}
	
	/**
	 * <p><des> 将参数转换为List表示 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Set&lt;String&gt; set = new HashSet&lt;String&gt;();
	 * set.add("fan");
	 * set.add("core");
	 * List&lt;String&gt; list = CollectionUtil.asList(set);
	 * Testing.printlnObject(list);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <E> List<E> asList(Collection<E> collection){
		if(collection == null){
			return null;
		}
		if(collection instanceof List){
			return (List<E>)collection;
		}
		return new ArrayList<E>(collection);
	}
	
	/**
	 * <p><des> 将参数转换为Set表示 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Set&lt;String&gt; set = CollectionUtil.asSet("fan", "core", "util");
	 * Testing.printlnObject(set);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <E> Set<E> asSet(E... object){
		return new HashSet<E>(Arrays.asList(object));
	}
	
	/**
	 * <p><des> 将参数转换为Set表示 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * List&lt;String&gt; list = new ArrayList&lt;String&gt;();
	 * list.add("fan");
	 * list.add("core");
	 * Set&lt;String&gt; set = CollectionUtil.asSet(set);
	 * Testing.printlnObject(set);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <E> Set<E> asSet(Collection<E> collection){
		if(collection == null){
			return null;
		}
		if(collection instanceof Set){
			return (Set<E>)collection;
		}
		return new HashSet<E>(collection);
	}
	
	/**
	 * <p><des> 返还一个新的ArrayList实例 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * List&lt;Map&lt;String, Set&lt;Object&gt;&gt;&gt; list = CollectionUtil.newList();
	 * Testing.printlnObject(list);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <E> List<E> newList(){
		return new ArrayList<E>();
	}
	
	/**
	 * <p><des> 返还一个新的ArrayList实例 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * List&lt;Map&lt;String, Set&lt;Object&gt;&gt;&gt; list = CollectionUtil.newList(16);
	 * Testing.printlnObject(list);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <E> List<E> newList(int size){
		return new ArrayList<E>(size);
	}
	
	/**
	 * <p><des> 返还一个新的HashSet实例 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Set&lt;Map&lt;String, List&lt;Object&gt;&gt;&gt; set = CollectionUtil.newSet();
	 * Testing.printlnObject(set);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <E> Set<E> newSet(){
		return new HashSet<E>(16, .75f);
	}
	
	/**
	 * <p><des> 返还一个新的HashSet实例 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Set&lt;Map&lt;String, List&lt;Object&gt;&gt;&gt; set = CollectionUtil.newSet(16, 0.75);
	 * Testing.printlnObject(set);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <E> Set<E> newSet(int initialCapacity, double loadFactor){
		return new HashSet<E>(initialCapacity, (float) loadFactor);
	}
	
	/**
	 * <p><des> 根据关键字排序集合, 关键字类型支持常用的数值类型, 字符类型, 布尔类型, 日期类型 </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class User {
	 * 
	 *     private int id;
	 *     private String sex;
	 *     private String name;
	 *     private Date registerTime;
	 *     private boolean valid;
	 * 
	 *     public User(int id, String name, String sex, boolean valid, Date registerTime){
	 *         this.id = id;
	 *         this.sex = sex;
	 *         this.name = name;
	 *         this.valid = valid;
	 *         this.registerTime = registerTime;
	 *     }
	 *     
	 *     <notes>// getter and setter method ....</notes>
	 * 
	 *     <b>@</b>Override
	 *     public String toString() {
	 *         StringBuilder builder = new StringBuilder();
	 *         builder.append(id).append("    ");
	 *         builder.append(name).append("    ");
	 *         builder.append(sex).append("    ");
	 *         builder.append(valid).append("    ");
	 *         builder.append(DateUtil.formatDate(registerTime, DateFormatCode.SHORT_HYPHEN.toCode()));
	 *         return builder.toString();
	 *     }
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * public static void main(String[] args) {
	 *     Date date = new Date();
	 *     List<User> users = new ArrayList<User>();
	 *     users.add(new User(3, "何国群", "男", true, DateUtil.getDateModifyYear(date, -6)));
	 *     users.add(new User(1, "杨晓婷", "女", true, DateUtil.getDateModifyYear(date, -8)));
	 *     users.add(new User(4, "杨忠杰", "男", false, DateUtil.getDateModifyYear(date, -4)));
	 *     users.add(new User(5, "叶水燕", "女", true, DateUtil.getDateModifyYear(date, -7)));
	 *     users.add(new User(2, "叶国珠", "女", false, DateUtil.getDateModifyYear(date, -3)));
	 *     Testing.printlnObject("------ origin ------");
	 *     Testing.printlnObject(users);
	 *     Testing.printlnObject("------ sorted ------");
	 *     CollectionUtil.sortByAsc(users, "id");
	 *     <notes>// CollectionUtil.sortByAsc(users, "sex");</notes>
	 *     <notes>// CollectionUtil.sortByAsc(users, "name");</notes>
	 *     <notes>// CollectionUtil.sortByAsc(users, "valid");</notes>
	 *     <notes>// CollectionUtil.sortByAsc(users, "registerTime");</notes>
	 *     Testing.printlnObject(users);
	 * }
	 * 
	 * <b><em>output look like：</em></b>
	 * <output>------ origin ------
	 * 3    何国群    男    true     2008-07-07    
	 * 1    杨晓婷    女    true     2006-07-07    
	 * 4    杨忠杰    男    false    2010-07-07    
	 * 5    叶水燕    女    true     2007-07-07    
	 * 2    叶国珠    女    false    2011-07-07    
	 * ------ sorted ------
	 * 1    杨晓婷    女    true     2006-07-07    
	 * 2    叶国珠    女    false    2011-07-07    
	 * 3    何国群    男    true     2008-07-07    
	 * 4    杨忠杰    男    false    2010-07-07    
	 * 5    叶水燕    女    true     2007-07-07</output>
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <E> void sortByAsc(Collection<E> collection, String key){
		sortBySortKey(collection, key, SortCode.ASC);
	}

	
	/**
	 * <p><des> 根据关键字排序集合, 关键字类型支持常用的数值类型, 字符类型, 布尔类型, 日期类型 </des></p>
	 * <p><pre>
	 * <b><em>Definition Class：</em></b>
	 * 
	 * public class User {
	 * 
	 *     private int id;
	 *     private String sex;
	 *     private String name;
	 *     private Date registerTime;
	 *     private boolean valid;
	 * 
	 *     public User(int id, String name, String sex, boolean valid, Date registerTime){
	 *         this.id = id;
	 *         this.sex = sex;
	 *         this.name = name;
	 *         this.valid = valid;
	 *         this.registerTime = registerTime;
	 *     }
	 *     
	 *     <notes>// getter and setter method ....</notes>
	 * 
	 *     <b>@</b>Override
	 *     public String toString() {
	 *         StringBuilder builder = new StringBuilder();
	 *         builder.append(id).append("    ");
	 *         builder.append(name).append("    ");
	 *         builder.append(sex).append("    ");
	 *         builder.append(valid).append("    ");
	 *         builder.append(DateUtil.formatDate(registerTime, DateFormatCode.SHORT_HYPHEN.toCode()));
	 *         return builder.toString();
	 *     }
	 * }
	 * 
	 * <b><em>e.g.</em></b>
	 * public static void main(String[] args) {
	 *     Date date = new Date();
	 *     List<User> users = new ArrayList<User>();
	 *     users.add(new User(3, "何国群", "男", true, DateUtil.getDateModifyYear(date, -6)));
	 *     users.add(new User(1, "杨晓婷", "女", true, DateUtil.getDateModifyYear(date, -8)));
	 *     users.add(new User(4, "杨忠杰", "男", false, DateUtil.getDateModifyYear(date, -4)));
	 *     users.add(new User(5, "叶水燕", "女", true, DateUtil.getDateModifyYear(date, -7)));
	 *     users.add(new User(2, "叶国珠", "女", false, DateUtil.getDateModifyYear(date, -3)));
	 *     Testing.printlnObject("------ origin ------");
	 *     Testing.printlnObject(users);
	 *     Testing.printlnObject("------ sorted ------");
	 *     CollectionUtil.sortByDesc(users, "id");
	 *     <notes>// CollectionUtil.sortByDesc(users, "sex");</notes>
	 *     <notes>// CollectionUtil.sortByDesc(users, "name");</notes>
	 *     <notes>// CollectionUtil.sortByDesc(users, "valid");</notes>
	 *     <notes>// CollectionUtil.sortByDesc(users, "registerTime");</notes>
	 *     Testing.printlnObject(users);
	 * }
	 * 
	 * <b><em>output look like：</em></b>
	 * <output>------ origin ------
	 * 3    何国群    男    true     2008-07-07    
	 * 1    杨晓婷    女    true     2006-07-07    
	 * 4    杨忠杰    男    false    2010-07-07    
	 * 5    叶水燕    女    true     2007-07-07    
	 * 2    叶国珠    女    false    2011-07-07    
	 * ------ sorted ------   
	 * 5    叶水燕    女    true     2007-07-07   
	 * 4    杨忠杰    男    false    2010-07-07     
	 * 3    何国群    男    true     2008-07-07  
	 * 2    叶国珠    女    false    2011-07-07
	 * 1    杨晓婷    女    true     2006-07-07</output>
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <E> void sortByDesc(Collection<E> collection, String key){
		sortBySortKey(collection, key, SortCode.DESC);
	}
	
	/** <p><des> 根据排序关键字排序集合 </des></p> */
	@SuppressWarnings("unchecked")
	private static <E> void sortBySortKey(Collection<E> collection, String key, SortCode sortKey){
		if(isEmpty(collection)) return ;
		Object[] source = ArrayUtil.asArray(collection);
		Class<E> entityClass = (Class<E>) collection.iterator().next().getClass(); // e type
		SimpleComparator comparator = new SimpleComparator(entityClass, key, sortKey);
		quicksort(source, 0, source.length - 1, comparator);
		collection.clear(); // clear origin
		collection.addAll((List<E>)asList(source)); // refill collection
	}
	
	/** <p><des> 快速排序算法 </des></p> */
	private static void quicksort(Object[] array, int low,int hight, SimpleComparator comparator){
		if(low < hight){
			int position = partition(array, low, hight, comparator);
			quicksort(array, low, position - 1, comparator);
			quicksort(array, position + 1, hight, comparator);
		}
	}

	/** <p><des> 快速排序算法 </des></p> */
	private static int partition(Object[] array, int low,int hight, SimpleComparator comparator){
		Object key = array[low];
		while(low < hight){
			while(low < hight && comparator.compare(array[hight], key) >= 0){
				hight--;
			}
			array[low] = array[hight];
			while(low < hight && comparator.compare(array[low], key) <= 0){
				low++;
			}
			array[hight] = array[low];
		}
		array[low] = key;
		return low;
	}
	
	/**
	 * <p> <b> @描述：</b> 比较器。提供 compare 方法比较两关键字的大小
	 * <p> <b> @作者：</b> fancore
	 * <p> <b> @邮箱：</b> fancore@126.com
	 * <p> <b> @日期：</b> 2014-06-08
	 */
	static class SimpleComparator {

		// the key word
		private String key;
		// the type of key word
		private KeyType keyType;
		// asc or desc
		private boolean isAsc;
		
		public SimpleComparator(Class<?> entityClass, String key, SortCode sortKey){
			this.key = key;
			isAsc = SortCode.equalsCode(sortKey, SortCode.ASC);
			this.keyType = KeyType.valueOf(FieldUtil.getFieldType(entityClass, key));
		}

		/** <p><des> 根据关键字的类型比较关键字值的大小 </des></p> */
		public int compare(Object o1, Object o2){
			switch (keyType) {
				case NUMBER :
					return numberCompare(value(o1), value(o2));
				case STRING :
					return StringCompare(value(o1), value(o2));
				case CHAR:
					return charCompare(value(o1), value(o2));
				case BOOL:
					return boolCompare(value(o1), value(o2));
				case DATE :
					return dateCompare(value(o1), value(o2));
			}
			throw new ExecutetimeException(key + " field type is not supported for sorting.");
		}

		/** <p><des> 比较两个数值类型关键字的大小 </des></p> */
		private int numberCompare(Object o1, Object o2) {
			BigDecimal n1 = new BigDecimal(o1.toString());
			BigDecimal n2 = new BigDecimal(o2.toString());
			return isAsc ? n1.compareTo(n2) : n2.compareTo(n1);
		}

		/** <p><des> 比较两个字符类串型关键字的大小 </des></p> */
		private int StringCompare(Object o1, Object o2) {
			String s1 = StringUtil.getStringISO1(StringUtil.getBytesGBK(o1.toString()));
			String s2 = StringUtil.getStringISO1(StringUtil.getBytesGBK(o2.toString()));
			return isAsc ? s1.compareTo(s2) : s2.compareTo(s1);
		}

		/** <p><des> 比较两个字符类型关键字的大小 </des></p> */
		private int charCompare(Object o1, Object o2) {
			char c1 = (Character) o1;
			char c2 = (Character) o2;
			return isAsc ? c1 - c2 : c2 - c1;
		}

		/** <p><des> 比较两个布尔类型关键字的大小 </des></p> */
		private int boolCompare(Object o1, Object o2) {
			Boolean b1 = (Boolean) o1;
			Boolean b2 = (Boolean) o2;
			return isAsc ? b1.compareTo(b2) : b2.compareTo(b1);
		}

		/** <p><des> 比较两个日期类型关键字的大小 </des></p> */
		private int dateCompare(Object o1, Object o2) {
			return isAsc ? ((Date)o1).compareTo((Date)o2) : ((Date)o2).compareTo((Date)o1);
		}

		/** <p><des> 获取关键字的值 </des></p> */
		private Object value(Object o) {
			return FieldUtil.getFieldValue(o, key);
		}
		
		/**
		 * <p> <b> @描述：</b> 支持排序的关键字类型
		 * NUMBER(数值类型), STRING(字符类型), CHAR(字符类型), BOOL(布尔类型), DATE(日期类型)
		 * <p> <b> @作者：</b> fancore
		 * <p> <b> @邮箱：</b> fancore@126.com
		 * <p> <b> @日期：</b> 2014-06-08
		 */
		enum KeyType {
			
			NUMBER, STRING, CHAR, BOOL, DATE, UNDEFINED;
			
			// number type
			private static final String[] numberContainer = {
				"byte", "short", "int", "long", "float", "double",
				"Byte", "Short", "Integer", "Long", "Float", "Double"
			};

			/** <p><des> 根据关键字类型确定比较类型 </des></p> */
			public static KeyType valueOf(Class<?> type){
				String name = type.getSimpleName();
				if(ArrayUtil.contains(numberContainer, name)){
					return NUMBER;
				}else if(name.equals("String")){
					return STRING;
				}else if(name.equalsIgnoreCase("boolean")){
					return BOOL;
				}else if(name.equals("char")){
					return CHAR;
				}else if(name.equals("Date")){
					return DATE;
				}else{
					return UNDEFINED;
				}
			}
		}
	}
}