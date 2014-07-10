package fan.core.util.code;
/**
 * <p> <b> @描述：</b> 系统参数类型枚举
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-07-04
 * <p> <b> @since 0.1.0 </b>
 */
public enum SystemCode implements Code {
	
	/**
	 * os.name
	 */
	OS_NAME("os.name"), 

	/**
	 * user.name
	 */
	USER_NAME("user.name"), 

	/**
	 * user.home
	 */
	USER_HOME("user.home"), 

	/**
	 * user.dir
	 */
	USER_DIR("user.dir"), 

	/**
	 * java.version
	 */
	JAVA_VERSION("java.version"), 

	/**
	 * java.vm.name
	 */
	JAVA_VM_NAME("java.vm.name"), 

	/**
	 * java.io.tmpdir
	 */
	JAVA_IO_TMPDIR("java.io.tmpdir"), 

	/**
	 * java.class.path
	 */
	CLASS_PATH_DIR("java.class.path"), 

	/**
	 * file.encoding
	 */
	FILE_ENCODING("file.encoding");

	private String code;
	
	private SystemCode(String code){
		this.code = code;
	}
	
	@Override
	public String toFace() {
		return this.name();
	}

	@Override
	public String toCode() {
		return code;
	}
	
}