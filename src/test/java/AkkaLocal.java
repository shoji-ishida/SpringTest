import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.google.protobuf.Empty;
import com.typesafe.config.ConfigFactory;
import jp.catalyna.actor.CountingActor;

/**
 * Created by ishida on 2017/05/09.
 */
public class AkkaLocal {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("LocalCounter",
                ConfigFactory.load("akka_local.conf"));
        final ActorRef actorRef = system.actorOf(Props.create(CountingActor.class), "counter");
        actorRef.tell(Empty.getDefaultInstance(), ActorRef.noSender());

        //final ActorSelection selection = system.actorSelection("akka.tcp://RemoteCounter@127.0.0.1:2552/user/counter");
        //selection.tell(new CountingActor.Count(), ActorRef.noSender());

        //system.terminate();
    }
}
