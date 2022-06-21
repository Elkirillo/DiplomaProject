package com.example1;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class GettingRequest extends AbstractBehavior<GettingRequest.Requests> {

    public static class Requests {
        public Requests(){}
    }

    public static Behavior<Requests> create() {
        return Behaviors.setup(GettingRequest::new);
    }

    private GettingRequest(ActorContext<Requests> context) {
        super(context);
    }

    @Override
    public Receive<Requests> createReceive() {
        return newReceiveBuilder().onMessage(Requests.class, this::setNotResolved).build();
    }

    private Behavior<Requests> setNotResolved(Requests comand) {
        getContext().getLog().info("Заявку принял персонал на решение");
        ActorRef<BehaviorForRequest.Status> behaviorActor = getContext().spawn(BehaviorForRequest.create(), "myReq2");
        behaviorActor.tell(BehaviorForRequest.Status.NOT_RESOLVED);
        return this;
    }



}
