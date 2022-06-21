package com.company.service_request.screen.request;

import io.jmix.ui.screen.*;
import com.company.service_request.entity.Request;

@UiController("Request.browse")
@UiDescriptor("request-browse.xml")
@LookupComponent("requestsTable")
public class RequestBrowse extends StandardLookup<Request> {
}