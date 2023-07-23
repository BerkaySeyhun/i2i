package i2i;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ActorTest {

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("MySystem");

		int port1 = 8080;
		int port2 = 8081;

		ActorRef actor1 = system.actorOf(Props.create(Actor1.class, port1), "Actor1");
		ActorRef actor2 = system.actorOf(Props.create(Actor2.class, port2), "Actor2");

		actor1.tell("Hello i2i, greetings from Actor1...", actor2);
	}

	static class Actor1 extends AbstractActor {

		private int port1 = 8080;

		public Actor1(int port) {
			this.port1 = port;
		}

		@Override
		public Receive createReceive() {
			return receiveBuilder().match(String.class, message -> {
				System.out.println(message);
				getSender().tell("Hello i2i, greetings from Actor2...", self());
			}).build();
		}
	}

	static class Actor2 extends AbstractActor {

		private int port2 = 8081;

		public Actor2(int port) {
			this.port2 = port;
		}

		@Override
		public Receive createReceive() {
			return receiveBuilder().match(String.class, message -> {
				System.out.println(message);
			}).build();
		}
	}
}
