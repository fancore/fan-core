package fan.core.util;

import java.math.BigDecimal;
import java.util.Random;
import fan.core.exception.ExecutetimeException;
/**
 * <p> <b> @描述：</b> 随机种子常用操作工具类
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-06-28
 * <p> <b> @since 0.1.0 </b>
 */
public class RandomSeedUtil {

	private RandomSeedUtil(){}

	private static final int NAGATIVE = 0;
	private static final int DISPLAYABLE_MIN_INDEX = 0 + 33;
	private static final int DISPLAYABLE_MAX_INDEX = 127 - 1;
	private static final Random random = new Random();
	
	/**
	 * <p><des> 产生 [0, x] 区间随机数, 调该方法要求 x > 0 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * <notes>// the result can be 0, 1, 2, 3, 4, 5</notes>
	 * RandomSeedUtil.intSeed(5);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.RandomSeedUtil#intSeed(int, int)
	 */
	public static int intSeed(int x){
		return intSeed(0, x);
	}
	
	/**
	 * <p><des> 产生 [x, y] 区间随机数, 调该方法要求 y > x > 0 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * <notes>// the result can be 2, 3, 4, 5</notes>
	 * RandomSeedUtil.intSeed(2, 5);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.RandomSeedUtil#intSeed(int)
	 */
	public static int intSeed(int x, int y){
		if(x < NAGATIVE){ // ensure x >= 0
			throw new ExecutetimeException(
				new IllegalArgumentException("x must be greater than or equal to 0")
			);
		}
		if(x >= y){ // ensure y > x, of course y > 0
			throw new ExecutetimeException(
				new IllegalArgumentException("y must be greater than x")
			);
		}
		return x + random.nextInt(y - x + 1);
	}
	
	/**
	 * <p><des> 产生 [0, x] 区间随机数, 调该方法要求 x > 0 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * <notes>// the result can be 0, 1, 2, 3, 4, 5</notes>
	 * RandomSeedUtil.longSeed(5);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.RandomSeedUtil#longSeed(long, long)
	 */
	public static long longSeed(long x){
		return longSeed(0, x);
	}
	
	/**
	 * <p><des> 产生 [x, y] 区间随机数, 调该方法要求 y > x > 0 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * <notes>// the result can be 2, 3, 4, 5</notes>
	 * RandomSeedUtil.longSeed(2, 5);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.RandomSeedUtil#longSeed(long)
	 */
	public static long longSeed(long x, long y){
		if(x < NAGATIVE){ // ensure x >= 0
			throw new ExecutetimeException(
				new IllegalArgumentException("x must be greater than or equal to 0")
			);
		}
		if(x >= y){ // ensure y > x, of course y > 0
			throw new ExecutetimeException(
				new IllegalArgumentException("y must be greater than x")
			);
		}
		return Math.abs(random.nextLong() % (y - x + 1)) + x;
	}
	
	/**
	 * <p><des> 产生 [0, x] 区间随机小数, 调该方法要求 x > 0 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * <notes>// the result can be 0 ~ 0.5 decimal</notes>
	 * RandomSeedUtil.floatSeed(0.5f);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.RandomSeedUtil#floatSeed(float, float)
	 */
	public static float floatSeed(float x){
		return (float) doubleSeed(0., x);
	}
	
	/**
	 * <p><des> 产生 [x, y] 区间随机小数, 调该方法要求 y > x > 0 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * <notes>// the result can be 0.2 ~ 0.5 decimal</notes>
	 * RandomSeedUtil.floatSeed(0.2f, 0.5f);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.RandomSeedUtil#floatSeed(float)
	 */
	public static float floatSeed(float x, float y){
		return (float) doubleSeed(x, y);
	}
	
	/**
	 * <p><des> 产生 [0, x] 区间随机小数, 调该方法要求 x > 0 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * <notes>// the result can be 0 ~ 0.5 decimal</notes>
	 * RandomSeedUtil.doubleSeed(0.5);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.RandomSeedUtil#doubleSeed(double, double)
	 */
	public static double doubleSeed(double x){
		return doubleSeed(0., x);
	}
	
	/**
	 * <p><des> 产生 [x, y] 区间随机小数, 调该方法要求 y > x > 0 </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * <notes>// the result can be 0.2 ~ 0.5 decimal</notes>
	 * RandomSeedUtil.doubleSeed(0.2, 0.5);
	 * </pre></p>
	 * @since 0.1.0
	 * @see fan.core.util.RandomSeedUtil#doubleSeed(double)
	 */
	public static double doubleSeed(double x, double y){
		if(x < NAGATIVE){ // ensure x >= 0
			throw new ExecutetimeException(
				new IllegalArgumentException("x must be greater than or equal to 0")
			);
		}
		if(x >= y){ // ensure y > x, of course y > 0
			throw new ExecutetimeException(
				new IllegalArgumentException("y must be greater than x")
			);
		}
		BigDecimal xbd = new BigDecimal(String.valueOf(x));
		BigDecimal ybd = new BigDecimal(String.valueOf(y));
		double diff = ybd.subtract(xbd).doubleValue();
		xbd = xbd.add(new BigDecimal(String.valueOf(random.nextDouble() * diff)));
		return xbd.doubleValue();
	}
	
	/**
	 * <p><des> 随机产生true或false </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * RandomSeedUtil.boolSeed();
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static boolean boolSeed(){
		return intSeed(0, 1) == NAGATIVE ? false : true;
	}
	
	/**
	 * <p><des> 随机产生有效的可显示的字符 ( 仅支持产生有效的可显示的字符 ) </des></p>
	 * <p><pre>
	 * <b><em>e.g.</em></b>
	 * RandomSeedUtil.charSeed('0', '9');
	 * RandomSeedUtil.charSeed('a', 'z');
	 * RandomSeedUtil.charSeed('A', 'Z');
	 * </pre></p>
	 * @since 0.1.0
	 */
	public static char charSeed(char x, char y){
		if(x < DISPLAYABLE_MIN_INDEX){ // ensure x >= 33('!')
			throw new ExecutetimeException(
				new IllegalArgumentException("x must be greater than or equal to '!'\nrefer to wiki http://zh.wikipedia.org/zh-cn/ASCII")
			);
		}
		if(x >= y){ // ensure y > x, of course y > 33
			throw new ExecutetimeException(
				new IllegalArgumentException("y must be greater than x\nrefer to wiki http://zh.wikipedia.org/zh-cn/ASCII")
			);
		}
		if(y > DISPLAYABLE_MAX_INDEX){ // ensure y <= 126('~'), [0, 127] --> [33, 126] 
			throw new ExecutetimeException(
				new IllegalArgumentException("y must be less than or equal to '~'\nrefer to wiki http://zh.wikipedia.org/zh-cn/ASCII")
			);
		}
		return (char) (random.nextInt(y - x + 1) + x);
	}
	
	/**
	 * <p><des> 获取Random实例 </des></p>
	 * @since 0.1.0
	 */
	public static Random getRandom(String pattern){
		return random;
	}
}