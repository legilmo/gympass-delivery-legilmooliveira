package br.com.gympass.racemonitoring.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.gympass.racemonitoring.bean.Lap;
import br.com.gympass.racemonitoring.dao.LapDao;

public class LapDaoImpl implements LapDao {
	
	private static List<Lap> lapList;
	
	static {
		lapList = new ArrayList<Lap>();
	}

	@Override
	public void save(Lap lap) {		
		lapList.add(lap);
	}

	@Override
	public List<Lap> list() {		
		return lapList;
	}
	
	public Lap getBestLapOfRace() {
		//get the best lap.
		Lap theBestLapOfRace = Collections.min(lapList, Comparator.comparing(lap -> lap .getLapDuration()));
		
		return theBestLapOfRace;		
	}
}
