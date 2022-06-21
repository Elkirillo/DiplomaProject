package com.example1;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;


import java.io.IOException;

public class AkkaMain {
    public static void main (String [] args) {
        //#actor-system
        final ActorSystem<CreateRequest.Requests> status = ActorSystem.create(CreateRequest.create(), "Create_request");
        //#actor-system

        //#main-send-messages
        status.tell(new CreateRequest.Requests());

        //#main-send-messages

        try {
            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (IOException ignored) {
        } finally {
            status.terminate();
        }
    }
}
