package fan.core.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
/**
 * <p> <b> @描述：</b> 排除策略
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-07-09
 * <p> <b> @since 0.2.0 </b>
 */
public class JsonExcluder implements ExclusionStrategy {

	// types or fields
	private Object[] exclusions;
	
	// default access authority
	JsonExcluder(Object[] exclusions){
		this.exclusions = exclusions;
	}
	
	/**
	 * <p><des> 忽略类型 </des></p>
	 * @since 0.2.0
	 */
	@Override
	public boolean shouldSkipClass(Class<?> skipClass) {
		for(Object item : exclusions){
			if(item == skipClass){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <p><des> 忽略字段属性 </des></p>
	 * @since 0.2.0
	 */
	@Override
	public boolean shouldSkipField(FieldAttributes skipField) {
		String fieldName = skipField.getName();
		for(Object item : exclusions){
			if(item.equals(fieldName)){
				return true;
			}
		}
		return false;
	}
	
}