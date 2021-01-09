package core;

public class CoreFields
{
	private String userName = "root";
	private String passWord = "";
	private String url = "jdbc:mysql://localhost:3306/stok?useUnicode=true&characterEncoding=UTF-8";
	
	public String getUserName() {
		return userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public String getUrl() {
		return url;
	}
}