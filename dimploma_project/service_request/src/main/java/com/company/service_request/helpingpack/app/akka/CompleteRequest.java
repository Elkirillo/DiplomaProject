package com.company.service_request.helpingpack.app.akka;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class CompleteRequest extends AbstractBehavior<CompleteRequest.Requests> {

    public static class Requests {
        public Requests(){}
    }
    private CompleteRequest(ActorContext<CompleteRequest.Requests> context) {
        super(context);
    }

    public static Behavior<Requests> create() { return Behaviors.setup(CompleteRequest::new); }

    @Override
    public Receive<Requests> createReceive() {
        return newReceiveBuilder().onMessage(Requests.class, this::setResolved).build();
    }

    private Behavior<Requests> setResolved(Requests command) {
        getContext().getLog().info("Отчет по заявке составлен");
        ActorRef<BehaviorForRequest.Status> behaviorActor = getContext().spawn(BehaviorForRequest.create(), "actor5");
        behaviorActor.tell(BehaviorForRequest.Status.RESOLVED);
        return this;
    }

}
