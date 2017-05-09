package jp.catalyna.actor;

import akka.actor.AbstractActor;
import jp.catalyna.service.CountingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * An actor that can count using an injected CountingService.
 *
 * @note The scope here is prototype since we want to create a new actor
 * instance for use of this bean.
 */
@Named("CountingActor")
@Scope("prototype")
public class CountingActor extends AbstractActor {
  private static final Logger LOGGER = LogManager.getLogger(CountingActor.class);

  public static class Count implements Serializable {}
  public static class Get implements  Serializable {}

  // the service that will be automatically injected
  final CountingService countingService;

  public CountingActor() {
    countingService = null;
  }
  @Inject
  public CountingActor(@Named("CountingService") CountingService countingService) {
    LOGGER.debug("creating CountingActor");
    this.countingService = countingService;
  }

  private int count = 0;

  @Override
  public Receive createReceive() {
    return receiveBuilder()
            .match(Count.class, s -> {
              LOGGER.info("Received Count message");
            })
            .match(Get.class, s -> {
              LOGGER.info("Received Get message");
            })
            .matchAny(o -> LOGGER.info("received unknown message"))
            .build();
  }
}
