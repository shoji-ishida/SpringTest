import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import jp.catalyna.actor.CountingActor;

/**
 * Created by ishida on 2017/05/09.
 */
public class AkkaRemote {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("RemoteCounter",
                ConfigFactory.load("akka_remote.conf"));
        system.actorOf(Props.create(CountingActor.class), "counter");
    }
}
