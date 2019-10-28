package br.com.gympass.racemonitoring.bean;

import java.time.LocalTime;
import java.util.List;

public class Pilot implements Comparable<Pilot>{
	private String id;
	private String name;
	private Integer position;
	private Integer numberLaps;
	private Lap bestLap;
	private List<Lap> laps;
	private LocalTime totalLapsTime;
	private Double averageSpeedRunning;
	private LocalTime finishTimeAfterWinner;

	public Pilot(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getNumberLaps() {
		return numberLaps;
	}

	public void setNumberLaps(Integer numberLaps) {
		this.numberLaps = numberLaps;
	}

	public Lap getBestLap() {
		return bestLap;
	}

	public void setBestLap(Lap bestLap) {
		this.bestLap = bestLap;
	}

	public List<Lap> getLaps() {
		return laps;
	}

	public void setLaps(List<Lap> laps) {
		this.laps = laps;
	}

	public LocalTime getTotalLapsTime() {
		return totalLapsTime;
	}

	public void setTotalLapsTime(LocalTime totalLapsTime) {
		this.totalLapsTime = totalLapsTime;
	}

	public Double getVelocityAverageRunning() {
		return averageSpeedRunning;
	}

	public void setVelocityAverageRunning(Double averageSpeedRunning) {
		this.averageSpeedRunning = averageSpeedRunning;
	}

	public LocalTime getFinishTimeAfterWinner() {
		return finishTimeAfterWinner;
	}

	public void setFinishTimeAfterWinner(LocalTime finishTimeAfterWinner) {
		this.finishTimeAfterWinner = finishTimeAfterWinner;
	}

	@Override
	public String toString() {
		StringBuilder objectPilot = new StringBuilder();

		objectPilot.append(this.id);
		objectPilot.append(" - ");
		objectPilot.append(this.name);

		return objectPilot.toString();
	}	

	@Override
	public boolean equals(Object pilotComparator) {

		Pilot pilot = (Pilot) pilotComparator;
		
		return ((this.id.equals(pilot.id)) && (this.name.equals(pilot.name)));
		
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public int compareTo(Pilot pilot) {
		if(this.numberLaps > pilot.numberLaps) {
			return -1;
		}
		if(this.numberLaps < pilot.numberLaps) {
			return 1;
		}
		
		return this.totalLapsTime.compareTo(pilot.totalLapsTime);
	}
	
	
}
