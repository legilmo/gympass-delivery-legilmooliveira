package br.com.gympass.racemonitoring.dao;

import java.util.List;

import br.com.gympass.racemonitoring.bean.Lap;

public interface LapDao {
	
	public void save(Lap lap);
	
	public List<Lap> list();
	
	public Lap getBestLapOfRace();

}
