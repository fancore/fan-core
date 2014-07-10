package fan.core.util.code;
/**
 * <p> <b> @描述：</b> 月份类型枚举
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-07-01
 * <p> <b> @since 0.1.0 </b>
 */
public enum MonthCode implements Code {

	/**
	 * 一月
	 */
	Jan("一月"), 

	/**
	 * 二月
	 */
	Feb("二月"), 

	/**
	 * 三月
	 */
	Mar("三月"), 

	/**
	 * 四月
	 */
	Apr("四月"), 

	/**
	 * 五月
	 */
	May("五月"), 

	/**
	 * 六月
	 */
	Jun("六月"), 

	/**
	 * 七月
	 */
	Jul("七月"), 

	/**
	 * 八月
	 */
	Aug("八月"), 

	/**
	 * 九月
	 */
	Sep("九月"), 

	/**
	 * 十月
	 */
	Oct("十月"), 

	/**
	 * 十一月
	 */
	Nov("十一月"), 

	/**
	 * 十二月
	 */
	Dec("十二月");
	
	private final String code;
	
	private MonthCode(String code){
		this.code = code;
	}
	
	/**
	 * <p><des> 以字面或代号获取相应的枚举类型实例 </des></p>
	 * @since 0.1.0
	 */
	public static MonthCode fromCode(String code){
		if(code == null || code.length() == 0) 
			return null;
		for(MonthCode monthCode : MonthCode.values()){
			if(monthCode.code.equals(code) || monthCode.name().equals(code)){
				return monthCode;
			}
		}
		return null;
	}
	
	/**
	 * <p><des> 以实际值获取相应的枚举类型实例 </des></p>
	 * @since 0.1.0
	 */
	public static MonthCode fromCode(int code){
		if(code < Jan.ordinal() || code > Dec.ordinal()) 
			return null;
		for(MonthCode monthCode : MonthCode.values()){
			if(monthCode.ordinal() == code){
				return monthCode;
			}
		}
		return null;
	}
	
	/**
	 * <p><des> 获取实例的实际值 </des></p>
	 * @since 0.1.0
	 */
	public int intValue(){
		return this.ordinal();
	}
	
	/**
	 * <p><des> 判定两个实例是否是相等 </des></p>
	 * @since 0.1.0
	 */
	public static boolean equalsCode(MonthCode e1, MonthCode e2){
		return e1.ordinal() == e2.ordinal();
	}

	@Override
	public String toFace(){
		return this.name();
	}

	@Override
	public String toCode() {
		return code;
	}
}