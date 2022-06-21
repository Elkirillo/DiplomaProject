package com.company.service_request.helpingpack;

import io.jmix.core.metamodel.datatype.impl.EnumClass;
import io.jmix.ui.meta.StudioProperty;

import javax.annotation.Nullable;
import java.util.Objects;

public interface Status {

    CaptionPosition getCaptionPosition();

    @StudioProperty(name = "captionPosition", defaultValue = "Not viewed", options = {"Not viewed",
            "Under consideration",
            "Submitted for decision",
            "Resolved",
            "Not Resolved"})
    void setCaptionPosition(CaptionPosition position);

    enum CaptionPosition implements EnumClass<String> {
        Not_viewed,
        Under_consideration,
        Submitted_for_decision,
        Resolved,
        Not_Resolved;

        @Override
        public String getId() {
            return name();
        }

        @Nullable
        public static CaptionPosition fromId(String id) {
            for (CaptionPosition captionPosition : CaptionPosition.values()) {
                if (Objects.equals(id, captionPosition.getId())) {
                    return captionPosition;
                }
            }
            return null;
        }
    }

}
