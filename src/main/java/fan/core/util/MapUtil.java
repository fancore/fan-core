package fan.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import fan.core.exception.ExecutetimeException;
/**
 * <p> <b> @描述：</b> 散列表常用操作工具类
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-06-10
 * <p> <b> @since 0.1.0 </b>
 */
public class MapUtil {

	private MapUtil(){}
	
	/**
	 * <p><des> 判定散列表是否为空 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Map&lt;String, Object&gt; map1 = new HashMap&lt;String, Object&gt;();
	 * Map&lt;String, Object&gt; map2 = new HashMap&lt;String, Object&gt;();
	 * map2.put(null, null);
	 * <notes>// true</notes>
	 * Testing.printlnObject(MapUtil.isEmpty(null));
	 * <notes>// true</notes>
	 * Testing.printlnObject(MapUtil.isEmpty(map1));
	 * <notes>// false</notes>
	 * Testing.printlnObject(MapUtil.isEmpty(map2));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static boolean isEmpty(Map<?, ?> map){
		return map == null || map.isEmpty() ? true : false;
	}
	
	/**
	 * <p><des> 判定散列表是否不为空 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Map&lt;String, Object&gt; map1 = new HashMap&lt;String, Object&gt;();
	 * Map&lt;String, Object&gt; map2 = new HashMap&lt;String, Object&gt;();
	 * map2.put(null, null);
	 * <notes>// false</notes>
	 * Testing.printlnObject(MapUtil.isNotEmpty(null));
	 * <notes>// false</notes>
	 * Testing.printlnObject(MapUtil.isNotEmpty(map1));
	 * <notes>// true</notes>
	 * Testing.printlnObject(MapUtil.isNotEmpty(map2));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static boolean isNotEmpty(Map<?, ?> map){
		return !isEmpty(map);
	}
	
	/**
	 * <p><des> 根据Value查找Key, 要求Value在散列表中是唯一的 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Map&lt;String, Object&gt; map = new HashMap&lt;String, Object&gt;();
	 * map.put("key1", "value1");
	 * map.put("key2", "value2");
	 * map.put("key3", "value3");
	 * String key = MapUtil.findKey(map, "value2");
	 * Testing.printlnObject(key);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <K, V> K findKey(Map<K, V> map, V value){
		if(!map.containsValue(value)){
			return null;
		}
		K key = null;
		int counts = 0;
		for(K k : map.keySet()){
			if(map.get(k).equals(value)){
				key = k;
				counts++;
			}
		}
		if(counts > 1){
			throw new ExecutetimeException("found different key have the same value, the value must be unique");
		}
		return key;
	}
	
	/**
	 * <p><des> 对调 Key&Value, 要求Value在散列表中是唯一的 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Map&lt;String, Object&gt; map1 = new HashMap&lt;String, Object&gt;();
	 * map1.put("key1", "value1");
	 * map1.put("key2", "value2");
	 * map1.put("key3", "value3");
	 * Map<Object, String> map2 = MapUtil.reverseKeyValue(map1);
	 * Testing.printlnObject(map2);
	 * <b><em>output look like：</em></b>
	 * <output>value3 : key3
	 * value1 : key1
	 * value2 : key2</output>
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <K, V> Map<V, K> reverseKeyValue(Map<K, V> map){
		Map<V, K> mapping = new HashMap<V, K>();
		V value;
		for(K key : map.keySet()){
			value = map.get(key);
			if(mapping.containsKey(value)){
				throw new ExecutetimeException("found different key have the same value, the value must be unique");
			}
			mapping.put(value, key);
		}
		return mapping;
	}
	
	/**
	 * <p><des> 创建一个新的初始容量为16, 加载因子为0.75的Map实例 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Map&lt;String, Set&lt;List&lt;Object&gt;&gt;&gt; map = MapUtil.newMap();
	 * Testing.printlnObject(map);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.MapUtil#newMap(int, double)
	 */
	public static <K, V> Map<K, V> newMap(){
		return new HashMap<K, V>(16, .75f);
	}

	
	/**
	 * <p><des> 创建一个新的Map实例 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Map&lt;String, Set&lt;List&lt;Object&gt;&gt;&gt; map = MapUtil.newMap(16, 0.75);
	 * Testing.printlnObject(map);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <K, V> Map<K, V> newMap(int initialCapacity, double loadFactor){
		return new HashMap<K, V>(initialCapacity, (float)loadFactor);
	}
	
	/**
	 * <p><des> Map的Key作为一个List </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Map&lt;String, Object&gt; map = new HashMap&lt;String, Object&gt;();
	 * map.put("key1", "value1");
	 * map.put("key2", "value2");
	 * map.put("key3", "value3");
	 * List<String> keys = MapUtil.mapKeyAsList(map);
	 * Testing.printlnObject(keys);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <K> List<K> mapKeyAsList(Map<K, ?> map){
		return new ArrayList<K>(map.keySet());
	}
	
	/**
	 * <p><des> Map的Value作为一个List </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Map&lt;String, Object&gt; map = new HashMap&lt;String, Object&gt;();
	 * map.put("key1", "value1");
	 * map.put("key2", "value2");
	 * map.put("key3", "value3");
	 * List<Object> values = MapUtil.mapValueAsList(map);
	 * Testing.printlnObject(values);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static <V> List<V> mapValueAsList(Map<?, V> map){
		return new ArrayList<V>(map.values());
	}
	
}