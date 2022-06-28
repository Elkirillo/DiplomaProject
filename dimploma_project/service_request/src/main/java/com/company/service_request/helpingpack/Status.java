package com.company.service_request.helpingpack;

import io.jmix.core.metamodel.datatype.impl.EnumClass;
import io.jmix.ui.meta.StudioProperty;

import javax.annotation.Nullable;
import java.util.Objects;

public enum Status implements EnumClass<String> {
    Not_Resolved("Not Resolved"),
    In_progress("In progress"),
    Resolved("Resolved");

    private String id;

    Status(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Status fromId(String id) {
        for (Status at : Status.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
