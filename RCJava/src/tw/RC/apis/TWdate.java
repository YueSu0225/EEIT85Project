package tw.RC.apis;



public class TWdate {
	private String date;
	public TWdate() {

	}
	
	
	
	public static boolean isRight(String date) {
		boolean isRight = false;

		if (date.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")) {
			return true;
		}
		
		return isRight;
	}
	
}
