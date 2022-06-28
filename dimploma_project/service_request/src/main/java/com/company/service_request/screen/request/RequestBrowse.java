package com.company.service_request.screen.request;

import io.jmix.ui.screen.*;
import com.company.service_request.entity.Request;
import liquibase.pro.packaged.id;
import org.atmosphere.config.service.Post;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@UiController("Request.browse")
@RestController
@RequestMapping("/defenition/Zayavka")
@UiDescriptor("request-browse.xml")
@LookupComponent("requestsTable")
public class RequestBrowse extends StandardLookup<Request> {
    public List<Map<String,String>> request = new ArrayList<Map<String, String>>(){};
    public List<Map<String,String>> status = new ArrayList<Map<String, String>>(){};

    public static Integer counter;
    @GetMapping("{getByID}")
    public Map<String, String> getByID(@PathVariable String id) throws ChangeSetPersister.NotFoundException {
        return request.stream()
                .filter(request -> request.get("id").equals(id))
                .findFirst()
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @GetMapping("{getByStatus}")
    public Map<String, String> getByStatus(@PathVariable Enum id) throws ChangeSetPersister.NotFoundException {
        return status.stream()
                .filter(request -> request.get("status").equals(id))
                .findFirst()
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @PostMapping("{_store}")
    public Map<String, String> create(@PathVariable Map<String, String> request) throws ChangeSetPersister.NotFoundException {
        request.put("id", String.valueOf(counter++));
        this.request.add(request);
        return request;
    }
}