package by.kazlova.command;

public enum LocaleType {
RU("message_ru"),
EN("message_en"),
BY("message_by");
	
	private String property;
	
	 LocaleType(String property) { //modif??s
		this.property = property;
	}
	 
	 public String getProperty() {
		 return property;
	 }
}
