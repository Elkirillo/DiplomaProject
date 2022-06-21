package com.company.service_request.screen.departament;

import io.jmix.ui.screen.*;
import com.company.service_request.entity.Departament;

@UiController("Departament.edit")
@UiDescriptor("departament-edit.xml")
@EditedEntityContainer("departamentDc")
public class DepartamentEdit extends StandardEditor<Departament> {
}