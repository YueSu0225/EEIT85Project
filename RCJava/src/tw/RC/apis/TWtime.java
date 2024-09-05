package tw.RC.apis;

public class TWtime {
//		private String time;
//		public TWtime() {
//
//		}
		
		
		
		
		
//		public static boolean isRight(String time) {
//			boolean isRight = false;
//			if (time.matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$")) {
//				return true;
//			}
//			return isRight;
//		}
		public static boolean isRight(String time) {
			boolean isRight = false;
			if (time.matches("^([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]")) {
				return true;
			}
			return isRight;
		}
		
		
		
		
		
}
