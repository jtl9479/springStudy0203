package chap02;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
//GenericXmlApplicationContext  xml파일로부터 설정 정보를 읽어 빈 객체를 생성하고 관리하는 기능을 제공한다.
import org.springframework.context.support.GenericXmlApplicationContext;

/*
 *  beanFactory -> 객체 생성과 검색에 대한 기능을 가진다.
 *  ApplicationContext -> 메세지, 이벤트, 프로필/환경 변수 등을 처리할 수 있는 기능을 정의
 *  	GenericXmlApplicationContext -> xml로부터 객체 설정 정보를 가져온다.
 *  	AnnotationConfigApplicationContext -> 자바 어노테이션을 이용한 클래스로 부터 객체 설정 정보를 가져온다.
 *  	GenericGroovyApplicationContext -> 그루비 코드를 이용해 설정 정보를 가져온다.\
 *  -> 빈 객체의 생성, 초기화, 보관, 제거 등을 관리한다.(스프링 컨테이너)
 *  스프링 컨테이너는 기본적으로 빈을 싱글톤으로 관리해준다. 따라서 싱글톤 컨테이너라고 불리기도 한다.
 */


public class Main2 {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		Greeter g1 = ctx.getBean("greeter", Greeter.class);
		Greeter g2 = ctx.getBean("greeter1"
				+ "지", Greeter.class);
		//같은 객체를 리턴한다.
		//스프링은 별도 설정을 하지 않을 경우 한 개의 빈 객체만을 생성하며,
		//이들 빈 객체들이 싱글톤 범위를 갖는다.
		//스프링은 기본적으로 한 개의 <bean> 태그에 대한 한 개의 빈 객체를 생성한다.
		
		System.out.println((g1 == g2));
		
		ctx.close();
	}
	
}
