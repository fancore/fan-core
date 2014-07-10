package fan.core.util;

import java.nio.charset.Charset;
import fan.core.util.code.EncodingCode;
/**
 * <p><des> <b> @描述：</b> 封装了字符串相关操作的工具类
 * <p><des> <b> @作者：</b> fancore
 * <p><des> <b> @邮箱：</b> fancore@126.com
 * <p><des> <b> @日期：</b> 2014-06-07
 * <p> <b> @since 0.1.0 </b>
 */
public class StringUtil {

	private static final String PLACEHOLDER = "\\?";
	private static final int INDEX_NOT_FOUND = -1;
	
	private StringUtil(){}
	
	/**
	 * <p><des> 判断对象是否为空 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * StringUtil.isEmpty("") = true
	 * StringUtil.isEmpty(null) = true
	 * StringUtil.isEmpty(" ") = false
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static boolean isEmpty(String source) {
		return source == null || source.length() == 0;
	}
	
	/**
	 * <p><des> 判断对象是否不为空 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * StringUtil.isNotEmpty("") = false
	 * StringUtil.isNotEmpty(null) = false
	 * StringUtil.isNotEmpty(" ") = true
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static boolean isNotEmpty(String source) {
		return !isEmpty(source);
	}
	
	/**
	 * <p><des> 分割字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String source = "An explorer is a friend to all, be it plants or fish or tiny mole.";
	 * Testing.printlnObject(StringUtil.substring(source, 3));
	 * Testing.printlnObject(StringUtil.substring(source, -10));
	 * <b><em>output look like：</em></b>
	 * <output>explorer is a friend to all, be it plants or fish or tiny mole.
	 * tiny mole.</output>
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.StringUtil#substring(String, int, int)
	 */
	public static String substring(String source, int beginIndex){
		return substring(source, beginIndex, source.length());
	}
	
	/**
	 * <p><des> 分割字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String source = "An explorer is a friend to all, be it plants or fish or tiny mole.";
	 * Testing.printlnObject(StringUtil.substring(source, 3, -10));
	 * Testing.printlnObject(StringUtil.substring(source, -5, -1));
	 * <b><em>output look like：</em></b>
	 * <output>explorer is a friend to all, be it plants or fish or 
	 * mole</output>
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.StringUtil#substring(String, int)
	 */
	public static String substring(String source, int beginIndex, int endIndex){
		int length = source.length();
		if(beginIndex < 0){
			beginIndex += length;
		}
		if(endIndex < 0){
			endIndex += length;
		}
		return source.substring(beginIndex, endIndex);
	}
	
	/**
	 * <p><des> 切割源串, 索引从0开始到源串中参数串第一次出现的位置结束, 不包括参数串。
	 *          如果源串中不含有参数子串, 则返回null </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String source = "An explorer is a friend to all, be it plants or fish or tiny mole.";
	 * Testing.printlnObject(StringUtil.substringBefore(source, " or "));
	 * <b><em>output look like：</em></b>
	 * <output>An explorer is a friend to all, be it plants</output>
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.StringUtil#substringBeforeWith(String, String)
	 * @see fan.core.util.StringUtil#substringBeforeLast(String, String)
	 * @see fan.core.util.StringUtil#substringBeforeLastWith(String, String)
	 */
	public static String substringBefore(String source, String substring) {
		int index = source.indexOf(substring);
		return index == INDEX_NOT_FOUND ? null : source.substring(0, index);
	}
	
	/**
	 * <p><des> 切割源串, 索引从0开始到源串中参数串第一次出现的位置结束, 包括参数串。
	 *          如果源串中不含有参数子串, 则返回null </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String source = "An explorer is a friend to all, be it plants or fish or tiny mole.";
	 * Testing.printlnObject(StringUtil.substringBeforeWith(source, " or "));
	 * <b><em>output look like：</em></b>
	 * <output>An explorer is a friend to all, be it plants or </output>
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.StringUtil#substringBefore(String, String)
	 * @see fan.core.util.StringUtil#substringBeforeLast(String, String)
	 * @see fan.core.util.StringUtil#substringBeforeLastWith(String, String)
	 */
	public static String substringBeforeWith(String source, String substring) {
		int index = source.indexOf(substring);
		return index == INDEX_NOT_FOUND ? null : source.substring(0, index + substring.length());
	}
	
