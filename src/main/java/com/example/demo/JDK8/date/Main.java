package com.example.demo.JDK8.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {
	public static void main(String args[]){
		Main java8tester = new Main();
	      java8tester.testLocalDateTime();
	   }
	    
	   public void testLocalDateTime(){
	    
	      // 获取当前的日期时间
	      LocalDateTime currentTime = LocalDateTime.now();
	      System.out.println("当前时间: " + currentTime);
	        
	      LocalDate date1 = currentTime.toLocalDate();
	      System.out.println("date1: " + date1);
	        
	      Month month = currentTime.getMonth();
	      int day = currentTime.getDayOfMonth();
	      int seconds = currentTime.getSecond();
	        
	      System.out.println("月: " + month +", 日: " + day +", 秒: " + seconds);
	        
	      LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
	      System.out.println("date2: " + date2);
	        
	      // 12 december 2014
	      LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
	      System.out.println("date3: " + date3);
	        
	      // 22 小时 15 分钟
	      LocalTime date4 = LocalTime.of(22, 15);
	      System.out.println("date4: " + date4);
	        
	      // 解析字符串
	      LocalTime date5 = LocalTime.parse("20:15:30");
	      System.out.println("date5: " + date5);
	      
	      // 获取当前时间日期
	      ZonedDateTime date11 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
	      System.out.println("date1: " + date11);
	        
	      ZoneId id = ZoneId.of("Europe/Paris");
	      System.out.println("ZoneId: " + id);
	        
	      ZoneId currentZone = ZoneId.systemDefault();
	      System.out.println("当期时区: " + currentZone);
	   }
}
