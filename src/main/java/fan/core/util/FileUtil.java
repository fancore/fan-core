package fan.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import fan.core.exception.ExecutetimeException;
/**
 * <p> <b> @描述：</b> 文件常用操作工具类
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-07-08
 * <p> <b> @since 0.1.0 </b>
 */
public class FileUtil {

	private FileUtil(){}
	
	private static final int BUFFER_SIZE = 1024 * 1024 / 2;
	
	/**
	 * <p><des> 文件拷贝, 方法调用结束或抛出异常, 都将关闭输入输出流, 输出流flush </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * InputStream in = FileUtil.getFileInputStream("src/test/java/image.jpg");
	 * OutputStream out = FileUtil.getFileOutputStream("src/test/java/fan/core/util/test/img.jpg");
	 * <notes>// please press F5 to refresh fan.core.util.test</notes>
	 * FileUtil.copyFile(in, out);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static void copyFile(InputStream in, OutputStream out){
		try {
			byte[] buffer = new byte[BUFFER_SIZE];
			int read;
			while((read = in.read(buffer)) != -1){
				out.write(buffer, 0, read);
			}
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		} finally {
			closeStream(in, out);
		}
	}
	
	/**
	 * <p><des> 读取文件 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String result = FileUtil.readFile("src/test/java/json");
	 * Testing.printlnObject(result);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String readFile(String pathname){
		return readFile(new File(pathname));
	}
	
	/**
	 * <p><des> 读取文件 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String result = FileUtil.readFile(new File("src/test/java/json"));
	 * Testing.printlnObject(result);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String readFile(File file){
		String[] contents = readLineFile(file);
		StringBuilder builder = new StringBuilder();
		for(String content : contents){
			builder.append(content);
		}
		return builder.toString();
	}
	
	/**
	 * <p><des> 读取文件, 文件的每一行作为数组的一个元素 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String[] lines = FileUtil.readLineFile("src/test/java/data.json");
	 * Testing.printlnObject(lines);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String[] readLineFile(String pathname){
		return readLineFile(new File(pathname));
	}
	
	/**
	 * <p><des> 读取文件, 文件的每一行作为数组的一个元素 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String[] lines = FileUtil.readLineFile(new File("src/test/java/data.json"));
	 * Testing.printlnObject(lines);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String[] readLineFile(File file){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			LinkedList<String> list = new LinkedList<String>();
			String read;
			while((read = reader.readLine()) != null){
				list.add(read);
			}
			return ArrayUtil.asArray(list);
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		} finally {
			closeStream(reader);
		}
	}
	
	/**
	 * <p><des> 获取目录下所有的文件对象 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * List<File> list = FileUtil.listFiles(new File("src/test/java"));
	 * Testing.printlnObject(list);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static List<File> listFiles(File directory){
		return listFiles(directory, null);
	}
	
	/**
	 * <p><des> 获取目录下所有由参数filter指定类型的文件对象 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * List<File> list = FileUtil.listFiles(new File("src/test/java"), ".java");
	 * Testing.printlnObject(list);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static List<File> listFiles(File source, String filter){
		File[] fileList = source.listFiles();
		List<File> list = new ArrayList<File>();
		filter = filter == null ? null : filter.toLowerCase();
		if(fileList != null && fileList.length > 0){
			for(File file : fileList){
				if(file.isFile()){
					add(list, file, filter);
				}else if(file.isDirectory()){
					list.addAll(listFiles(file, filter));
				}
			}
		}else if(source.isFile()){
			add(list, source, filter);
		}
		return list;
	}
	
	/**
	 * <p><des> 在参数指定的目录中查找文件, 若查找的到, 则返还该文件对象, 若查找不到, 则返还null </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * File file = FileUtil.findFile("src/test", "FileUtilTest.java");
	 * Testing.printlnObject(file);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static File findFile(String dirpath, String filename){
		return findFile(new File(dirpath), filename);
	}
	
	/**
	 * <p><des> 在参数指定的目录中查找文件, 若查找的到, 则返还该文件对象, 若查找不到, 则返还null </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * File file = FileUtil.findFile(new File("src/test"), "FileUtilTest.java");
	 * Testing.printlnObject(file);
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static File findFile(File directory, String filename){
		String filter = StringUtil.substringAfterLastWith(filename, ".");
		List<File> files = listFiles(directory, filter);
		if(files.size() == 0) return null;
		for(File file : files){
			if(file.getAbsolutePath().endsWith(filename)){
				return file;
			}
		}
		return null;
	}
	
	/**
	 * <p><des> 删除文件或目录 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * FileUtil.delete(new File("src/test/java/temp"));
	 * @since 0.1.0
	 */
	public static void delete(File file){
		if(file.isFile()){
			file.delete();
		}else if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File item : files){
				delete(item);
			}
		}
		file.delete();
	}
	
	/**
	 * <p><des> 创建目录, 若目录不存在则创建, 若存在则直接返还true </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * FileUtil.createDir("src/test/java/temp/tempdir");
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static boolean createDir(String pathname){
		return createDir(new File(pathname));
	}
	
	/**
	 * <p><des> 创建目录, 若目录不存在则创建, 若存在则直接返还true </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * FileUtil.createDir(new File("src/test/java/temp/tempdir"));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static boolean createDir(File dir){
		if(!dir.exists()){
			return dir.mkdirs();
		}
		return true;
	}
	
	/** 添加文件到列表 */
	private static void add(List<File> list, File file, String filter){
		if(filter == null){
			list.add(file);
		}else if(file.getAbsolutePath().toLowerCase().endsWith(filter)){
			list.add(file);
		}
	}
	
	/**
	 * <p><des> 关闭输入输出流, 并flush输出流 </des></p>
	 * @since 0.1.0
	 */
	public static void closeStream(Object... streams){
		if(streams.length == 0) return ;
		for(Object stream : streams){
			if(stream != null){
				try {
					MethodUtil.invokeMethod(stream, "flush");
				} catch (ExecutetimeException e) {
					
				} finally {
					MethodUtil.invokeMethod(stream, "close");
				}
			}
		}
	}
	
	/**
	 * <p><des> 获取FileInputStream实例 </des></p>
	 * @since 0.1.0
	 */
	public static FileInputStream getFileInputStream(String pathname){
		return getFileInputStream(new File(pathname));
	}
	
	/**
	 * <p><des> 获取FileInputStream实例 </des></p>
	 * @since 0.1.0
	 */
	public static FileInputStream getFileInputStream(File file){
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new ExecutetimeException(e);
		}
	}
	
	/**
	 * <p><des> 获取FileOutputStream实例 </des></p>
	 * @since 0.1.0
	 */
	public static FileOutputStream getFileOutputStream(String pathname){
		return getFileOutputStream(new File(pathname), false);
	}
	
	/**
	 * <p><des> 获取FileOutputStream实例 </des></p>
	 * @since 0.1.0
	 */
	public static FileOutputStream getFileOutputStream(String pathname, boolean append){
		return getFileOutputStream(new File(pathname), append);
	}
	
	/**
	 * <p><des> 获取FileOutputStream实例 </des></p>
	 * @since 0.1.0
	 */
	public static FileOutputStream getFileOutputStream(File file){
		return getFileOutputStream(file, false);
	}
	
	/**
	 * <p><des> 获取FileOutputStream实例 </des></p>
	 * @since 0.1.0
	 */
	public static FileOutputStream getFileOutputStream(File file, boolean append){
		try {
			return new FileOutputStream(file, append);
		} catch (FileNotFoundException e) {
			throw new ExecutetimeException(e);
		}
	}
	
}