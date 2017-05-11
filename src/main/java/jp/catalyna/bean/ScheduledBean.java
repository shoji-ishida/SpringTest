package jp.catalyna.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;


/**
 * Created by ishida on 2017/05/10.
 */
public class ScheduledBean {
    private static final Logger LOGGER = LogManager.getLogger(ScheduledBean.class);

    @Scheduled(cron="*/5 * * * * MON-FRI")
    public void timeout() {
        LOGGER.info("Scheduled ScheduledBean");
    }
}
