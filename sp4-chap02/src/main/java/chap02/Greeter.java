package chap02;

public class Greeter {

	private String format;

	/*	getter setter
	 *  getter -> 데이터 꺼내기
	 *  setter -> 데이터 넣기
	 */
	
	public String greet(String guest) {
		return String.format(format, guest);
	}

	public void setFormat(String format) {
		this.format = format;
	}
		
}
