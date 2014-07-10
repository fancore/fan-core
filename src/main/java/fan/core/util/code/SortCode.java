package fan.core.util.code;
/**
 * <p> <b> @描述：</b> 排序类型枚举
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-07-02
 * <p> <b> @since 0.1.0 </b>
 */
public enum SortCode implements Code {

	/**
	 * asc
	 */
	ASC("asc"), 

	/**
	 * desc
	 */
	DESC("desc");
	
	private final String code;
	
	private SortCode(String code){
		this.code = code;
	}
	
	/**
	 * <p><des> 以字面或代号获取相应的枚举类型实例 </des></p>
	 * @since 0.1.0
	 */
	public static SortCode fromCode(String code){
		if(code == null || code.length() == 0) 
			return null;
		for(SortCode sortCode : SortCode.values()){
			if(sortCode.code.equals(code) || sortCode.name().equals(code)){
				return sortCode;
			}
		}
		return null;
	}
	
	/**
	 * <p><des> 判定两个实例是否是相等 </des></p>
	 * @since 0.1.0
	 */
	public static boolean equalsCode(SortCode e1, SortCode e2){
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