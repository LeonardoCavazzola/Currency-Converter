package com.converter.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    private String originCur;
    private BigDecimal originValue;
    private String destinyCur;
    private BigDecimal conversionRate;
    private LocalDateTime transactionTime;

    public Transaction(User user, String originCur, BigDecimal originValue, String destinyCur, BigDecimal conversionRate) {
        this.user = user;
        this.originCur = originCur;
        this.originValue = originValue;
        this.destinyCur = destinyCur;
        this.conversionRate = conversionRate;
        this.transactionTime = LocalDateTime.now();
    }

    public BigDecimal getdestinyValue(){
        return originValue.multiply(conversionRate);
    }
}
