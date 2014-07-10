package fan.core.util.code;
/**
 * <p> <b> @描述：</b> 日期格式类型枚举
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-07-03
 * <p> <b> @since 0.1.0 </b>
 */
public enum DateFormatCode implements Code {

	/**
	 * yyyMMdd
	 */
	SHORT("yyyMMdd"), 

	/**
	 * yyyy-MM-dd
	 */
	SHORT_HYPHEN("yyyy-MM-dd"), 

	/**
	 * yyyy/MM/dd
	 */
	SHORT_VIRGULE("yyyy/MM/dd"), 

	/**
	 * yyyMMddHHmmss
	 */
	LONG("yyyMMddHHmmss"), 

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	LONG_HYPHEN("yyyy-MM-dd HH:mm:ss"), 

	/**
	 * yyyy/MM/dd HH:mm:ss
	 */
	LONG_VIRGULE("yyyy/MM/dd HH:mm:ss"), 

	/**
	 * yyyMMddHHmmssSSS
	 */
	FULL("yyyMMddHHmmssSSS"), 

	/**
	 * yyyy-MM-dd HH:mm:ss:SSS
	 */
	FULL_HYPHEN("yyyy-MM-dd HH:mm:ss:SSS"), 

	/**
	 * yyyy/MM/dd HH:mm:ss:SSS
	 */
	FULL_VIRGULE("yyyy/MM/dd HH:mm:ss:SSS");
	
	private final String code;
	
	private DateFormatCode(String code){
		this.code = code;
	}
	
	/**
	 * <p><des> 以字面或代号获取相应的枚举类型实例 </des></p>
	 * @since 0.1.0
	 */
	public static DateFormatCode fromCode(String code){
		if(code == null || code.length() == 0) 
			return null;
		for(DateFormatCode dateFormatCode : DateFormatCode.values()){
			if(dateFormatCode.code.equals(code) || dateFormatCode.name().equals(code)){
				return dateFormatCode;
			}
		}
		return null;
	}
	
	/**
	 * <p><des> 判定两个实例是否是相等 </des></p>
	 * @since 0.1.0
	 */
	public static boolean equalsCode(DateFormatCode e1, DateFormatCode e2){
		return e1.ordinal() == e2.ordinal();
	}

	@Override
	public String toFace(){
		return this.name();
	}

	@Override
	public String toCode(){
		return code;
	}
	
}