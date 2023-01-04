package app.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.concurrent.ThreadFactory;

public class TopicThreadFactory implements ThreadFactory {

    private static final Logger LOGGER = LogManager.getLogger(TopicThreadFactory.class);

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        LOGGER.info("Created thread {}_{} at: {}", thread.getId(), thread.getName(), new Date());
        return thread;
    }
}
