package at.racemanager.drivers.business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import at.racemanager.drivers.api.Driver;

@ApplicationScoped
public class DriverService {
	
	private final List<Driver> driverRepo = new ArrayList<>();
	
	@PostConstruct
	void initRepo() {
		Driver mika = new Driver();
		mika.setFirstname("Mika");
		mika.setLastname("Hakkinen");
		driverRepo.add(mika);
		
		Driver michael = new Driver();
		michael.setFirstname("Michael");
		michael.setLastname("Schumacher");
		driverRepo.add(michael);
	}

	public List<Driver> getDrivers() {
		return new ArrayList<>(driverRepo);
	}
	
	public void add(Driver driver) {
		if (driverRepo.contains(driver)) {
			driverRepo.remove(driver);
		}
		driverRepo.add(driver);
	}
	
	public void remove(Driver driver) {
		driverRepo.remove(driver);
	}
}
