package fan.core.util.code;
/**
 * <p> <b> @描述：</b> 性别类型枚举
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-07-01
 * <p> <b> @since 0.1.0 </b>
 */
public enum SexCode implements Code {

	/**
	 * 男
	 */
	MALE("男"), 

	/**
	 * 女
	 */
	FEMALE("女"), 

	/**
	 * 保密
	 */
	SECRET("保密");
	
	private final String code;
	
	private SexCode(String code){
		this.code = code;
	}
	
	/**
	 * <p><des> 以字面或代号获取相应的枚举类型实例 </des></p>
	 * @since 0.1.0
	 */
	public static SexCode fromCode(String code){
		if(code == null || code.length() == 0) 
			return null;
		for(SexCode sexCode : SexCode.values()){
			if(sexCode.code.equals(code) || sexCode.name().equals(code)){
				return sexCode;
			}
		}
		return null;
	}
	
	/**
	 * <p><des> 判定两个实例是否是相等 </des></p>
	 * @since 0.1.0
	 */
	public static boolean equalsCode(SexCode e1, SexCode e2){
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