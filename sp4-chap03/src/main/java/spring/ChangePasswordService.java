package spring;

public class ChangePasswordService {

	private MemberDao memberDao;
	
	public ChangePasswordService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if (member == null) {
			//email에 해당하는 Member가 존재하지 않으면 exception을 발생한다.
			throw new MemberNotFoundException();
		} else {
			//Member가 존재하면 암호 변경
			member.changePassword(oldPwd, newPwd);
		}
		
		memberDao.update(member);
	}
	
}
