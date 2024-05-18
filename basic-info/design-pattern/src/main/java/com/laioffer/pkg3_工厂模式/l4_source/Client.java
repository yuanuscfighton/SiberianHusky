package com.laioffer.pkg3_工厂模式.l4_source;

import java.util.Calendar;

public class Client {
  public static void main(String[] args) {
    Calendar calendar = Calendar.getInstance();
    System.out.println("年: " + calendar.get(Calendar.YEAR));
    System.out.println("月: " + calendar.get(Calendar.MONTH) + 1);
    System.out.println("日: " + calendar.get(Calendar.DAY_OF_MONTH));
    System.out.println("时: " + calendar.get(Calendar.HOUR_OF_DAY));
    System.out.println("分: " + calendar.get(Calendar.MINUTE));
    System.out.println("秒: " + calendar.get(Calendar.SECOND));
  }
}


/*
   Calendar # createCalendar()
       if (aLocale.hasExtensions()) {
            String caltype = aLocale.getUnicodeLocaleType("ca");
            if (caltype != null) {
                cal = switch (caltype) {
                    case "buddhist" -> new BuddhistCalendar(zone, aLocale);
                    case "japanese" -> new JapaneseImperialCalendar(zone, aLocale);
                    case "gregory"  -> new GregorianCalendar(zone, aLocale);
                    default         -> null;
                };
            }
        }
 */
