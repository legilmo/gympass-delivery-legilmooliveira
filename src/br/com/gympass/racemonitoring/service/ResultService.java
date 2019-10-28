package br.com.gympass.racemonitoring.service;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.gympass.racemonitoring.bean.Lap;
import br.com.gympass.racemonitoring.bean.Pilot;
import br.com.gympass.racemonitoring.util.Utils;

public class ResultService {
	private PilotService pilotService = new PilotService();
	private Integer      position     = 0;
	private LocalTime    finishTimeWinner;

	public List<Pilot> processResult() {
		List<Pilot> pilots = this.pilotService.list();

		pilots.forEach(pilot -> {
			// Set Laps pilot.
			pilot.setLaps(pilotService.getLapsPilot(pilot));

			// Set number laps.
			pilot.setNumberLaps(pilot.getLaps().size());

			//#Set Best Lap By Pilot.
			pilot.setBestLap(Collections.min(pilot.getLaps(), Comparator.comparing(lap -> lap.getLapDuration())));

			// Calculate total time .
			pilot.getLaps().forEach(lap -> {
				pilot.setTotalLapsTime(Utils.addLocalTime(pilot.getTotalLapsTime(), lap.getLapDuration()));
			});
		});

		// Order the Pilots
		Collections.sort(pilots);

		pilots.forEach(pilot -> {
			// Set position.
			pilot.setPosition(++this.position);

			//Calculate Average speed each pilot.
			Double totalVelocityAverageLap = pilot.getLaps().stream().map(lap -> lap.getVelocityAverageLap())
					.mapToDouble(Double::doubleValue).sum();
			pilot.setVelocityAverageRunning(totalVelocityAverageLap / pilot.getNumberLaps());

			// Get total time of winner.
			if (pilot.getPosition().equals(1)) {
				this.finishTimeWinner = pilot.getTotalLapsTime();
			}

			if (!pilot.getPosition().equals(1)) {
				//get finish time by pilot after winner.
				pilot.setFinishTimeAfterWinner(
						Utils.subtractionLocalTime(pilot.getTotalLapsTime(), this.finishTimeWinner));
			}
		});

		return pilots;
	}

	public void printResult(List<Pilot> pilots) {
		preHeader("                    Final Result ");
		header("|Position | Id-Name Pilot     |Laps Completed| Total Time  |");

		pilots.forEach(pilot -> {
			System.out.print(String.format("%-11.11s", String.format("%so", pilot.getPosition())));
			System.out.print(String.format("%-25.25s", String.format("%s - %s", pilot.getId(), pilot.getName())));
			System.out.print(String.format("%-10.10s", String.format("%s", pilot.getNumberLaps())));
			System.out.println(pilot.getTotalLapsTime());
		});
		
	}

	public void printBestLapsPerPilot(List<Pilot> pilots) {
		preHeader("                 Best Lap By Pilot");
		header("|ID Pilot - Name Pilot | Lap Number      |       Lap Time  |");
		pilots.forEach(pilot -> {
			System.out.print(String.format("%-30.30s", String.format("%s - %s", pilot.getId(), pilot.getName())));
			System.out.print(String.format("%-15.15s", pilot.getBestLap().getLapNumber()));
			System.out.println(pilot.getBestLap().getLapDuration());
		});
	}

	public void printBestLapOfRace(Lap lap) {
		preHeader("                  Best Lap of the Race");
        header("|ID Pilot - Name Pilot  |   Lap Number    | Lap Time       |");
		System.out.print(
				String.format("%-30.30s", String.format("%s - %s", lap.getPilot().getId(), lap.getPilot().getName())));
		System.out.print(String.format("%-15.15s", lap.getLapNumber()));
		System.out.println(lap.getLapDuration());
	}

	public void printAverageSpeedByPilot(List<Pilot> pilots) {
		preHeader("                     Average Speed By Pilot");
		header("|ID Pilot - Name Pilot | Number of Turns | Average Speed   |");
		pilots.forEach(pilot -> {
			System.out.print(String.format("%-30.30s", String.format("%s - %s", pilot.getId(), pilot.getName())));
			System.out.print(String.format("%-24.24s", pilot.getNumberLaps()));
			System.out.println(Utils.formatNumber(pilot.getVelocityAverageRunning()));
		});
	}

	public void printTimeBeforeOfWinner(List<Pilot> pilots) {
		preHeader("                          Time After Winner ");
		header("|ID Pilot - Name Pilot    | Position       | Finish Time   |");
	
		pilots.forEach(pilot -> {
			if(pilot.getFinishTimeAfterWinner() != null) {
				System.out.print(String.format("%-30.30s", String.format("%s - %s", pilot.getId(), pilot.getName())));
				System.out.print(String.format("%-15.15s", pilot.getPosition()));
				System.out.println(Utils.localTimeHoursToString(pilot.getFinishTimeAfterWinner()));
			}
		});
		
	}
	
	private void preHeader(String title) {
		System.out.println("");
		System.out.println("============================================================");
		System.out.println(title);
		System.out.println("============================================================");
	}
	private void header(String title) {
		System.out.println(title);
		System.out.println("------------------------------------------------------------");
	}
	
}
