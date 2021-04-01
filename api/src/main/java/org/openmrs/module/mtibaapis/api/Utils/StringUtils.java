package org.openmrs.module.mtibaapis.api.Utils;

public class StringUtils {
	
	/**
	 * @param string String to be checked for not null, not empty, and not white space only.
	 * @return {@code true} if provided String is not null, is not empty, and has at least one
	 *         character that is not considered white space.
	 */
	public static boolean isNullOrEmptyOrWhiteSpace(final String string) {
		return string == null || string.isEmpty() || string.trim().isEmpty();
	}
}
