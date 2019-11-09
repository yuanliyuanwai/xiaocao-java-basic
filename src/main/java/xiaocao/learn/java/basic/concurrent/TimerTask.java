package xiaocao.learn.java.basic.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class TravelQuote {
	
}

class TravelCompany {
	TravelQuote solicitQuote(TravelInfo travelInfo) {
		return null;
	}
}

class TravelInfo {
	
}

/**
 * 多个任务在指定的时间内返回
 * @author Administrator
 *
 */
public class TimerTask {
	
	private final ExecutorService exec = Executors.newCachedThreadPool();
	
	private class QuoteTask implements Callable<TravelQuote>{
		
		private final TravelCompany company;
		
		private final TravelInfo travelInfo;
		
		public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
			this.company = company;
			this.travelInfo = travelInfo;
		}

		@Override
		public TravelQuote call() throws Exception {
			return company.solicitQuote(travelInfo);
		}
		
		public TravelQuote getFailureQuote(Throwable e) {
			return null;
		}
		
		public TravelQuote getTimeoutQuote(Throwable e) {
			return null;
		}

	}
	
	public List<TravelQuote> getRankedTravelQuotes(TravelInfo travelInfo, Set<TravelCompany> companies, 
			Comparator<TravelQuote> ranking, long time, TimeUnit unit) throws InterruptedException{
		List<QuoteTask> tasks = new ArrayList<QuoteTask>();
		for(TravelCompany company : companies) {
			tasks.add(new QuoteTask(company, travelInfo));
		}
		List<Future<TravelQuote>> futures = exec.invokeAll(tasks, time, unit);
		List<TravelQuote> quotes = new ArrayList<TravelQuote>(tasks.size());
		Iterator<QuoteTask> taskIter = tasks.iterator();
		for(Future<TravelQuote> f : futures) {
			QuoteTask task = taskIter.next();
			try{
				quotes.add(f.get());
			} catch (ExecutionException e) {
				quotes.add(task.getFailureQuote(e.getCause()));
			} catch (CancellationException e) {
				quotes.add(task.getTimeoutQuote(e.getCause()));
			}
		}
		Collections.sort(quotes, ranking);
		return quotes;
	}

	
}

