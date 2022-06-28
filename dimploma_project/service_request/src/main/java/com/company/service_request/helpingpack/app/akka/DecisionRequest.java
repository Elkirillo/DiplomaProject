package com.company.service_request.helpingpack.app.akka;
import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class DecisionRequest extends AbstractBehavior<DecisionRequest.Requests> {

    public static class Requests {
        public Requests(){}
    }

    private DecisionRequest(ActorContext<Requests> context) {
        super(context);
    }

    public static Behavior<Requests> create() { return Behaviors.setup(DecisionRequest::new); }


    @Override
    public Receive<Requests> createReceive() {
        return  newReceiveBuilder()
                .onMessage(Requests.class, this::gettingAssignee)
                .build();
    }

    private Behavior<Requests> gettingAssignee(Requests command) {
        getContext().getLog().info("Заявку решает персонал");
        ActorRef<BehaviorForRequest.Status> behaviorActor = getContext().spawn(BehaviorForRequest.create(), "myReq2");
        behaviorActor.tell(BehaviorForRequest.Status.IN_PROCESS);


        return this;
    }

}
