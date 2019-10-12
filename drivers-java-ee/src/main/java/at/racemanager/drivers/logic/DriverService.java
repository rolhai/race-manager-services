/**
 * Copyright (c) 2019 by rolhai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.racemanager.drivers.logic;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import at.racemanager.drivers.api.model.Driver;

/**
 * business logic for drivers
 *
 * @author rolhai
 */
@RequestScoped
public class DriverService {

    @Inject
    private DriverRepository repo;

    public List<Driver> getDrivers() {
        return repo.getDrivers();
    }

    public void update(Driver driver) {
        repo.update(driver);
    }

    public void remove(Driver driver) {
        repo.remove(driver);
    }
}