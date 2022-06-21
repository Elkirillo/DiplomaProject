package com.company.service_request.screen.departament;

import io.jmix.ui.screen.*;
import com.company.service_request.entity.Departament;

@UiController("Departament.browse")
@UiDescriptor("departament-browse.xml")
@LookupComponent("departamentsTable")
public class DepartamentBrowse extends StandardLookup<Departament> {
}