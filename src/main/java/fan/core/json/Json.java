package fan.core.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fan.core.exception.ExecutetimeException;
import fan.core.util.code.DateFormatCode;
/**
 * <p> <b> @描述：</b> Json 相关常用操作
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-07-09
 * <p> <b> @since 0.2.0 </b>
 */
public class Json {

	// date type format pattern
	private String dateFormat;
	// serialize null or not
	private boolean serializeNulls;
	// gson builder
	private GsonBuilder gsonBuilder;
	// exclusion strategy
	private JsonExcluder exclusions;
	
	public Json() {
		this(true);
	}
	
	private Json(boolean serializeNulls){
		if(serializeNulls){
			serializeNulls();
		}else{
			disableSerializeNulls();
		}
	}
	
	/**
	 * <p><des> 获取预设的Json实例 </des></p>
	 * @return Json
	 * @since 0.2.0
	 */
	public static Json getDefault(){
		return new Json(true).setDateFormat(DateFormatCode.SHORT_HYPHEN.toCode());
	}
	
	/**
	 * <p><des> 序列化对象, 若需要以键值对的形式序列化, 可以通过在外部构造Map作为参数 </des></p>
	 * @param object  需要序列化的对象
	 * @return  序列化结果的字符串值
	 * @since 0.2.0
	 */
	public String toJson(Object object){
		return createGson().toJson(object);
	}
	
	/**
	 * <p><des> 序列化对象, 以键值对的形式 </des></p>
	 * @param key  键值
	 * @param value  需要序列化的对象
	 * @return  序列化结果的字符串值
	 * @see fan.core.json.Json#toJson(Object)
	 * @since 0.2.0
	 */
	public String toJson(String key, Object value){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		return toJson(map);
	}
	
	/**
	 * <p><des> 反序列化JSON字符串 </des></p>
	 * @param json  需要进行反序列化的JSON字符串
	 * @param pojoClass  反序列化成为的类
	 * @return  反序列化成为的对象
	 * @since 0.2.0
	 */
	public <E> E fromJson(String json, Class<E> pojoClass){
		return createGson().fromJson(json, pojoClass);
	}
	
	/**
	 * <p><des> 反序列化JSON字符串 </des></p>
	 * @param json  需要进行反序列化的JSON字符串
	 * @param typeToken  反序列化成为的类型. eg. new TypeToken&lt;List&lt;Pojo&gt;&gt;(){}
	 * @return  反序列化成为的对象
	 * @since 0.2.0
	 */
	public <E> E fromJson(String json, TypeToken<E> typeToken){
		return createGson().fromJson(json, typeToken.getType());
	}
	
	/**
	 * <p><des> 写出JSON字符串 </des></p>
	 * @param value  需要序列化成JSON字符串写出的对象
	 * @param response  HttpServletResponse
	 * @see fan.core.json.Json#toJson(Object)
	 * @see fan.core.json.Json#toWriteOut(String, HttpServletResponse)
	 * @since 0.2.0
	 */
	public void toWriteOut(Object value, HttpServletResponse response) {
		Json.toWriteOut(toJson(value), response);
	}
	
	/**
	 * <p><des> 写出JSON字符串 </des></p>
	 * @param key  键值
	 * @param value  需要序列化成JSON字符串写出的对象
	 * @param response  HttpServletResponse
	 * @see fan.core.json.Json#toJson(String, Object)
	 * @see fan.core.json.Json#toWriteOut(String, HttpServletResponse)
	 * @since 0.2.0
	 */
	public void toWriteOut(String key, Object value, HttpServletResponse response) {
		Json.toWriteOut(toJson(key, value), response);
	}
	
	/**
	 * <p><des> 写出JSON字符串 </des></p>
	 * @param json  需要写出的JSON字符串
	 * @param response  HttpServletResponse
	 * @since 0.2.0
	 */
	public static void toWriteOut(String json, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = null;
		try {
			out = response.getWriter();
	        out.write(json);
	        out.flush();
		} catch (IOException e) {
			throw new ExecutetimeException(e);
		} finally {
			if(out != null){
				out.close();
			}
		}
	}

	/**
	 * <p><des> 设定日期类型序列化的格式 </des></p>
	 * @param pattern  日期格式
	 * @return  当前Json对象
	 * @see fan.core.util.code.DateFormatCode
	 * @since 0.2.0
	 */
	public Json setDateFormat(String pattern) {
		this.dateFormat = pattern;
		return this;
	}

	/**
	 * <p><des> 设定对象序列化时需要排除的字段属性或类型 </des></p>
	 * @param exclusions  需要排除的字段属性或类型. eg. "time" or Date.class
	 * @return  当前Json对象
	 * @since 0.2.0
	 */
	public Json setExclusions(Object... exclusions) {
		this.exclusions = new JsonExcluder(exclusions);
		return this;
	}

	/**
	 * <p><des> 设定对象序列化时值为null的字段属性也一起序列化 </des></p>
	 * @return  当前Json对象
	 * @since 0.2.0
	 */
	public Json serializeNulls() {
		this.serializeNulls = true;
		return init();
	}

	/**
	 * <p><des> 设定对象序列化时值为null的字段属性不序列化 </des></p>
	 * @return  当前Json对象
	 * @since 0.2.0
	 */
	public Json disableSerializeNulls() {
		this.serializeNulls = false;
		return init();
	}
	
	// init
	private Json init(){
		gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		gsonBuilder.disableHtmlEscaping();
		gsonBuilder.enableComplexMapKeySerialization();
		if(serializeNulls){
			gsonBuilder.serializeNulls();
		}
		return this;
	}
	
	// create gson
	private Gson createGson(){
		gsonBuilder.setDateFormat(dateFormat);
		if(exclusions != null){
			gsonBuilder.setExclusionStrategies(exclusions);
		}
		return gsonBuilder.create();
	}
	
}