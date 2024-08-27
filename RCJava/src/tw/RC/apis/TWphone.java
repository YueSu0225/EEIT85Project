package tw.RC.apis;

import javax.lang.model.type.NullType;

public class TWphone {
		private String phone;
		
		
		public TWphone() {
			
		}
		
		private TWphone(String phone) {
			this.phone = phone;
			}
		
		public static TWphone newTWphone(String phone) {
			

			if (isRight(phone)) {
				return new TWphone(phone);
			}else {
				
				return null;
			}
		}
		public String getphone() {
			return phone;
		}

		
		
		public static boolean isRight(String phone) {
			boolean isRight = false;

			if (phone.matches("[0][9]-[0-9]{8}")) {
				return true;
			}
			
			return isRight;
		}
}
