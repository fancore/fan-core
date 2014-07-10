package fan.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import fan.core.exception.ExecutetimeException;
import fan.core.util.code.DateFormatCode;
/**
 * <p> <b> @描述：</b> 日期常用操作工具类
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-06-20
 * <p> <b> @since 0.1.0 </b>
 */
public final class DateUtil {

	private DateUtil(){}
	
	/** <p><des> 空间换时间, 确保 SimpleDateFormat 多线程安全 </des></p> */
	private static final ThreadLocal<SimpleDateFormat> THREADLOCAL = new ThreadLocal<SimpleDateFormat>(){
		
		@Override
		protected SimpleDateFormat initialValue() {
			// create a new SimpleDateFormat object for each thread
			return new SimpleDateFormat();
		}
		
	};
	
	/** <p><des> 获取 SimpleDateFormat 实例, 线程安全 </des></p> */
	public static SimpleDateFormat getSimpleDateFormat(String pattern){
		SimpleDateFormat dateFormat = THREADLOCAL.get();
		dateFormat.applyPattern(pattern);
		return dateFormat;
	}
	
	/**
	 * <p><des> 获取当前日期 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String date = DateUtil.getCurrentDate();
	 * Testing.printlnObject(date);
	 * <b><em>output look like：</em></b>
	 * <output>2014-05-20</output>
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String getCurrentDate() {
		return getCurrentDateString(DateFormatCode.SHORT_HYPHEN.toCode());
	}
	
	/**
	 * <p><des> 获取当前日期时间 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String date = DateUtil.getCurrentDateTime();
	 * Testing.printlnObject(date);
	 * <b><em>output look like：</em></b>
	 * <output>2014-05-20 11:02:24</output>
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String getCurrentDateTime() {
		return getCurrentDateString(DateFormatCode.LONG_HYPHEN.toCode());
	}
	
	/**
	 * <p><des> 获取日期串 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String date = DateUtil.getCurrentDateString(DateFormatCode.LONG_HYPHEN.toCode());
	 * Testing.printlnObject(date);
	 * <b><em>output look like：</em></b>
	 * <output>2014-05-20 11:02:24:827</output>
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.code.DateFormatCode
	 * @see fan.core.util.code.DateFormatCode#toCode()
	 */
	public static String getCurrentDateString(String pattern) {
		return getSimpleDateFormat(pattern).format(new Date());
	}
	
