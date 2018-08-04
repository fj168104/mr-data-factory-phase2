package com.mr.modules.api.caller;

import com.mr.modules.api.site.SiteTask;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

/**
 * Created by fengj on 18-3-16
 */
@Component
@Log
public class StartVisitor implements SiteVisitor<Future<String>> {

	private SiteThreadPoolExecutor executor;

	@Override
	public Future<String> visit(SiteTask task) {
		return executor.submit(task);
	}

	@PostConstruct
	public void init() {
		executor = new SiteThreadPoolExecutor(10,10);
		log.info("SiteData Process Task start finish");
	}

	/**
	 * The site thread factory
	 */
	static class SiteThreadPoolExecutor extends ThreadPoolExecutor {

		public SiteThreadPoolExecutor(int corePoolSize, int maximumPoolSize) {
			super(corePoolSize, maximumPoolSize, 0,
					TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new SiteThreadFactory());
		}

		@Override
		protected void beforeExecute(Thread t, Runnable r) {
			log.log(Level.INFO, "Thread[{%s}] task start", t.getName());
		}

		@Override
		protected void afterExecute(Runnable r, Throwable t) {
			log.log(Level.INFO, "Thread[{%s}] task finish", "");
		}

		/**
		 * The site thread factory
		 */
		static class SiteThreadFactory implements ThreadFactory {
			private static final AtomicInteger poolNumber = new AtomicInteger(1);
			private final ThreadGroup group;
			private final AtomicInteger threadNumber = new AtomicInteger(1);
			private final String namePrefix;

			SiteThreadFactory() {
				SecurityManager s = System.getSecurityManager();
				group = (s != null) ? s.getThreadGroup() :
						Thread.currentThread().getThreadGroup();
				namePrefix = "pool-" +
						poolNumber.getAndIncrement() +
						"-thread-";
			}

			public Thread newThread(Runnable r) {
				Thread t = new Thread(group, r,
						namePrefix + threadNumber.getAndIncrement(),
						0);
				t.setDaemon(true);
				if (t.getPriority() != Thread.NORM_PRIORITY)
					t.setPriority(Thread.NORM_PRIORITY);
				return t;
			}
		}

	}
}
