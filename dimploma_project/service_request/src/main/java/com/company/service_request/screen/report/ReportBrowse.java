package com.company.service_request.screen.report;

import io.jmix.ui.screen.*;
import com.company.service_request.entity.Report;

@UiController("Report.browse")
@UiDescriptor("report-browse.xml")
@LookupComponent("reportsTable")
public class ReportBrowse extends StandardLookup<Report> {
}