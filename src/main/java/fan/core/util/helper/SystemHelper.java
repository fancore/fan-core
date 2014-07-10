package fan.core.util.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import fan.core.util.ArrayUtil;
import fan.core.util.code.SystemCode;
/**
 * <p> <b> @描述：</b> 系统参数助手工具类
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-07-04
 * <p> <b> @since 0.1.0 </b>
 */
public class SystemHelper {
	
	private SystemHelper(){}
	
	/**
	 * <p><des> 获取项目文件的编码集 </des></p>
	 * @since 0.1.0
	 */
	public static String getSystemFileEncoding(){
		return getProperty(SystemCode.FILE_ENCODING);
	}
	
	/**
	 * <p><des> 获取项目类路径目录列表 </des></p>
	 * @since 0.1.0
	 */
	public static String[] getClassPathDir(){
		List<String> dirList = new ArrayList<String>();
		String[] paths = getProperty(SystemCode.CLASS_PATH_DIR).split(";");
		for(String path : paths){
			if(new File(path).isDirectory()){
				dirList.add(path);
			}
		}
		return ArrayUtil.asArray(dirList);
	}
	
	/**
	 * <p><des> 获取项目目录路径 </des></p>
	 * @since 0.1.0
	 */
	public static String getProjectDir(){
		return getProperty(SystemCode.USER_DIR);
	}
	
	/**
	 * <p><des> 获取系统参数值 </des></p>
	 * @since 0.1.0
	 * @see fan.core.util.code.SystemCode
	 */
	public static String getProperty(SystemCode systemCode){
		return System.getProperty(systemCode.toCode());
	}
	
}