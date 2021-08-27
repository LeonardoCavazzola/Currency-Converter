package com.converter.api.dto;

import com.converter.api.model.Conversion;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ConversionView extends RepresentationModel<ConversionView> {

    private final Long id;
    private final Long userId;
    private final String originCurrency;
    private final BigDecimal originValue;
    private final String destinyCurrency;
    private final BigDecimal destinyValue;
    private final BigDecimal conversionRate;
    private final LocalDateTime transactionTime;

    public ConversionView(Conversion conversion) {
        this.id = conversion.getId();
        this.userId = conversion.getUser().getId();
        this.originCurrency = conversion.getOriginCur();
        this.originValue = conversion.getOriginValue();
        this.destinyCurrency = conversion.getDestinyCur();
        this.destinyValue = conversion.getDestinyValue();
        this.conversionRate = conversion.getConversionRate();
        this.transactionTime = conversion.getTransactionTime();
    }
}
