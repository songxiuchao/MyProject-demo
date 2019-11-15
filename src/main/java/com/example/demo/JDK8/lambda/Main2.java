package com.example.demo.JDK8.lambda;

public class Main2 {
	   
   public static void main(String args[]){
	   
	   String salutation = "Hello! ";
//	   salutation = "good night! ";
	   
      GreetingService greetService1 = message -> 
      System.out.println(salutation + message);
      greetService1.sayMessage("Runoob");
      
   }
    
   interface GreetingService {
      void sayMessage(String message);
   }
}
