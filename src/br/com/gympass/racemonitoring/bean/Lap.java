package br.com.gympass.racemonitoring.bean;

import java.time.LocalTime;

import br.com.gympass.racemonitoring.util.Utils;

public class Lap implements Comparable<Lap> {
	private Integer lapNumber;
	private LocalTime lapDuration;
	private Double averageSpeedLap;
	private LocalTime startLap;
	private Pilot pilot;
	private LocalTime finalLapToPilot;

	public Lap(Integer lapNumber, LocalTime lapDuration, Double averageSpeedLap, LocalTime startLap, Pilot pilot, LocalTime finalLapToPilot) {
		super();
		this.lapNumber = lapNumber;
		this.lapDuration = lapDuration;
		this.averageSpeedLap = averageSpeedLap;
		this.startLap = startLap;
		this.pilot = pilot;
		this.finalLapToPilot = finalLapToPilot;
	}

	public Integer getLapNumber() {
		return lapNumber;
	}

	public LocalTime getLapDuration() {
		return lapDuration;
	}

	public Double getVelocityAverageLap() {
		return averageSpeedLap;
	}

	public LocalTime getStartLap() {
		return startLap;
	}
	
	public Pilot getPilot() {
		return pilot;
	}

	public LocalTime getFinalLapToPilot() {
		return finalLapToPilot;
	}

	@Override
	public String toString() {
		StringBuilder objectLap = new StringBuilder();

		objectLap.append("Lap Number: ").append(this.lapNumber).append("\n");
		objectLap.append("Pilot: ").append(this.pilot.toString()).append("\n");
		objectLap.append("Start Lap: ").append(Utils.localTimeHoursToString(this.startLap)).append("\n");
		objectLap.append("Lap Duration: ").append(Utils.localTimeHoursToString(this.lapDuration)).append("\n");
		objectLap.append("Final Lap: ").append(Utils.localTimeHoursToString(this.finalLapToPilot)).append("\n");
		objectLap.append("Average Speed Lap: ").append(Utils.formatNumber(this.averageSpeedLap)).append("\n");

		return objectLap.toString();
	}

	@Override
	public int compareTo(Lap lap) {
		
		if(this.lapNumber.equals(lap.lapNumber)) {
			if (this.finalLapToPilot.isAfter(lap.finalLapToPilot)) {
				return 1;
			}
			if (this.finalLapToPilot.isBefore(lap.finalLapToPilot)) {
				return -1;
			}
		} else {
			if ((this.lapNumber > lap.lapNumber)) {
				return 1;
			}
			if ((this.lapNumber < lap.lapNumber)) {
				return -1;
			}
		}
		
		return 0;
	}
	
}
