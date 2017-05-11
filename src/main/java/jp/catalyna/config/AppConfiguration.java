package jp.catalyna.config;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;
import jp.catalyna.bean.ScheduledBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import static jp.catalyna.extension.SpringExtension.SpringExtProvider;

/**
 * The application configuration.
 */
@Configuration
@EnableAsync
@EnableScheduling
class AppConfiguration {
    private static final Logger LOGGER = LogManager.getLogger(AppConfiguration.class);
    // the application context is needed to initialize the Akka Spring Extension
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Actor system singleton for this application.
     */
    @Bean
    public ActorSystem actorSystem() {
        LOGGER.debug("creating ActorSystem bean");
        ActorSystem system = ActorSystem.create("RemoteCounter", ConfigFactory.load("akka_remote.conf"));

        // initialize the application context in the Akka Spring Extension
        SpringExtProvider.get(system).initialize(applicationContext);
        return system;
    }

    @Bean
    public ActorRef actorRef() {
        LOGGER.debug("creating ActorRef bean");
        ActorSystem system = applicationContext.getBean(ActorSystem.class);
        ActorRef actorRef = system.actorOf(SpringExtProvider.get(system).props("CountingActor"), "counter");
        return actorRef;
    }

    @Bean
    public ScheduledBean scheduler() {
        LOGGER.debug("creating ScheduledBean");
        return new ScheduledBean();
    }
}
