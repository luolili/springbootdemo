package com.webflux.waiterdemo.support;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class MoneyWriteConverter implements Converter<Money, Long> {

    @Override
    public Long convert(Money money) {
        return money.getAmountMinorLong();


    }
}
