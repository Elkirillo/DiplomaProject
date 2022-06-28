package com.company.service_request.screen.request;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import com.company.service_request.helpingpack.app.TaskService;
import com.company.service_request.helpingpack.app.akka.CompleteRequest;
import com.company.service_request.helpingpack.app.akka.CreateRequest;
import com.company.service_request.helpingpack.app.akka.DecisionRequest;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.service_request.entity.Request;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Request.edit")
@UiDescriptor("request-edit.xml")
@EditedEntityContainer("requestDc")
public class RequestEdit extends StandardEditor<Request> {
    @Autowired
    private TaskService taskService;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Request> event) {

        if (event.getEntity().getDepartament() == null) {
            final ActorSystem<CreateRequest.Requests> status = ActorSystem.create(CreateRequest.create(), "Create_request");
            status.tell(new CreateRequest.Requests());
        }

        event.getEntity().setAssignee(taskService.findLestBusyWorker());

        if (event.getEntity().getAssignee() != null) {
            final ActorSystem<DecisionRequest.Requests> status = ActorSystem.create(DecisionRequest.create(), "myReq2");
            status.tell(new DecisionRequest.Requests());
        }

        if (event.getEntity().getDescription() != null) {
            final ActorSystem<CompleteRequest.Requests> behaviorActor = ActorSystem.create(CompleteRequest.create(), "myReq3");
            behaviorActor.tell(new CompleteRequest.Requests());
        }
    }
}