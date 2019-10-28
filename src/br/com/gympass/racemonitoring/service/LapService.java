package br.com.gympass.racemonitoring.service;

import java.util.List;

import br.com.gympass.racemonitoring.bean.Lap;
import br.com.gympass.racemonitoring.dao.LapDao;
import br.com.gympass.racemonitoring.dao.impl.LapDaoImpl;
import br.com.gympass.racemonitoring.exception.BusinessException;

public class LapService {
	private LapDao lapDao = new LapDaoImpl();
	
	public List<Lap> list() {
		List<Lap> laps = this.lapDao.list();
		if (laps == null || laps.size() == 0) {
			throw new BusinessException("Without Laps.");
		}
		
		return laps;
	}
	
	public void save(Lap lap) {
		if(lap != null) {
			this.lapDao.save(lap);
		} else {
			throw new BusinessException("Laps no informed.");
		}
	}
	
	public Lap getBestLapOfRace() {
		Lap lap = this.lapDao.getBestLapOfRace();
		if(lap == null) {
			throw new BusinessException("Without the best lap.");
		}
		return lap;
	}
}