	/**
	 * <p><des> 格式化日期 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String date = DateUtil.formatDate(new Date(), DateFormatCode.SHORT_HYPHEN.toCode());
	 * Testing.printlnObject(date);
	 * <b><em>output look like：</em></b>
	 * <output>2014-05-20</output>
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.code.DateFormatCode
	 * @see fan.core.util.code.DateFormatCode#toCode()
	 */
	public static String formatDate(Date date, String pattern){
		return getSimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * <p><des> 解析字符串为日期类型 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String dateString = "2014-05-20";
	 * Date date = DateUtil.parseDate(dateString, DateFormatCode.SHORT_HYPHEN.toCode());
	 * Testing.printlnObject(date);
	 * <b><em>output look like：</em></b>
	 * <output>Tue May 20 00:00:00 CST 2014</output>
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.code.DateFormatCode
	 * @see fan.core.util.code.DateFormatCode#toCode()
	 */
	public static Date parseDate(String date, String pattern){
		try {
			return getSimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			throw new ExecutetimeException(e);
		}
	}
	
	/**
	 * <p><des> 获取与指定日期对象相隔参数arg个年份的日期对象 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Date date1 = new Date();
	 * Date date2 = DateUtil.getDateModifyYear(date1, 1);
	 * Date date3 = DateUtil.getDateModifyYear(date1, -1);
	 * Testing.printlnObject(DateUtil.formatDate(date1, DateFormatCode.SHORT_HYPHEN.toCode()));
	 * Testing.printlnObject(DateUtil.formatDate(date2, DateFormatCode.SHORT_HYPHEN.toCode()));
	 * Testing.printlnObject(DateUtil.formatDate(date3, DateFormatCode.SHORT_HYPHEN.toCode()));
	 * <b><em>output look like：</em></b>
	 * <output>2014-07-07</output>
	 * <output>2015-07-07</output>
	 * <output>2013-07-07</output>
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static Date getDateModifyYear(Date date, int arg){
		return readjustDate(date, Calendar.YEAR, arg);
	}
	
	/**
	 * <p><des> 获取与指定日期对象相隔参数arg个月份的日期对象 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Date date1 = new Date();
	 * Date date2 = DateUtil.getDateModifyMonth(date1, 1);
	 * Date date3 = DateUtil.getDateModifyMonth(date1, -1);
	 * Testing.printlnObject(DateUtil.formatDate(date1, DateFormatCode.SHORT_HYPHEN.toCode()));
	 * Testing.printlnObject(DateUtil.formatDate(date2, DateFormatCode.SHORT_HYPHEN.toCode()));
	 * Testing.printlnObject(DateUtil.formatDate(date3, DateFormatCode.SHORT_HYPHEN.toCode()));
	 * <b><em>output look like：</em></b>
	 * <output>2014-07-07</output>
	 * <output>2014-08-07</output>
	 * <output>2014-06-07</output>
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static Date getDateModifyMonth(Date date, int arg){
		return readjustDate(date, Calendar.MONTH, arg);
	}
	
	/**
	 * <p><des> 获取与指定日期对象相隔参数arg天的日期对象 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Date date1 = new Date();
	 * Date date2 = DateUtil.getDateModifyDays(date1, 1);
	 * Date date3 = DateUtil.getDateModifyDays(date1, -1);
	 * Testing.printlnObject(DateUtil.formatDate(date1, DateFormatCode.SHORT_HYPHEN.toCode()));
	 * Testing.printlnObject(DateUtil.formatDate(date2, DateFormatCode.SHORT_HYPHEN.toCode()));
	 * Testing.printlnObject(DateUtil.formatDate(date3, DateFormatCode.SHORT_HYPHEN.toCode()));
	 * <b><em>output look like：</em></b>
	 * <output>2014-07-07</output>
	 * <output>2014-07-08</output>
	 * <output>2014-07-06</output>
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static Date getDateModifyDays(Date date, int arg){
		return readjustDate(date, Calendar.DAY_OF_MONTH, arg);
	}
	
	/**
	 * <p><des> 获取与指定日期对象相隔参数arg小时的日期对象 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Date date1 = new Date();
	 * Date date2 = DateUtil.getDateModifyHour(date1, 1);
	 * Date date3 = DateUtil.getDateModifyHour(date1, -1);
	 * Testing.printlnObject(DateUtil.formatDate(date1, DateFormatCode.LONG_HYPHEN.toCode()));
	 * Testing.printlnObject(DateUtil.formatDate(date2, DateFormatCode.LONG_HYPHEN.toCode()));
	 * Testing.printlnObject(DateUtil.formatDate(date3, DateFormatCode.LONG_HYPHEN.toCode()));
	 * <b><em>output look like：</em></b>
	 * <output>2014-07-07 21:01:44</output>
	 * <output>2014-07-07 22:01:44</output>
	 * <output>2014-07-07 20:01:44</output>
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static Date getDateModifyHour(Date date, int arg){
		return readjustDate(date, Calendar.HOUR_OF_DAY, arg);
	}
	
	/**
	 * <p><des> 获取与指定日期对象相隔参数arg分钟的日期对象 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Date date1 = new Date();
	 * Date date2 = DateUtil.getDateModifyMinute(date1, 1);
	 * Date date3 = DateUtil.getDateModifyMinute(date1, -1);
	 * Testing.printlnObject(DateUtil.formatDate(date1, DateFormatCode.LONG_HYPHEN.toCode()));
	 * Testing.printlnObject(DateUtil.formatDate(date2, DateFormatCode.LONG_HYPHEN.toCode()));
	 * Testing.printlnObject(DateUtil.formatDate(date3, DateFormatCode.LONG_HYPHEN.toCode()));
	 * <b><em>output look like：</em></b>
	 * <output>2014-07-07 21:03:20</output>
	 * <output>2014-07-07 21:04:20</output>
	 * <output>2014-07-07 21:02:20</output>
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static Date getDateModifyMinute(Date date, int arg){
		return readjustDate(date, Calendar.MINUTE, arg);
	}
	
	/**
	 * <p><des> 获取与指定日期对象相隔参数arg秒的日期对象 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Date date1 = new Date();
	 * Date date2 = DateUtil.getDateModifySecond(date1, 1);
	 * Date date3 = DateUtil.getDateModifySecond(date1, -1);
	 * Testing.printlnObject(DateUtil.formatDate(date1, DateFormatCode.LONG_HYPHEN.toCode()));
	 * Testing.printlnObject(DateUtil.formatDate(date2, DateFormatCode.LONG_HYPHEN.toCode()));
	 * Testing.printlnObject(DateUtil.formatDate(date3, DateFormatCode.LONG_HYPHEN.toCode()));
	 * <b><em>output look like：</em></b>
	 * <output>2014-07-07 21:04:31</output>
	 * <output>2014-07-07 21:04:32</output>
	 * <output>2014-07-07 21:04:30</output>
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static Date getDateModifySecond(Date date, int arg){
		return readjustDate(date, Calendar.SECOND, arg);
	}

	
	/**
	 * <p><des> 获取与指定日期对象相隔参数arg毫秒的日期对象 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Date date1 = new Date();
	 * Date date2 = DateUtil.getDateModifyMillisecond(date1, 1);
	 * Date date3 = DateUtil.getDateModifyMillisecond(date1, -1);
	 * Testing.printlnObject(DateUtil.formatDate(date1, DateFormatCode.FULL_HYPHEN.toCode()));
	 * Testing.printlnObject(DateUtil.formatDate(date2, DateFormatCode.FULL_HYPHEN.toCode()));
	 * Testing.printlnObject(DateUtil.formatDate(date3, DateFormatCode.FULL_HYPHEN.toCode()));
	 * <b><em>output look like：</em></b>
	 * <output>2014-07-07 21:05:33:900</output>
	 * <output>2014-07-07 21:05:33:901</output>
	 * <output>2014-07-07 21:05:33:899</output>
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static Date getDateModifyMillisecond(Date date, int arg){
		return readjustDate(date, Calendar.MILLISECOND, arg);
	}
	
	/**
	 * <p><des> 获取参数指定日期对象表示的一个日历对象 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * Calendar calendar = DateUtil.getCalendar(new Date());
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static Calendar getCalendar(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	/**
	 * <p><des> 获取日期的格式(简单的年月日时分秒毫秒顺序) </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * String dateString1 = "2014-07-07";
	 * String dateString2 = "2014-07-07 21:12:34";
	 * String dateString3 = "2014年07月07日 21时12分34秒";
	 * Date date = DateUtil.parseDate(dateString3, DateUtil.getPattern(dateString3));
	 * Testing.printlnObject(DateUtil.getPattern(dateString1));
	 * Testing.printlnObject(DateUtil.getPattern(dateString2));
	 * Testing.printlnObject(DateUtil.getPattern(dateString3));
	 * Testing.printlnObject(date);
	 * <b><em>output look like：</em></b>
	 * <output>yyyy-MM-dd
	 * yyyy-MM-dd HH:mm:ss
	 * yyyy年MM月dd日 mm时ss分SS秒
	 * Mon Jul 07 00:21:12 CST 2014</output>
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static String getPattern(String date){
		char[] chars = date.toCharArray();
		char[][] pattern = {
			{'y', 'y', 'y', 'y'}, 
			{'M', 'M'}, 
			{'d', 'd'}, 
			{'H', 'H'}, 
			{'m', 'm'},
			{'s', 's'}, 
			{'S', 'S', 'S'}
		};
		int i = 0, j = 0, monthIndex = 1, hourIndex = 3, fullLength = 2;
		StringBuilder builder = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		for(char ch : chars){
			if(ch >= '0' && ch <= '9'){
				temp.append(pattern[i][j++]);
			}else{
				if((i == monthIndex || i == hourIndex) && temp.length() != fullLength){
					String lowerCase = temp.toString().toLowerCase();
					builder.append(lowerCase).append(lowerCase);
				}else{
					builder.append(temp.toString());
				}
				builder.append(ch);
				i++;
				j = 0;
				temp = new StringBuilder();
			}
		}
		builder.append(temp.toString());
		return builder.toString();
	}
	
	/** <p><des> 重新调整日期 </pre></p> */
	private static Date readjustDate(Date date, int field, int arg){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, arg);
		return calendar.getTime();
	}
}