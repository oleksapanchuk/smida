package org.smida.smidaApplication.configuration;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import org.smida.smidaApplication.entity.ReportDetails;
import org.smida.smidaApplication.repository.ReportDetailsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ChangeLog
public class DatabaseChangeLog {

    @ChangeSet(order = "001", id = "seedDatabase", author = "Oleksandr Panchuk")
    public void seedDatabase(ReportDetailsRepository reportDetailsRepository) {
        List<ReportDetails> expenseList = new ArrayList<>();
        expenseList.add(new ReportDetails(
                UUID.fromString("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb"),
                "Financial Data 1",
                "Comments 1"
        ));
        expenseList.add(new ReportDetails(
                UUID.fromString("cccccccc-cccc-cccc-cccc-cccccccccccc"),
                "Financial Data 2",
                "Comments 2"
        ));
        expenseList.add(new ReportDetails(
                UUID.fromString("dddddddd-dddd-dddd-dddd-dddddddddddd"),
                "Financial Data 3",
                "Comments 3"
        ));
        expenseList.add(new ReportDetails(
                UUID.fromString("eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee"),
                "Financial Data 4",
                "Comments 4"
        ));
        expenseList.add(new ReportDetails(
                UUID.fromString("ffffffff-ffff-ffff-ffff-ffffffffffff"),
                "Financial Data 5",
                "Comments 5"
        ));

        reportDetailsRepository.insert(expenseList);
    }
}
