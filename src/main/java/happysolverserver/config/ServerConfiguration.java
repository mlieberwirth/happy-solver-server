package happysolverserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Configuration("server")
public class ServerConfiguration {

	@Value("${server.number-of-threads}")
	private Integer numberOfThreads;

	@Bean(name = "rest-thread-pool")
	public ThreadPoolTaskScheduler createRESTThreadPool() {
		return createThreadPool("REST-Pool");
	}

	private ThreadPoolTaskScheduler createThreadPool(String name) {
		ThreadPoolTaskScheduler threadPool = new ThreadPoolTaskScheduler();
		threadPool.setThreadNamePrefix(name);
		threadPool.setErrorHandler(exception -> log.error("Exception in thread.", exception));
		threadPool.setPoolSize(numberOfThreads);
		threadPool.setWaitForTasksToCompleteOnShutdown(true);
		threadPool.setAwaitTerminationSeconds(30);
		return threadPool;
	}
}
