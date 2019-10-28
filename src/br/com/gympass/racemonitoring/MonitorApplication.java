package br.com.gympass.racemonitoring;

import java.util.List;

import br.com.gympass.racemonitoring.bean.Pilot;
import br.com.gympass.racemonitoring.controller.RaceMonitoringController;
import br.com.gympass.racemonitoring.exception.BusinessException;
import br.com.gympass.racemonitoring.exception.ReadFileException;

public class MonitorApplication {

	public static void main(String[] args) {
	
		if (args.length == 0) {
			throw new ReadFileException("No args. You need inform the file path.");	
		}
		String pathFile = args[0];
		if (pathFile != null && !"".equals(pathFile)) {
			RaceMonitoringController raceMonitoringController = new RaceMonitoringController();

			raceMonitoringController.processFile(pathFile);			
			List<Pilot> pilots = raceMonitoringController.processResult();
			
			raceMonitoringController.printResult(pilots);
			raceMonitoringController.printBestLapsByPilot(pilots);
			raceMonitoringController.printBestLapOfRace();
			raceMonitoringController.printAverageSpeedByPilot(pilots);
			raceMonitoringController.printTimeBeforeOfWinner(pilots);
		} else {
			throw new BusinessException("Path no informed.");
		}
	}

}
