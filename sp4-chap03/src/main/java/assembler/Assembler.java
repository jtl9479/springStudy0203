package assembler;

import spring.CachedMemberDao;
import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

/*
 *  객체 생성에 사용할 클래스를 변경하기 위해
 *  그 객체를 사용하는 코드를 변경하지 않고 
 *  객체를 주입해주는 코드 한 곳만 변경하는 것을 DI라고 한다.
 *  그렇다면 실제 객체를 생성하는 코드는 어디에 있을까?
 *  쉽게 생각하면  main()메소드에서 객체를 생성한다고 떠올릴 수 있다.
 *  main()메소드에서 의존 대상 객체를 생성하고 주입하는 방법이 나쁘진 않지만
 *  객체를 생성하고 의존 객체를 주입해주는 클래스를 따로 작성하는 것이 더 좋은 방법이다.
 *  의존 객체를 주입한다는 것은 서로 다른 두 객체를 조립한다는 의미 임으로
 *  해당 클래스를 조립기(Assembler)라고도 표현한다
 */
public class Assembler {

	private MemberDao memberDao;
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;
	
	public Assembler() {
		//의존 객체를 변경하려면 조립기의 코드만 수정하면 됨
		this.memberDao = new CachedMemberDao();
		
		/* 객체 생성시 생성자를 통한 객체 주입
		 * Assembler Class는 객체를 생성하고, 
		 * 각 객체가 의존하고있는 객체를 주입한다.  
		 * SPRING ID 역활
		 */
		this.regSvc = new MemberRegisterService(memberDao);
		this.pwdSvc = new ChangePasswordService(memberDao);
	}
	
	public MemberDao getMemberDao() {
		return memberDao;
	}
	
	//객체 호춣
	public MemberRegisterService getMemberRegisterService() {
		return regSvc;
	}
	
	//객체 호춣	
	public ChangePasswordService getChangePasswordService() {
		return pwdSvc;
	}
}
