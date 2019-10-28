package br.com.gympass.racemonitoring.service;

import java.util.ArrayList;
import java.util.List;

import br.com.gympass.racemonitoring.bean.Lap;
import br.com.gympass.racemonitoring.bean.Pilot;
import br.com.gympass.racemonitoring.dao.PilotDao;
import br.com.gympass.racemonitoring.dao.impl.PilotDaoImpl;
import br.com.gympass.racemonitoring.exception.BusinessException;

public class PilotService {
	private PilotDao pilotDao = new PilotDaoImpl();
	
	public void save(Pilot pilot, Lap lap) {
		if (pilot != null) {
			this.pilotDao.saveLapsPilot(pilot, lap);								
		} else {
			throw new BusinessException("Pilot not informed.");
		}
	}

	public List<Pilot> list() {
		List<Pilot> pilots = new ArrayList<Pilot>();
		
		pilots = this.pilotDao.list();
		if (pilots == null || pilots.size() == 0) {
			throw new BusinessException("Without pilots.");
		}
		return pilots;
	}
	
	public List<Lap> getLapsPilot(Pilot pilot) {
		List<Lap> laps = null;
		if (pilot != null) {
			laps = this.pilotDao.getLapsPilot(pilot);	
			if(laps == null || laps.size() == 0) {
				throw new BusinessException("Without pilots.");
			}
		} else {
			throw new BusinessException("Pilot not informed.");
		}
		return laps;
	}

}