	/**
	 * <p><des> 切割源串, 索引从0开始到源串中参数串最后一次出现的位置结束, 不包括参数串。
	 *          如果源串中不含有参数子串, 则返回null </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String source = "An explorer is a friend to all, be it plants or fish or tiny mole.";
	 * Testing.printlnObject(StringUtil.substringBeforeLast(source, " or "));
	 * <b><em>output look like：</em></b>
	 * <output>An explorer is a friend to all, be it plants or fish</output>
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.StringUtil#substringBefore(String, String)
	 * @see fan.core.util.StringUtil#substringBeforeWith(String, String)
	 * @see fan.core.util.StringUtil#substringBeforeLastWith(String, String)
	 */
	public static String substringBeforeLast(String source, String substring) {
		int index = source.lastIndexOf(substring);
		return index == INDEX_NOT_FOUND ? null : source.substring(0, index);
	}
	
	/**
	 * <p><des> 切割源串, 索引从0开始到源串中参数串最后一次出现的位置结束, 包括参数串。
	 *          如果源串中不含有参数子串, 则返回null </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String source = "An explorer is a friend to all, be it plants or fish or tiny mole.";
	 * Testing.printlnObject(StringUtil.substringBeforeLastWith(source, " or "));
	 * <b><em>output look like：</em></b>
	 * <output>An explorer is a friend to all, be it plants or fish or </output>
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.StringUtil#substringBefore(String, String)
	 * @see fan.core.util.StringUtil#substringBeforeWith(String, String)
	 * @see fan.core.util.StringUtil#substringBeforeLast(String, String)
	 */
	public static String substringBeforeLastWith(String source, String substring) {
		int index = source.lastIndexOf(substring);
		return index == INDEX_NOT_FOUND ? null : source.substring(0, index + substring.length());
	}
	
	/**
	 * <p><des> 切割源串, 索引从源串中参数串第一次出现的位置开始到源串最后结束, 不包括参数串。
	 *          如果源串中不含有参数子串, 则返回null </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String source = "An explorer is a friend to all, be it plants or fish or tiny mole.";
	 * Testing.printlnObject(StringUtil.substringAfter(source, " or "));
	 * <b><em>output look like：</em></b>
	 * <output>fish or tiny mole.</output>
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.StringUtil#substringAfterWith(String, String)
	 * @see fan.core.util.StringUtil#substringAfterLast(String, String)
	 * @see fan.core.util.StringUtil#substringAfterLastWith(String, String)
	 */
	public static String substringAfter(String source, String substring) {
		int index = source.indexOf(substring);
		return index == INDEX_NOT_FOUND ? null : source.substring(index + substring.length());
	}
	
	/**
	 * <p><des> 切割源串, 索引从源串中参数串第一次出现的位置开始到源串最后结束, 包括参数串。
	 *          如果源串中不含有参数子串, 则返回null </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String source = "An explorer is a friend to all, be it plants or fish or tiny mole.";
	 * Testing.printlnObject(StringUtil.substringAfterWith(source, " or "));
	 * <b><em>output look like：</em></b>
	 * <output> or fish or tiny mole.</output>
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.StringUtil#substringAfter(String, String)
	 * @see fan.core.util.StringUtil#substringAfterLast(String, String)
	 * @see fan.core.util.StringUtil#substringAfterLastWith(String, String)
	 */
	public static String substringAfterWith(String source, String substring) {
		int index = source.indexOf(substring);
		return index == INDEX_NOT_FOUND ? null : source.substring(index);
	}
	
	/**
	 * <p><des> 切割源串, 索引从源串中参数串最后一次出现的位置开始到源串最后结束, 不包括参数串。
	 *          如果源串中不含有参数子串, 则返回null </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String source = "An explorer is a friend to all, be it plants or fish or tiny mole.";
	 * Testing.printlnObject(StringUtil.substringAfterLast(source, " or "));
	 * <b><em>output look like：</em></b>
	 * <output>tiny mole.</output>
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.StringUtil#substringAfter(String, String)
	 * @see fan.core.util.StringUtil#substringAfterWith(String, String)
	 * @see fan.core.util.StringUtil#substringAfterLastWith(String, String)
	 */
	public static String substringAfterLast(String source, String substring) {
		int index = source.lastIndexOf(substring);
		return index == INDEX_NOT_FOUND ? null : source.substring(index + substring.length());
	}
	
	/**
	 * <p><des> 切割源串, 索引从源串中参数串最后一次出现的位置开始到源串最后结束, 包括参数串。
	 *          如果源串中不含有参数子串, 则返回null </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String source = "An explorer is a friend to all, be it plants or fish or tiny mole.";
	 * Testing.printlnObject(StringUtil.substringAfterLastWith(source, " or "));
	 * <b><em>output look like：</em></b>
	 * <output> or tiny mole.</output>
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.StringUtil#substringAfter(String, String)
	 * @see fan.core.util.StringUtil#substringAfterWith(String, String)
	 * @see fan.core.util.StringUtil#substringAfterLast(String, String)
	 */
	public static String substringAfterLastWith(String source, String substring) {
		int index = source.lastIndexOf(substring);
		return index == INDEX_NOT_FOUND ? null : source.substring(index);
	}
	
