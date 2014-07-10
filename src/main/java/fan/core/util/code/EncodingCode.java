package fan.core.util.code;
/**
 * <p> <b> @描述：</b> 字符编码集类型枚举
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-07-04
 * <p> <b> @since 0.1.0 </b>
 */
public enum EncodingCode implements Code {

	/**
	 * GBK
	 */
	GBK("GBK"), 

	/**
	 * GB2312
	 */
	GB2312("GB2312"), 

	/**
	 * UTF-8
	 */
	UTF_8("UTF-8"), 

	/**
	 * UTF-16
	 */
	UTF_16("UTF-16"), 

	/**
	 * US-ASCII
	 */
	ASCII("US-ASCII"), 

	/**
	 * ISO-8859-1
	 */
	ISO_8859_1("ISO-8859-1");
	
	private final String code;
	
	private EncodingCode(String code){
		this.code = code;
	}
	
	/**
	 * <p><des> 以字面或代号获取相应的枚举类型实例 </des></p>
	 * @since 0.1.0
	 */
	public static EncodingCode fromCode(String code){
		if(code == null || code.length() == 0) 
			return null;
		for(EncodingCode encodingCode : EncodingCode.values()){
			if(encodingCode.code.equals(code) || encodingCode.name().equals(code)){
				return encodingCode;
			}
		}
		return null;
	}
	
	/**
	 * <p><des> 判定两个实例是否是相等 </des></p>
	 * @since 0.1.0
	 */
	public static boolean equalsCode(EncodingCode e1, EncodingCode e2){
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