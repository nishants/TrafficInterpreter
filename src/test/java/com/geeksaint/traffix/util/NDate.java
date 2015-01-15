package com.geeksaint.traffix.util;

import lombok.AllArgsConstructor;

import static com.geeksaint.traffix.util.DateSupport.addDaysTo;
import static java.lang.Long.parseLong;

@AllArgsConstructor
public class NDate extends java.util.Date{
    private java.util.Date date;
}
