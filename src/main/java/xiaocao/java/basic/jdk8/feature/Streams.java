package xiaocao.java.basic.jdk8.feature;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @ClassName: Streams 
 * @Description: 流式api
 *     java流将函数式编程引入到java库中开发人员能够写出更加紧凑和简洁的代码 Stream API能够极大的简化集合操作
 * @author zhengchong.wan
 * @date 2019年1月11日 下午7:20:37
 *
 */
public class Streams {

	private enum Status {
		OPEN, CLOSED;
	}

	private static final class Task {
		private final Status status;
		private final Integer points;

		Task(final Status status, final Integer points) {
			this.status = status;
			this.points = points;
		}

		public Integer getPoints() {
			return points;
		}

		public Status getStatus() {
			return status;
		}

		@Override
		public String toString() {
			return String.format("[%s, %d]", status, points);
		}
	}
	
	public static void main(String[] args) throws IOException {
		final Collection<Task> tasks = Arrays.asList(
			    new Task( Status.OPEN, 5 ),
			    new Task( Status.OPEN, 13 ),
			    new Task( Status.CLOSED, 8 ) 
			);
		
		final long totalPointsOfOpenTasks = tasks
			    .stream()
			    .filter( task -> task.getStatus() == Status.OPEN )
			    .mapToInt( Task::getPoints )
			    .sum();
		
		System.out.println( "Total points: " + totalPointsOfOpenTasks);
		
		// Calculate total points of all tasks
		final double totalPoints = tasks
		   .stream()
		   .parallel()
		   .map( task -> task.getPoints() ) // or map( Task::getPoints ) 
		   .reduce(0, Integer::sum );
		System.out.println( "Total points (all tasks): " + totalPoints);
		
		// 分组
		final Map<Status, List<Task>> map = tasks.stream().collect(Collectors.groupingBy(Task::getStatus));
		System.out.println(map);
		
		// 计算比重
		final Collection<String> result = tasks
		    .stream()                                        // Stream< String >
		    .mapToInt(Task::getPoints )                     // IntStream
		    .asLongStream()                                  // LongStream
		    .mapToDouble(points -> points / totalPoints)   // DoubleStream
		    .boxed()                                         // Stream< Double >
		    .mapToLong(weigth -> (long )( weigth * 100)) // LongStream
		    .mapToObj(percentage -> percentage + "%")      // Stream< String> 
		    .collect(Collectors.toList() );                 // List< String > 
		System.out.println(result);
		
		// IO操作
		final Path path = new File("D:\\test\\test.txt").toPath();
		try(Stream< String > lines = Files.lines(path, StandardCharsets.UTF_8 ) ) {
		    lines.onClose(() -> System.out.println("Done!") ) .forEach( System.out::println );
		}
	}
	
	

}
