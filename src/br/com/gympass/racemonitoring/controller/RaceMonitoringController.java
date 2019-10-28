package br.com.gympass.racemonitoring.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import br.com.gympass.racemonitoring.bean.Lap;
import br.com.gympass.racemonitoring.bean.Pilot;
import br.com.gympass.racemonitoring.constants.Constants;
import br.com.gympass.racemonitoring.exception.ReadFileException;
import br.com.gympass.racemonitoring.service.LapService;
import br.com.gympass.racemonitoring.service.PilotService;
import br.com.gympass.racemonitoring.service.ResultService;
import br.com.gympass.racemonitoring.util.Utils;

public class RaceMonitoringController {
    
	private LapService    lapService    = new LapService();
	private PilotService  pilotService  = new PilotService();
	private ResultService resultService = new ResultService();

	public void processFile(String pathFile) {
		Path path = null;
		Stream<String> fileContent = null;
		try {
			path = Paths.get(pathFile);
			fileContent = Files.lines(path);
			//read lines
			fileContent.forEach(line -> {
				if (verifyHeaderFile(line)) {
					Lap lap = Utils.lineToLap(line); 
					this.lapService.save(lap);
					// Set laps.
					this.pilotService.save(lap.getPilot(), lap);
				}
			});
			Collections.sort(this.lapService.list());
		} catch (IOException e) {
			throw new ReadFileException(String.format("Error reading file: %s", e.getMessage()));
		} finally {
			fileContent.close();
			path = null;
		}
	}
	
	private boolean verifyHeaderFile (String line) {
		return (!Constants.HORA_HEADER.equals(line.substring(0, 10).trim())) ;
	}

	public List<Pilot> processResult() {
		List<Pilot> pilots = null;
		//Mount the result: Position, ID Pilot, Name Pilot, Amount Completed and Total Trial Time.
		pilots = this.resultService.processResult();
		
		return pilots;
	}
	
	public void printResult(List<Pilot> pilots) {		
		this.resultService.printResult(pilots);
	}
	
	public void printBestLapsByPilot(List<Pilot> pilots) {
		this.resultService.printBestLapsPerPilot(pilots);
	}
	
	public void printBestLapOfRace() {
		Lap lap = this.lapService.getBestLapOfRace();
		this.resultService.printBestLapOfRace(lap);
	}
	
	public void printAverageSpeedByPilot(List<Pilot> pilots) {
		this.resultService.printAverageSpeedByPilot(pilots);
	}
	
	public void printTimeBeforeOfWinner(List<Pilot> pilots) {
		this.resultService.printTimeBeforeOfWinner(pilots);
	}

}
