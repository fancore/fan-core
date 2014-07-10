package fan.core.util.code;
/**
 * <p> <b> @描述：</b> 布尔类型枚举
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-07-01
 * <p> <b> @since 0.1.0 </b>
 */
public enum BoolCode implements Code {

	/**
	 * T
	 */
	TRUE("T"), 
	
	/**
	 * F
	 */
	FALSE("F");
	
	private final String code;
	
	private BoolCode(String code){
		this.code = code;
	}
	
	/**
	 * <p><des> 以字面或代号获取相应的枚举类型实例 </des></p>
	 * @since 0.1.0
	 */
	public static BoolCode fromCode(String code){
		if(code == null || code.length() == 0) 
			return null;
		for(BoolCode boolCode : BoolCode.values()){
			if(boolCode.code.equals(code) || boolCode.name().equals(code)){
				return boolCode;
			}
		}
		return null;
	}
	
	/**
	 * <p><des> 以布尔值获取相应的枚举类型实例 </des></p>
	 * @since 0.1.0
	 */
	public static BoolCode fromCode(boolean code){
		return code ? TRUE : FALSE;
	}
	
	/**
	 * <p><des> 获取实例的实际值 </des></p>
	 * @since 0.1.0
	 */
	public boolean boolValue(){
		return this.ordinal() == 0;
	}
	
	/**
	 * <p><des> 判定两个实例是否是相等 </des></p>
	 * @since 0.1.0
	 */
	public static boolean equalsCode(BoolCode e1, BoolCode e2){
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