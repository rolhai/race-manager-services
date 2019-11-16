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

import java.time.LocalDate;

import at.racemanager.drivers.api.model.Driver;
import at.racemanager.drivers.api.model.DriverInfo;

/**
 * builder for driver entities
 *
 * @author rolhai
 */
public final class DriverBuilder {

    private Driver driver = new Driver();

    private DriverInfo driverInfo = new DriverInfo();

    public static DriverBuilder create(Driver driver) {
        DriverBuilder builder = create();
        if (driver != null) {
            builder.id(driver.getId())
            .firstname(driver.getFirstname())
            .lastname(driver.getLastname())
            .birthday(driver.getBirthday())
            .country(driver.getCountry())
            .carNumber(driver.getCarNumber());
        }
        if (driver.getInfo() != null) {
            builder.wikipedia(driver.getInfo().getWikipedia());
        }
        return builder;
    }

    private DriverBuilder() {
        super();
    }

    public static DriverBuilder create() {
        return new DriverBuilder();
    }

    public static DriverBuilder merge(Driver target, Driver changes) {
        DriverBuilder builder = create(target);
        if (changes != null) {
            // the ID is not merged
            if (changes.getFirstname() != null) {
                builder.firstname(changes.getFirstname());
            }
            if (changes.getLastname() != null) {
                builder.lastname(changes.getLastname());
            }
            if (changes.getBirthday() != null) {
                builder.birthday(changes.getBirthday());
            }
            if (changes.getCarNumber() != null) {
                builder.carNumber(changes.getCarNumber());
            }
            DriverInfo infoChanges = changes.getInfo();
            if (infoChanges != null) {
                if (infoChanges.getWikipedia() != null) {
                    builder.wikipedia(infoChanges.getWikipedia());
                }
            }
        }
        return builder;
    }

    public DriverBuilder id(Long id) {
        driver.setId(id);
        return this;
    }

    public DriverBuilder firstname(String firstname) {
        driver.setFirstname(firstname);
        return this;
    }

    public DriverBuilder lastname(String lastname) {
        driver.setLastname(lastname);
        return this;
    }

    public DriverBuilder birthday(LocalDate birthday) {
        driver.setBirthday(birthday);
        return this;
    }

    public DriverBuilder country(String country) {
        driver.setCountry(country);
        return this;
    }

    public DriverBuilder carNumber(Integer carNumber) {
        driver.setCarNumber(carNumber);
        return this;
    }

    public DriverBuilder wikipedia(String wikipedia) {
        driverInfo.setWikipedia(wikipedia);
        return this;
    }

    public Driver build() {
        driver.setInfo(driverInfo);
        return driver;
    }
}
