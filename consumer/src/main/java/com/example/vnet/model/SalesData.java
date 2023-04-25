package com.example.vnet.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "sales_data")
@Getter
@Setter
@NoArgsConstructor
public class SalesData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product")
    private String productName;

    @Column(name = "store")
    private String storeName;

    @Column(name = "units")
    private Long units;

    @Column(name = "revenue")
    private BigDecimal revenue;

    private LocalDate date;

    public SalesData(String productName, String storeName, Long units, BigDecimal revenue) {
        this.productName = productName;
        this.storeName = storeName;
        this.units = units;
        this.revenue = revenue;
    }

    public SalesData(String productName, Long units, BigDecimal revenue) {
        this.productName = productName;
        this.units = units;
        this.revenue = revenue;
    }
}

