package org.smida.smidaApplication.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Report {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID companyId;
    private Timestamp reportDate;
    private BigDecimal totalRevenue;
    private BigDecimal netProfit;
}
