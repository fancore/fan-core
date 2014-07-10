package fan.core.util.code;
/**
 * <p> <b> @描述：</b> 日期类型枚举
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-07-01
 * <p> <b> @since 0.1.0 </b>
 */
public enum DateCode implements Code {

	/**
	 * 年
	 */
	YEAR("年"), 

	/**
	 * 月
	 */
	MONTH("月"), 

	/**
	 * 周
	 */
	WEEK("周"), 

	/**
	 * 日
	 */
	DAY("日");
	
	private final String code;
	
	private DateCode(String code){
		this.code = code;
	}
	
	/**
	 * <p><des> 以字面或代号获取相应的枚举类型实例 </des></p>
	 * @since 0.1.0
	 */
	public static DateCode fromCode(String code){
		if(code == null || code.length() == 0) 
			return null;
		for(DateCode dateCode : DateCode.values()){
			if(dateCode.code.equals(code) || dateCode.name().equals(code)){
				return dateCode;
			}
		}
		return null;
	}
	
	/**
	 * <p><des> 判定两个实例是否是相等 </des></p>
	 * @since 0.1.0
	 */
	public static boolean equalsCode(DateCode e1, DateCode e2){
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