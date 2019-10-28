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
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import at.racemanager.drivers.api.model.Driver;
import at.racemanager.drivers.api.model.DriverInfo;

/**
 * repository for drivers
 *
 * @author rolhai
 */
@ApplicationScoped
public class DriverRepository {

    private final List<Driver> repo = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    void initRepo() {
        // https://en.wikipedia.org/wiki/List_of_Formula_One_driver_numbers

        DriverBuilder hamilton = DriverBuilder.create()
                .firstname("Lewis").lastname("Hamilton")
                .country(CountryIsoCode.GB.toString())
                .birthday(LocalDate.of(1985, Month.JANUARY, 7))
                .carNumber(44)
                .wikipedia("https://en.wikipedia.org/wiki/Lewis_Hamilton");
        repo.add(hamilton.build());

        DriverBuilder bottas = DriverBuilder.create()
                .firstname("Valtteri")
                .lastname("Bottas")
                .country(CountryIsoCode.FI.toString())
                .birthday(LocalDate.of(1989, Month.AUGUST, 28))
                .carNumber(77)
                .wikipedia("https://en.wikipedia.org/wiki/Valtteri_Bottas");
        repo.add(bottas.build());

        DriverBuilder leclerc = DriverBuilder.create()
                .firstname("Charles")
                .lastname("Leclerc")
                .country(CountryIsoCode.MC.toString())
                .birthday(LocalDate.of(1997, Month.OCTOBER, 16))
                .carNumber(16)
                .wikipedia("https://en.wikipedia.org/wiki/Charles_Leclerc");
        repo.add(leclerc.build());

        DriverBuilder vettel = DriverBuilder.create()
                .firstname("Sebastian")
                .lastname("Vettel")
                .country(CountryIsoCode.DE.toString())
                .birthday(LocalDate.of(1987, Month.JULY, 3))
                .carNumber(5)
                .wikipedia("https://en.wikipedia.org/wiki/Sebastian_Vettel");
        repo.add(vettel.build());
    }

    public List<Driver> getDrivers() {
        return new ArrayList<>(repo);
    }

    public void update(Driver driver) {
        DriverInfo info = driver.getInfo();
        if (repo.contains(driver)) {
            repo.remove(driver);
        }
        repo.add(driver);
    }

    public void remove(Driver driver) {
        repo.remove(driver);
    }
}
