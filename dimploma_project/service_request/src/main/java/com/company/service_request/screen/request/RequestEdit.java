package com.company.service_request.screen.request;

import io.jmix.ui.screen.*;
import com.company.service_request.entity.Request;

@UiController("Request.edit")
@UiDescriptor("request-edit.xml")
@EditedEntityContainer("requestDc")
public class RequestEdit extends StandardEditor<Request> {
}