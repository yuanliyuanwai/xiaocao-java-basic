package xiaocao.java.basic.jdk8.feature;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * jdk8提供了并行数组最重要的方法是并行排序可以显著的提高多核处理器的数组排序性能
 * 
 * @author zhengchong.wan
 *
 */
public class ParallelArray {

	public static void main(String[] args) {
		long[] arrayOfLong = new long[20000];
		Arrays.parallelSetAll(arrayOfLong, index -> ThreadLocalRandom.current().nextInt(1000000));
		Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));
		System.out.println();
		Arrays.parallelSort(arrayOfLong);

		Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));
		System.out.println();
	}

}
