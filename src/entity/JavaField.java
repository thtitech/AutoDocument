package entity;

public class JavaField {

	public String name;
	public String type;
	public String visible;
	public boolean isStatic;
	public String detail;
	
	public JavaField(String name, String type, String visible, boolean isStatic, String detail) {
		this.name = name;
		this.type = type;
		this.visible = visible;
		this.isStatic = isStatic;
		this.detail = detail;
	}

}
