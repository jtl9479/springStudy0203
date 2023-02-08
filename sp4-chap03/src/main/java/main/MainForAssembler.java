package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import assembler.Assembler;
import spring.AlreadyExistingMemberException;
import spring.ChangePasswordService;
import spring.IdPasswordNotMatchingException;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

public class MainForAssembler {

	/*
	 * 정적 메소드 내부 인스턴스 객체 생성 불가
	*/
	public static void main(String[] args) throws IOException {
		//console 대신에 system.in을 이용하여 콘솔 입력을 받도록 처리함
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		//true일때까지 계속 loof
		while(true) {
			System.out.println("명령어를 입력하세요");
			//BufferedReader의 readLine()메소드를 이용하여 콘솔에서 한 줄을 입력받는다.
			String command = reader.readLine();
			
			//equalsIgnoreCase 대소문자구분 안함
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다");
				break;
			}

			//이 문자열 인스턴스의 시작 부분과 지정한 문자열이 일치하는지 확인
			if(command.startsWith("new")) {
				processNewCommand(command.split(" "));//split 배열로 넘어간다.
				continue;
			} else if (command.startsWith("change")) {
				processChangeCommand(command.split(" "));
				continue;
			}
			printHelp();
		}
	}

	/*	Assembler 객체 생성
	 * 	Assembler 클래스의 생성자에서 필요한 객체를 생성하고 의존을 주입하고 있다.
	 */
	private static Assembler assembler = new Assembler(); 

	/*
	 * 새로운 회원 정보를 생성
	 */
	private static void processNewCommand(String[] split) {
		
		if(split.length != 5) {
			printHelp();
			return;
		}
		/*
		 *  객체 주입을 통해 MemberRegisterService 객체 호출
		 */
		MemberRegisterService registerService = assembler.getMemberRegisterService();
		RegisterRequest registerRequest = new RegisterRequest();
		
		registerRequest.setEmail(split[1]);
		registerRequest.setName(split[2]);
		registerRequest.setPassword(split[3]);
		registerRequest.setConfirmPassword(split[4]);
		
		//true일 경우 패스워드 일치, false일 경우 패스워드 불일치
		if (!registerRequest.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호가 일치하지 않습니다.\n");
			return;
		}
		
		try {
			registerService.regist(registerRequest);
			System.out.println("등록했습니다.\n");
		} catch (AlreadyExistingMemberException e) {
			System.out.println("이미 존재하는 이메일 입니다.\n");
		}
	}
	
	private static void processChangeCommand(String[] split) {
		
		if(split.length != 4) {
			printHelp();
			return;
		}
		
		ChangePasswordService changePasswordService = assembler.getChangePasswordService();
		
		try {
			changePasswordService.changePassword(split[1], split[2], split[3]);
			System.out.println("암호를 변경했습니다.\n");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.\n");
		} catch (IdPasswordNotMatchingException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
			
		}
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법:");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비번 변경비번");
		System.out.println();
	}	
}

