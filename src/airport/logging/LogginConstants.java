package airport.logging;

/**
 * @author Lokesh Gupta
 *
 */
public class LogginConstants {

	private static boolean STD_OUT_Enable = false;

	/**
	 * @return the is_STD_OUT_Enable
	 */
	public static boolean isSTD_OUT_Enable() {
		return STD_OUT_Enable;
	}

	/**
	 * @param is_STD_OUT_Enable the is_STD_OUT_Enable to set
	 */
	public static void setSTD_OUT_Enable(boolean STD_OUT_Enable) {
		LogginConstants.STD_OUT_Enable = STD_OUT_Enable;
	}
	
}
