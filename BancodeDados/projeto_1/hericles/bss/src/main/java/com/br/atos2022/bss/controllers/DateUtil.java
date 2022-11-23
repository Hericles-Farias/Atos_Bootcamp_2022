package com.br.atos2022.bss.controllers;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public interface DateUtil {
    String ISO_DATE_FORMAT_ZERO_OFFSET = "dd/MM/yyyy HH:mm:ss";
    String UTC_TIMEZONE_NAME = "UTC";
  
    static SimpleDateFormat provideDateFormat() {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ISO_DATE_FORMAT_ZERO_OFFSET);
      simpleDateFormat.setTimeZone(TimeZone.getTimeZone(UTC_TIMEZONE_NAME));
      return simpleDateFormat;
    }
}
