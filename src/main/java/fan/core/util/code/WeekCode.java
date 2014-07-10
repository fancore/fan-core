package fan.core.util.code;
/**
 * <p> <b> @描述：</b> 周类型枚举
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-07-01
 * <p> <b> @since 0.1.0 </b>
 */
public enum WeekCode implements Code {

	/**
	 * 星期六
	 */
	SAT("星期六"), 

	/**
	 * 星期天
	 */
	SUN("星期天"), 

	/**
	 * 星期一
	 */
	MON("星期一"), 

	/**
	 * 星期二
	 */
	TUE("星期二"), 

	/**
	 * 星期三
	 */
	WED("星期三"), 

	/**
	 * 星期四
	 */
	THU("星期四"), 

	/**
	 * 星期五
	 */
	FRI("星期五");
	
	private final String code;
	
	private WeekCode(String code){
		this.code = code;
	}
	
	/**
	 * <p><des> 以字面或代号获取相应的枚举类型实例 </des></p>
	 * @since 0.1.0
	 */
	public static WeekCode fromCode(String code){
		if(code == null || code.length() == 0) 
			return null;
		for(WeekCode weekCode : WeekCode.values()){
			if(weekCode.code.equals(code) || weekCode.name().equals(code)){
				return weekCode;
			}
		}
		return null;
	}
	
	/**
	 * <p><des> 以实际值获取相应的枚举类型实例 </des></p>
	 * @since 0.1.0
	 */
	public static WeekCode fromCode(int code){
		if(code < SAT.ordinal() || code > FRI.ordinal()) 
			return null;
		for(WeekCode weekCode : WeekCode.values()){
			if(weekCode.ordinal() == code){
				return weekCode;
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
	public static boolean equalsCode(WeekCode e1, WeekCode e2){
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