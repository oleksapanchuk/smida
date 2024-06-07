package org.smida.smidaApplication.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import lombok.extern.slf4j.Slf4j;
import org.smida.smidaApplication.entity.FinancialData;
import org.smida.smidaApplication.entity.ReportDetails;
import org.smida.smidaApplication.repository.ReportDetailsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@ChangeLog
public class DatabaseChangeLog {

    @ChangeSet(order = "001", id = "seedDatabase", author = "Oleksandr Panchuk")
    public void seedDatabase(ReportDetailsRepository reportDetailsRepository) {
        List<ReportDetails> expenseList = new ArrayList<>();

        expenseList.add(createReportDetails(
                "5d8e4adf-359a-4a3f-b0c5-e4704554f320",
                FinancialData.builder()
                        .costOfGoodsSold(124578.32)
                        .operatingExpenses(23456.78)
                        .taxesPaid(3456.78)
                        .build(),
                "Q3 report showing steady growth."
        ));
        expenseList.add(createReportDetails(
                "909143ac-7b44-430e-b810-f1b33afe9598",
                FinancialData.builder()
                        .costOfGoodsSold(278456.92)
                        .operatingExpenses(29385.44)
                        .taxesPaid(4290.22)
                        .build(),
                "End of year report with excellent results."
        ));
        expenseList.add(createReportDetails(
                "cac8a2fc-7923-4775-8711-e9b71b755d06",
                FinancialData.builder()
                        .costOfGoodsSold(302145.68)
                        .operatingExpenses(37429.51)
                        .taxesPaid(5287.94)
                        .build(),
                "Excellent quarterly performance."
        ));
        expenseList.add(createReportDetails(
                "2d4b43c1-7984-40fa-b2cf-458765d4b476",
                FinancialData.builder()
                        .costOfGoodsSold(109034.12)
                        .operatingExpenses(20894.22)
                        .taxesPaid(3697.28)
                        .build(),
                "Strong profit margins this quarter."
        ));
        expenseList.add(createReportDetails(
                "b9e9ffc8-dbdd-417a-b178-e8d4fd5ca5c1",
                FinancialData.builder()
                        .costOfGoodsSold(30123.45)
                        .operatingExpenses(15009.78)
                        .taxesPaid(2097.12)
                        .build(),
                "Q4 performance overview."
        ));
        expenseList.add(createReportDetails(
                "b053acce-adfe-472a-9bec-9739fa26edc0",
                FinancialData.builder()
                        .costOfGoodsSold(189234.56)
                        .operatingExpenses(28756.44)
                        .taxesPaid(4123.78)
                        .build(),
                "Year-end summary with solid growth."
        ));

        expenseList.add(createReportDetails(
                "a8ed3d20-3fbf-478f-890f-32f531c460bc",
                FinancialData.builder()
                        .costOfGoodsSold(100432.89)
                        .operatingExpenses(20134.22)
                        .taxesPaid(3894.56)
                        .build(),
                "Positive financial results."
        ));
        expenseList.add(createReportDetails(
                "b9817915-9ce3-48d1-a3a5-2c8761f149c1",
                FinancialData.builder()
                        .costOfGoodsSold(78943.21)
                        .operatingExpenses(15345.78)
                        .taxesPaid(2847.32)
                        .build(),
                "Moderate growth with controlled expenses."
        ));
        expenseList.add(createReportDetails(
                "6a188f87-3c37-4532-9a6c-4d4112c0867d",
                FinancialData.builder()
                        .costOfGoodsSold(129456.78)
                        .operatingExpenses(23456.78)
                        .taxesPaid(4567.89)
                        .build(),
                "Solid revenue with potential for expansion."
        ));
        expenseList.add(createReportDetails(
                "ae99f358-1080-4adf-ad54-2ac6fa000e85",
                FinancialData.builder()
                        .costOfGoodsSold(65432.10)
                        .operatingExpenses(9876.54)
                        .taxesPaid(2345.67)
                        .build(),
                "Stable performance with steady profit margins."
        ));
        expenseList.add(createReportDetails(
                "ce11c792-3fd9-493d-93af-c48aac56a5cb",
                FinancialData.builder()
                        .costOfGoodsSold(193456.78)
                        .operatingExpenses(45678.90)
                        .taxesPaid(6789.01)
                        .build(),
                "Excellent performance with high net profit."
        ));
        expenseList.add(createReportDetails(
                "056bf1ca-d88f-4d0e-b0a6-79b37dbb1655",
                FinancialData.builder()
                        .costOfGoodsSold(245678.90)
                        .operatingExpenses(34567.89)
                        .taxesPaid(5678.12)
                        .build(),
                "Outstanding revenue and profit for the period."
        ));
        expenseList.add(createReportDetails(
                "dc9ed97b-2cd0-43e3-a8d1-a5eb06523606",
                FinancialData.builder()
                        .costOfGoodsSold(56789.23)
                        .operatingExpenses(12345.67)
                        .taxesPaid(1789.34)
                        .build(),
                ""
        ));
        expenseList.add(createReportDetails(
                "f0b52bf4-4082-41e6-b544-97c46cc96500",
                FinancialData.builder()
                        .costOfGoodsSold(113456.78)
                        .operatingExpenses(22345.67)
                        .taxesPaid(3345.78)
                        .build(),
                "Strong financial performance with good margins."
        ));
        expenseList.add(createReportDetails(
                "05502d2b-bf33-4f98-a252-56abcb4fa44b",
                FinancialData.builder()
                        .costOfGoodsSold(154321.09)
                        .operatingExpenses(26789.45)
                        .taxesPaid(4789.12)
                        .build(),
                ""
        ));
        expenseList.add(createReportDetails(
                "9c3d13d3-8c11-4773-b73b-aaaa7ad0e656",
                FinancialData.builder()
                        .costOfGoodsSold(213456.89)
                        .operatingExpenses(213456.89)
                        .taxesPaid(5678.34)
                        .build(),
                ""
        ));

        reportDetailsRepository.insert(expenseList);
    }

    private ReportDetails createReportDetails(String reportId, FinancialData financialData, String comments) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String financialDataJson = objectMapper.writeValueAsString(financialData);
            return new ReportDetails(UUID.fromString(reportId), financialDataJson, comments);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // log error
        }

        return null;
    }
}
