package com.converter.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@NoArgsConstructor
@Getter
@Entity
public class Conversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(nullable = false, precision = 6)
    private String originCur;

    @Column(nullable = false, precision = 6)
    private BigDecimal originValue;

    @Column(nullable = false)
    private String destinyCur;

    @Column(nullable = false, precision = 6)
    private BigDecimal conversionRate;

    @Column(nullable = false)
    private LocalDateTime transactionTime;

    public Conversion(User user, String originCur, BigDecimal originValue, String destinyCur, BigDecimal conversionRate) {
        this.user = user;
        this.originCur = originCur;
        this.originValue = originValue;
        this.destinyCur = destinyCur;
        this.conversionRate = conversionRate;
        this.transactionTime = LocalDateTime.now(ZoneId.of("UTC"));
    }

    public BigDecimal getDestinyValue(){
        return originValue.multiply(conversionRate);
    }
}
