package util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils
{

	/**
	 * Converts a UTC timestamp to a specific timezone and formats it as DD-MM-YYYY
	 * HH:MM AM/PM
	 *
	 * @param utcTimestamp The timestamp in UTC from the database (e.g., "2024-12-17
	 *                     12:49:20")
	 * @param userTimeZone The timezone of the user (e.g., "Asia/Kolkata",
	 *                     "America/New_York")
	 * @return Formatted date string in DD-MM-YYYY HH:MM AM/PM format
	 */
	public static String convertToUserTimeZone(String utcTimestamp, String userTimeZone)
	{
		// Parse the UTC timestamp into LocalDateTime
		LocalDateTime localDateTime = LocalDateTime.parse(utcTimestamp,
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		// Attach UTC timezone to the LocalDateTime
		ZonedDateTime utcZonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));

		// Convert to user's timezone
		ZonedDateTime userZonedDateTime = utcZonedDateTime.withZoneSameInstant(ZoneId.of(userTimeZone));

		// Define the required output format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

		// Format and return the date
//		return userZonedDateTime.format(formatter);

		return localDateTime.format(formatter);
	}

	public static void main(String[] args)
	{
		// Example usage
		String utcTimestamp = "2024-12-17 12:49:20";
		String userTimeZone = "Asia/Kolkata"; // Replace with dynamic timezone

		String formattedDate = convertToUserTimeZone(utcTimestamp, userTimeZone);
		System.out.println("Formatted Date: " + formattedDate);
	}
}