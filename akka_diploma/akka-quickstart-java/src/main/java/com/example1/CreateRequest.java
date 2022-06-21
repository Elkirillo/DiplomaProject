package com.example1;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class CreateRequest extends AbstractBehavior<CreateRequest.Requests> {


    public static class Requests {
        public Requests(){}
    }
    private CreateRequest(ActorContext<Requests> context) {
        super(context);
    }

    //фабричный метод создания актора CreateRequest
    public static Behavior<Requests> create() { return Behaviors.setup(CreateRequest::new); }

    @Override
    public Receive<Requests> createReceive() {
        return  newReceiveBuilder()
                .onMessage(Requests.class, this::gettingRequest)
                .build();
    }

    //Метод отправляет gettingrequest актору заявку
    public Behavior<Requests> gettingRequest(Requests command) {
        getContext().getLog().info("Заявка создана на сервисе");
        //#create-actors
        ActorRef<GettingRequest.Requests> currentRequest = getContext().spawn(GettingRequest.create(), "MyRequest");
        currentRequest.tell(new GettingRequest.Requests());
        return this;
    }


}
