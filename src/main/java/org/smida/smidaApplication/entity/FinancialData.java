package org.smida.smidaApplication.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FinancialData {
    private double costOfGoodsSold;
    private double operatingExpenses;
    private double taxesPaid;
}
