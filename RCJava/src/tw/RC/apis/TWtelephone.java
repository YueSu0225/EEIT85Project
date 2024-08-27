package tw.RC.apis;

public class TWtelephone {
		private String telephone;
		
		
		
		
		
		public static boolean isRight (String telephone) {
			boolean isRight = false;

			if (telephone.matches("[0][1-9]-[0-9]{7}")) {
				return true;
			}
			
			return isRight;
		}
		
		
}