	/**
	 * <p><des> 将源串编码为一个字节序列 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String source = "An explorer is a friend to all, be it plants or fish or tiny mole.";
	 * Testing.printlnObject(StringUtil.getBytes(source, Charset.forName("UTF-8")));
	 * </pre></p>
	 * @since 0.1.0
	 * @see java.nio.charset.Charset
	 * @see fan.core.util.StringUtil#getBytes(String, String)
	 */
	public static byte[] getBytes(String source, Charset charset){
		return source == null ? null : source.getBytes(charset);
	}
	
	/**
	 * <p><des> 将源串编码为一个字节序列 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String source = "An explorer is a friend to all, be it plants or fish or tiny mole.";
	 * Testing.printlnObject(StringUtil.getBytes(source, EncodingCode.UTF_8.toCode()));
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.code.EncodingCode
	 * @see fan.core.util.StringUtil#getBytes(String, Charset)
	 */
	public static byte[] getBytes(String source, String charset){
        return getBytes(source, Charset.forName(charset));
	}
	
	/**
	 * <p><des> 将源串编码为一个ISO-8859-1字节序列 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String source = "An explorer is a friend to all, be it plants or fish or tiny mole.";
	 * Testing.printlnObject(StringUtil.getBytesISO1(source));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static byte[] getBytesISO1(String source){
		return getBytes(source, Charset.forName(EncodingCode.ISO_8859_1.toCode()));
	}
	
	/**
	 * <p><des> 将源串编码为一个UTF-8字节序列 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String source = "An explorer is a friend to all, be it plants or fish or tiny mole.";
	 * Testing.printlnObject(StringUtil.getBytesUTF8(source));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static byte[] getBytesUTF8(String source){
		return getBytes(source, Charset.forName(EncodingCode.UTF_8.toCode()));
	}
	
	/**
	 * <p><des> 将源串编码为一个GBK字节序列 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String source = "An explorer is a friend to all, be it plants or fish or tiny mole.";
	 * Testing.printlnObject(StringUtil.getBytesGBK(source));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static byte[] getBytesGBK(String source){
		return getBytes(source, Charset.forName(EncodingCode.GBK.toCode()));
	}
	
	/**
	 * <p><des> 将源串编码为一个GB2312字节序列 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String source = "An explorer is a friend to all, be it plants or fish or tiny mole.";
	 * Testing.printlnObject(StringUtil.getBytesGB2312(source));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static byte[] getBytesGB2312(String source){
		return getBytes(source, Charset.forName(EncodingCode.GB2312.toCode()));
	}
	
	/**
	 * <p><des> 将源串编码为一个ASCII字节序列 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String source = "An explorer is a friend to all, be it plants or fish or tiny mole.";
	 * Testing.printlnObject(StringUtil.getBytesASCII(source));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static byte[] getBytesASCII(String source){
		return getBytes(source, Charset.forName(EncodingCode.ASCII.toCode()));
	}
	
	/**
	 * <p><des> 使用参数指定的编码解码字节序列 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * byte[] bytes = {-28, -69, -118, -27, -92, -87, -28, -67, -96, -27, -66, -82, -25, -84, -111, -28, -70, -122, -27, -112, -105, -17, -68, -97};
	 * Testing.printlnObject(StringUtil.getString(bytes, Charset.forName(EncodingCode.UTF_8.toCode())));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String getString(byte[] bytes, Charset charset) {
        return bytes == null ? null : new String(bytes, charset);
    }
	
	/**
	 * <p><des> 使用参数指定的编码解码字节序列 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * byte[] bytes = {-28, -69, -118, -27, -92, -87, -28, -67, -96, -27, -66, -82, -25, -84, -111, -28, -70, -122, -27, -112, -105, -17, -68, -97};
	 * Testing.printlnObject(StringUtil.getString(bytes, EncodingCode.UTF_8.toCode()));
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.code.EncodingCode
	 */
	public static String getString(byte[] bytes, String charset) {
        return getString(bytes, Charset.forName(charset));
    }
	
