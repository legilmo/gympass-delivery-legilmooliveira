package br.com.gympass.racemonitoring.dao;

import java.util.List;

import br.com.gympass.racemonitoring.bean.Lap;
import br.com.gympass.racemonitoring.bean.Pilot;

public interface PilotDao {
	
	public void saveLapsPilot(Pilot pilot, Lap lap);
	
	public List<Pilot> list();
	
	public List<Lap> getLapsPilot(Pilot pilot);
}
