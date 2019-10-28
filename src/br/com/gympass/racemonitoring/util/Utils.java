package br.com.gympass.racemonitoring.util;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import br.com.gympass.racemonitoring.bean.Lap;
import br.com.gympass.racemonitoring.bean.Pilot;

public class Utils {
	private static final String NUMBER_PATTERN = "##,###.000";
	private static final String FORMAT_TIME    = "HH:mm:ss.SSS";

	public static LocalTime stringToLocalTimeHours(String localTimeString) {
		return LocalTime.parse(localTimeString, DateTimeFormatter.ofPattern(FORMAT_TIME));
	}

	public static LocalTime stringToLocalTimeMinutes(String localTimeString) {
		localTimeString = String.format("%9s", localTimeString);
		localTimeString = localTimeString.replaceAll(" ", "00:0");
		return Utils.stringToLocalTimeHours(localTimeString);
	}

	public static String localTimeHoursToString(LocalTime localTime) {
		return localTime.format(DateTimeFormatter.ofPattern(FORMAT_TIME)).trim();
	}

	public static LocalTime addLocalTime(LocalTime firsLocalTime, LocalTime secoundLocalTime) {
		LocalTime localTimeResult = firsLocalTime;

		if (firsLocalTime != null) {
			localTimeResult = localTimeResult.plusNanos(secoundLocalTime.getNano());
			localTimeResult = localTimeResult.plusSeconds(secoundLocalTime.getSecond());
			localTimeResult = localTimeResult.plusMinutes(secoundLocalTime.getMinute());
			localTimeResult = localTimeResult.plusHours(secoundLocalTime.getHour());
		} else {
			localTimeResult = secoundLocalTime;
		}

		return Utils.stringToLocalTimeHours(Utils.localTimeHoursToString(localTimeResult));
	}

	public static LocalTime subtractionLocalTime(LocalTime highestLocalTime, LocalTime lowerLocalTime) {
		LocalTime localTimeResult = highestLocalTime;

		if (highestLocalTime != null) {
			localTimeResult = localTimeResult.plusNanos(-lowerLocalTime.getNano());
			localTimeResult = localTimeResult.plusSeconds(-lowerLocalTime.getSecond());
			localTimeResult = localTimeResult.plusMinutes(-lowerLocalTime.getMinute());
			localTimeResult = localTimeResult.plusHours(-lowerLocalTime.getHour());
		} else {
			localTimeResult = lowerLocalTime;
		}

		return localTimeResult;
	}

	public static String formatNumber(Double value) {
		String numberFormated = null;
		DecimalFormat decimalFormat = new DecimalFormat(Utils.NUMBER_PATTERN);

		numberFormated = decimalFormat.format(value);

		return numberFormated;
	}

	public static Lap lineToLap(String line) {
		Lap lap = null;

		if (line != null && !line.equals("")) {
			LocalTime startLap = Utils.stringToLocalTimeHours((line.substring(0, 18).trim()));
			String idPilot = line.substring(19, 22).trim();
			String namePilot = line.substring(24, 67).trim();
			Integer lapNumber = new Integer(line.substring(67, 74).trim());
			LocalTime lapDuration = Utils.stringToLocalTimeMinutes(line.substring(75, 101).trim());
			Double averageSpeedLap = new Double(line.substring(101, line.length()).trim().replace(",", "."));

			Pilot pilot = new Pilot(idPilot, namePilot);
			lap = new Lap(lapNumber, lapDuration, averageSpeedLap, startLap, pilot,
					Utils.addLocalTime(startLap, lapDuration));
		}

		return lap;
	}
}