	/**
	 * <p><des> 使用ISO-8859-1解码字节序列 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * byte[] bytes = {-28, -69, -118, -27, -92, -87, -28, -67, -96, -27, -66, -82, -25, -84, -111, -28, -70, -122, -27, -112, -105, -17, -68, -97};
	 * Testing.printlnObject(StringUtil.getStringISO1(bytes));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String getStringISO1(byte[] bytes) {
        return getString(bytes, Charset.forName(EncodingCode.ISO_8859_1.toCode()));
    }
	
	/**
	 * <p><des> 使用UTF-8解码字节序列 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * byte[] bytes = {-28, -69, -118, -27, -92, -87, -28, -67, -96, -27, -66, -82, -25, -84, -111, -28, -70, -122, -27, -112, -105, -17, -68, -97};
	 * Testing.printlnObject(StringUtil.getStringUTF8(bytes));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String getStringUTF8(byte[] bytes) {
        return getString(bytes, Charset.forName(EncodingCode.UTF_8.toCode()));
    }
	
	/**
	 * <p><des> 使用GBK解码字节序列 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * byte[] bytes = {-28, -69, -118, -27, -92, -87, -28, -67, -96, -27, -66, -82, -25, -84, -111, -28, -70, -122, -27, -112, -105, -17, -68, -97};
	 * Testing.printlnObject(StringUtil.getStringGBK(bytes));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String getStringGBK(byte[] bytes) {
        return getString(bytes, Charset.forName(EncodingCode.GBK.toCode()));
    }
	
	/**
	 * <p><des> 使用GB2312解码字节序列 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * byte[] bytes = {-28, -69, -118, -27, -92, -87, -28, -67, -96, -27, -66, -82, -25, -84, -111, -28, -70, -122, -27, -112, -105, -17, -68, -97};
	 * Testing.printlnObject(StringUtil.getStringGB2312(bytes));
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String getStringGB2312(byte[] bytes) {
        return getString(bytes, Charset.forName(EncodingCode.GB2312.toCode()));
    }
	
	/**
	 * <p><des> 返还一个由参数顺序替换源串占位符(?)的字符串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String result = StringUtil.parsePlaceholder("email: ?", "fancore@126.com");
	 * Testing.printlnObject(result);
	 * <b><em>output look like：</em></b>
	 * <output>email: fancore@126.com</output>
	 * <b><em>e.g.</em></b>
	 * String result = StringUtil.parsePlaceholder("id(?, ?, ?, ?)", "fancy", "fancydeepin", "fancore", "fan");
	 * Testing.printlnObject(result);
	 * <b><em>output look like：</em></b>
	 * <output>id(fancy, fancydeepin, fancore, fan)</output>
	 * <b><em>e.g.</em></b>
	 * String result = StringUtil.parsePlaceholder("id(?, ?, ?)", "fancy", "fancydeepin", "fancore", "fan");
	 * Testing.printlnObject(result);
	 * <b><em>output look like：</em></b>
	 * <output>id(fancy, fancydeepin, fancore)</output>
	 * <b><em>e.g.</em></b>
	 * String result = StringUtil.parsePlaceholder("id(?, ?, ?, ?)", "fancy", "fancydeepin", "fancore");
	 * Testing.printlnObject(result);
	 * <b><em>output look like：</em></b>
	 * <output>id(fancy, fancydeepin, fancore, ?)</output>
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String parsePlaceholder(String source, Object... values){
		if(source == null || values == null){
			return source;
		}
		int length = values == null ? 0 : values.length;
		for(int i = 0; i < length; i++){
			source = source.replaceFirst(PLACEHOLDER, values[i].toString());
		}
		return source;
	}
	
	/**
	 * <p><des> 首字母大写 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String source = "when the sun shines we shine together";
	 * String result = StringUtil.toFirstLetterUpperCase(source);
	 * Testing.printlnObject(result);
	 * <b><em>output look like：</em></b>
	 * <output>When the sun shines we shine together</output>
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String toFirstLetterUpperCase(String source){
		return source.substring(0, 1).toUpperCase() + source.substring(1);
	}
	
	/**
	 * <p><des> 使用参数分隔符将源串中大写字母变换成小写并保留该字符 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String result = StringUtil.convertUpperCaseLetter("personInfo", "-");
	 * Testing.printlnObject(result);
	 * <b><em>output look like：</em></b>
	 * <output>person-info</output>
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String convertUpperCaseLetter(String source, String separator) {
		if(source.equals(source.toLowerCase()) || separator == null){
			return source;
		}
		char[] chars = source.toCharArray();
		StringBuilder builder = new StringBuilder();
		for(char ch : chars){
			if(ch >= 'A' && ch <= 'Z'){
				builder.append(separator).append(Character.toLowerCase(ch));
			}else{
				builder.append(ch);
			}
		}
		return builder.toString();
	}
}