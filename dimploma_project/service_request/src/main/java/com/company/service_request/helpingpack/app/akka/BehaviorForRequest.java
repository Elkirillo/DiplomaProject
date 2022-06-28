package com.company.service_request.helpingpack.app.akka;

import akka.actor.typed.ActorRef;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import akka.actor.typed.Behavior;
import com.company.service_request.entity.Request;
import com.company.service_request.helpingpack.Status;

import static com.company.service_request.helpingpack.app.akka.BehaviorForRequest.Status.*;

public class BehaviorForRequest  extends AbstractBehavior<BehaviorForRequest.Status> {

    public enum Status{
        NOT_RESOLVED, IN_PROCESS, RESOLVED
    }
    public static Request request = new Request();

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
        request.setStatus(com.company.service_request.helpingpack.Status.Not_Resolved);
        return this;
    }

    public Behavior<Status> changeStatusToProccess(){
        getContext().getLog().info("Заявка решается");
        request.setStatus(com.company.service_request.helpingpack.Status.In_progress);
        return this;
    }

    public Behavior<Status> changeStatusToResolved(){
        getContext().getLog().info("Заявка решена");
        request.setStatus(com.company.service_request.helpingpack.Status.Resolved);
        return Behaviors.stopped();
    }



}
