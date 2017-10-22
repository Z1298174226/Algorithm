//import akka.actor.*;
//import akka.dispatch.Mailbox;

/**
 * Created by qtfs on 2017/9/25.
 */
public class AKKADemo {

////    public static void main(String[] args) {
////        ActorSystem system = ActorSystem.create("actor-demo-java");
////        ActorRef hello = system.actorOf(Props.create(Hello.class));
////        hello.tell("Bob", ActorRef.noSender());
////        try {
////            Thread.sleep(1000);
////        } catch (InterruptedException e) { /* ignore */ }
////        system.shutdown();
////    }
////
////    private static class Hello extends UntypedActor {
////
////        public void onReceive(Object message) throws Exception {
////            if (message instanceof String) {
////                System.out.println("Hello " + message);
////            }
////        }
////    }
//public static void main(String[] args) {
//    ActorSystem system = ActorSystem.create("actor-demo-java");
//    ActorRef bob = system.actorOf(Greeter.props("Bob", "Howya doing"));
//    ActorRef alice = system.actorOf(Greeter.props("Alice", "Happy to meet you"));
//    ActorRef mike = system.actorOf(Greeter.props("Mike","Are you Ready"));
//    mike.tell(new Greet(mike), ActorRef.noSender());
//    bob.tell(new Greet(alice), ActorRef.noSender());
//    alice.tell(new Greet(bob), ActorRef.noSender());
//   // alice.tell(new Greet(alice), ActorRef.noSender());
//    alice.tell(new Greet(mike), ActorRef.noSender());
//    try {
//        Thread.sleep(1000);
//    } catch (InterruptedException e) { /* ignore */ }
//    system.shutdown();
//}
//
//    // messages
//    private static class Greet {
//
//
//        public final ActorRef target;
//
//        public Greet(ActorRef actor) {
//            target = actor;
//        }
//    }
//
//    private static Object AskName = new Object();
//
//    private static class TellName {
//        public final String name;
//
//        public TellName(String name) {
//            this.name = name;
//        }
//    }
//
//    // actor implementation
//    private static class Greeter extends UntypedActor {
//        private final String myName;
//        private final String greeting;
//
//        Greeter(String name, String greeting) {
//            myName = name;
//            this.greeting = greeting;
//        }
//
//        public static Props props(String name, String greeting) {
//            return Props.create(Greeter.class, name, greeting);
//        }
//
//        public void onReceive(Object message) throws Exception {
//            if (message instanceof Greet) {
//                ((Greet)message).target.tell(AskName, self());
//            } else if (message == AskName) {
//                sender().tell(new TellName(myName), self());
//            } else if (message instanceof TellName) {
//                System.out.println(greeting + ", " + ((TellName)message).name);
//            }
//        }
//
//    }
}