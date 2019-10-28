package br.com.gympass.racemonitoring.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.gympass.racemonitoring.bean.Lap;
import br.com.gympass.racemonitoring.bean.Pilot;
import br.com.gympass.racemonitoring.dao.PilotDao;

public class PilotDaoImpl implements PilotDao {

	private static Map<Pilot, List<Lap>> pilotMap;

	static {
		pilotMap = new HashMap<Pilot, List<Lap>>();
	}

	@Override
	public void saveLapsPilot(Pilot pilot, Lap lap) {

		if (pilotMap.containsKey(pilot)) {
			List<Lap> laps = pilotMap.get(pilot);
			laps.add(lap);
		} else {
			List<Lap> laps = new ArrayList<Lap>();
			laps.add(lap);
			pilotMap.put(lap.getPilot(), laps);
		}
	}

	@Override
	public List<Pilot> list() {
		List<Pilot> pilotsReturn = new ArrayList<Pilot>();

		pilotMap.keySet().iterator().forEachRemaining(pilotsReturn::add);
		
		return pilotsReturn;
	}

	@Override
	public List<Lap> getLapsPilot(Pilot pilot) {
		return pilotMap.get(pilot);
	}
	
}
