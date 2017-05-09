package jp.catalyna.producer;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

/**
 * An actor producer that lets Spring create the Actor instances.
 */
public class SpringActorProducer implements IndirectActorProducer {
    private static final Logger LOGGER = LogManager.getLogger(SpringActorProducer.class);
    final ApplicationContext applicationContext;
    final String actorBeanName;

    public SpringActorProducer(ApplicationContext applicationContext,
                               String actorBeanName) {
        LOGGER.debug("bean = " + actorBeanName);
        this.applicationContext = applicationContext;
        this.actorBeanName = actorBeanName;
    }

    @Override
    public Actor produce() {
        LOGGER.debug("produce: " + actorBeanName);
        return (Actor) applicationContext.getBean(actorBeanName);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        LOGGER.debug("actorClass: " + actorBeanName);
        return (Class<? extends Actor>) applicationContext.getType(actorBeanName);
    }
}
