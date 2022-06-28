package com.company.service_request.screen.user;

import com.company.service_request.entity.User;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@UiController("User.browse")
@RestController
@RequestMapping("/v1/users")
@UiDescriptor("user-browse.xml")
@LookupComponent("usersTable")
@Route("users")
public class UserBrowse extends StandardLookup<User> {
}