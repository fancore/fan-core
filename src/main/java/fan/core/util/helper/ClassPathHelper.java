package fan.core.util.helper;

import java.io.File;
import fan.core.util.FileUtil;
import fan.core.util.StringUtil;
/**
 * <p> <b> @描述：</b> 类路径助手工具类
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-07-08
 * <p> <b> @since 0.1.0 </b>
 */
public class ClassPathHelper {
	
	private ClassPathHelper(){}
	private static final int INDEX_NOT_FOUND = -1;

	/**
	 * <p><des> 获取类路径下的文件, 若类路径下找不到与参数指定的文件名相匹配的文件, 则返还null </des></p>
	 * @since 0.1.0
	 */
	public static File getClassPathFile(String filename){
		String name = StringUtil.substringAfter(filename, ":");
		filename = name == null ? filename : name;
		String basedir = SystemHelper.getProjectDir();
		String[] classpathDirs = SystemHelper.getClassPathDir();
		File result;
		for(String classpathDir : classpathDirs){
			if(classpathDir.indexOf(basedir) != INDEX_NOT_FOUND){
				result = FileUtil.findFile(classpathDir, filename);
				if(result != null) return result;
			}
		}
		return null;
	}
	
}