package com.example.demo;

import com.example.demo.yes.very.deep.pack.DeepComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

// http://localhost:8080/home -> 주소를 주소창에 입력했을 때
//우리가 만든 html이 보이게 하기!

//@SpringBootApplication
//@SpringBootAlpplication's coomponent
@EnableAutoConfiguration
//@ComponentScan
//@ComponentScan(basePackages = "com.example.demo" ) //기본이 되는 패키지의 이름을 넣어주면 됨
//@ComponentScan(basePackages = "com.example.demo.yes")//yes만 사용하기!!!!
//@ComponentScan(basePackages = {
//		"com.example.demo.yes",
//		"com.example.demo.temp",
//}) //various packages
@ComponentScan(basePackageClasses = DeepComponent.class) //package including the class
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
