package at.racemanager.drivers.business;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import at.racemanager.drivers.api.Driver;

@RequestScoped
public class DriverService {

	public List<Driver> getDrivers() {
		List<Driver> result = new ArrayList<>();
		
		Driver mika = new Driver();
		mika.setFirstname("Mika");
		mika.setLastname("Hakkinen");
		result.add(mika);
		
		Driver michael = new Driver();
		michael.setFirstname("Michael");
		michael.setLastname("Schumacher");
		result.add(michael);
		
		return result;
	}
}
