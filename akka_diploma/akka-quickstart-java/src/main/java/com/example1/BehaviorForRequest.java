package com.example1;

import akka.actor.typed.ActorRef;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import akka.actor.typed.Behavior;

import static com.example1.BehaviorForRequest.Status.*;

public class BehaviorForRequest  extends AbstractBehavior<BehaviorForRequest.Status> {

    public enum Status{
        NOT_RESOLVED, IN_PROCESS, RESOLVED
    }

    // static фабричный метод,Behavior.setup возвращает ссылку на контекст актора
    public static Behavior<Status> create(){
        return Behaviors.setup(context -> new BehaviorForRequest(context));
    }
    public BehaviorForRequest(ActorContext<Status> context) {
        super(context);
    }



    @Override
    public Receive<Status> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals(NOT_RESOLVED,this::changeStatusToNotResolve)
                .onMessageEquals(IN_PROCESS,this::changeStatusToProccess)
                .onMessageEquals(RESOLVED,this::changeStatusToResolved)
                .build();
    }

    public Behavior<Status> changeStatusToNotResolve(){
        getContext().getLog().info("Заявка на рассмотрении");
        ActorRef<DecisionRequest.Requests> behaviorActor = getContext().spawn(DecisionRequest.create(), "myReq2");
        behaviorActor.tell(new DecisionRequest.Requests());
        return this;
    }

    public Behavior<Status> changeStatusToProccess(){
        getContext().getLog().info("Заявка решается");
        ActorRef<CompleteRequest.Requests> behaviorActor = getContext().spawn(CompleteRequest.create(), "myReq3");
        behaviorActor.tell(new CompleteRequest.Requests());
        return this;
    }

    public Behavior<Status> changeStatusToResolved(){
        getContext().getLog().info("Заявка решена");
        return Behaviors.stopped();
    }



}
