package com.example.bankapp.date;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DateRange {

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateFrom;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateTo;

}
