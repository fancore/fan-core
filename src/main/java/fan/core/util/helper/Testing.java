package fan.core.util.helper;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import fan.core.exception.ExecutetimeException;
/**
 * <p> <b> @描述：</b> 测试常用操作工具类
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-06-29
 * <p> <b> @since 0.1.0 </b>
 */
public class Testing {
	
	private Testing(){}
	
	/**
	 * <p><des> 输出对象(不换行)。支持 Collection, Map, Iterator, Pojo, 数组 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Testing.printObject(Map);
	 * Testing.printObject(Set);
	 * Testing.printObject(List);
	 * Testing.printObject(POJO);
	 * Testing.printObject(Array);
	 * Testing.printObject(Object);
	 * Testing.printObject(Iterator);
	 * Testing.printObject(Collection);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.helper.Testing#printlnObject(Object)
	 */
	public static void printObject(Object object){
		printObject(object, false);
	}
	
	/**
	 * <p><des> 输出对象(换行)。支持 Collection, Map, Iterator, Pojo, 数组 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Testing.printlnObject(Map);
	 * Testing.printlnObject(Set);
	 * Testing.printlnObject(List);
	 * Testing.printlnObject(POJO);
	 * Testing.printlnObject(Array);
	 * Testing.printlnObject(Object);
	 * Testing.printlnObject(Iterator);
	 * Testing.printlnObject(Collection);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.helper.Testing#printObject(Object)
	 */
	public static void printlnObject(Object object){
		printObject(object, true);
	}
	
	/**
	 * <p><des> 使用操作系统工具打开文件 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Testing.openFile(new File("src/test/java/image.jpg"));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static void openFile(File file){
		if(file == null){
			System.err.println("[ERROR] File must not be null");
		}else {
			System.out.println("Opening " + file.getAbsolutePath() + ", please wait.");
			try {
				Desktop.getDesktop().open(file);
			} catch (IOException e) {
				throw new ExecutetimeException(e);
			}
		}
	}
	
	/** 是否换行输出对象 */
	private static void printObject(Object object, boolean isNewLine){
		if(object instanceof Collection){
			printCollection((Collection<?>)object, isNewLine);
		}else if(object instanceof Iterator){
			printIterator((Iterator<?>)object, isNewLine);
		}else if(object instanceof Map){
			printMap((Map<?, ?>)object, isNewLine);
		}else if(object != null && object.getClass().isArray()){
			printArray(object, isNewLine);
		}else{
			printOut(object, isNewLine, true);
		}
	}
	
	/** 输出Collection */
	private static void printCollection(Collection<?> collection, boolean isNewLine){
		int length = collection.size(), counts = 0;
		if(length == 0){
			printEmpty(); return ;
		}
		for(Object o : collection){
			printOut(o, isNewLine, ++counts == length);
		}
	}
	
	/** 输出Iterator */
	private static void printIterator(Iterator<?> it, boolean isNewLine){
		if(!it.hasNext()){
			printEmpty(); return ;
		}
		while(it.hasNext()){
			printOut(it.next(), isNewLine, it.hasNext());
		}
	}

	/** 输出Map */
	private static void printMap(Map<?, ?> map, boolean isNewLine){
		int length = map.size(), counts = 0;
		if(length == 0){
			printEmpty(); return ;
		}
		for(Object key : map.keySet()){
			printOut(key + " : " + map.get(key), isNewLine, ++counts == length);
		}
	}
	
	/** 输出Array */
	private static void printArray(Object object, boolean isNewLine){
		int length = Array.getLength(object);
		if(!isNewLine) System.out.print("[");
		for(int i = 0; i < length - 1; i++){
			printOut(Array.get(object, i), isNewLine, false);
		}
		System.out.print(Array.get(object, length - 1));
		if(!isNewLine) {
			System.out.println("]");
		}else{
			System.out.println("");
		} 
	}
	
	/** 处理是否换行以及最后一个元素的输出方式 */
	private static void printOut(Object object, boolean isNewLine, boolean isLastItem){
		if(isNewLine || isLastItem){
			System.out.println(object);
		}else{
			System.out.print(object + ", ");
		}
	}
	
	/** 容器对象为空的时候输出的信息 */
	private static void printEmpty(){
		System.err.println("----->>> The object argument is empty <<<-----");
	}
}