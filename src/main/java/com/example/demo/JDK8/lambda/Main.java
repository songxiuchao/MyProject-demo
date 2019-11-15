package com.example.demo.JDK8.lambda;

public class Main {

	public static void main(String[] args) {

		Main tester = new Main();

		// 类型声明
		MathOperation addition = (int a, int b) -> a + b;

		// 不用类型声明
		MathOperation subtraction = (a, b) -> a - b;

		// 大括号中的返回语句
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};

		// 没有大括号及返回语句
		MathOperation division = (int a, int b) -> a / b;

		System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
		System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
		System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
		System.out.println("10 / 5 = " + tester.operate(10, 5, division));

		// 不用括号
		GreetingService greetService1 = message -> System.out.println("Hello " + message);

		// 用括号
		GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

		greetService1.sayMessage("Runoob");
		greetService2.sayMessage("Google");
	}
	interface MathOperation {
		int operation(int a, int b);
	}

	/**
	 * 接口有且仅有一个抽象方法
	 * 允许定义静态方法
	 * 允许定义默认方法
	 * 允许java.lang.Object中的public方法
	 * 该注解不是必须的，如果一个接口符合"函数式接口"定义，那么加不加该注解都没有影响。
	 * 加上该注解能够更好地让编译器进行检查。如果编写的不是函数式接口，
	 * 但是加上了@FunctionInterface，那么编译器会报错
	 */
	@FunctionalInterface
	interface GreetingService {
		void sayMessage(String message);
	}

	private int operate(int a, int b, MathOperation mathOperation) {
		return mathOperation.operation(a, b);
	}
}
