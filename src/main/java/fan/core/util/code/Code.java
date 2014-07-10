package fan.core.util.code;
/**
 * <p> <b> @描述：</b> 枚举接口
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-07-01
 * <p> <b> @since 0.1.0 </b>
 */
public interface Code {

	/**
	 * <p><des> 枚举类型的字面字符串 </des></p>
	 * @since 0.1.0
	 */
	String toFace();
	
	/**
	 * <p><des> 枚举实例代号 </des></p>
	 * @since 0.1.0
	 */
	String toCode();
	
}