package com.company.service_request.screen.report;

import io.jmix.ui.screen.*;
import com.company.service_request.entity.Report;

@UiController("Report.edit")
@UiDescriptor("report-edit.xml")
@EditedEntityContainer("reportDc")
public class ReportEdit extends StandardEditor<Report> {
}