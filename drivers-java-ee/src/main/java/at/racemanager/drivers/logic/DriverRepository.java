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
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import at.racemanager.drivers.api.model.Driver;

/**
 * repository for drivers
 *
 * @author rolhai
 */
@ApplicationScoped
public class DriverRepository {

    private final Set<Driver> repo = Collections.synchronizedSet(new HashSet<>());

    /**
     * determine the next unused id.
     * result = max + 1
     * @return the next id
     */
    private long nextEntityId() {
        if (repo.isEmpty()) {
            return 1;
        }
        return repo.stream()
                .mapToLong(entity -> entity.getId() == null ? 0 : entity.getId().longValue())
                .max()
                .getAsLong() + 1;
    }

    @PostConstruct
    private void initRepo() {
        // https://en.wikipedia.org/wiki/List_of_Formula_One_driver_numbers

        DriverBuilder hamilton = DriverBuilder.create()
                .firstname("Lewis").lastname("Hamilton")
                .country(CountryIsoCode.GB.toString())
                .birthday(LocalDate.of(1985, Month.JANUARY, 7))
                .carNumber(44)
                .wikipedia("https://en.wikipedia.org/wiki/Lewis_Hamilton");
        insert(hamilton.build());

        DriverBuilder bottas = DriverBuilder.create()
                .firstname("Valtteri")
                .lastname("Bottas")
                .country(CountryIsoCode.FI.toString())
                .birthday(LocalDate.of(1989, Month.AUGUST, 28))
                .carNumber(77)
                .wikipedia("https://en.wikipedia.org/wiki/Valtteri_Bottas");
        insert(bottas.build());

        DriverBuilder leclerc = DriverBuilder.create()
                .firstname("Charles")
                .lastname("Leclerc")
                .country(CountryIsoCode.MC.toString())
                .birthday(LocalDate.of(1997, Month.OCTOBER, 16))
                .carNumber(16)
                .wikipedia("https://en.wikipedia.org/wiki/Charles_Leclerc");
        insert(leclerc.build());

        DriverBuilder vettel = DriverBuilder.create()
                .firstname("Sebastian")
                .lastname("Vettel")
                .country(CountryIsoCode.DE.toString())
                .birthday(LocalDate.of(1987, Month.JULY, 3))
                .carNumber(5)
                .wikipedia("https://en.wikipedia.org/wiki/Sebastian_Vettel");
        insert(vettel.build());
    }

    /**
     * determine all entities
     * @return all entities
     */
    public Set<Driver> getAll() {
        return new HashSet<>(repo);
    }

    /**
     * determine the entity with id
     * @param entityId entity-id
     * @return the entity with id
     */
    public Driver get(long entityId) {
        if (repo.isEmpty()) {
            return null;
        }
        return repo.stream()
                .filter(entity -> Long.valueOf(entityId) == entity.getId())
                .findFirst()
                .orElse(null);
    }

    /**
     * insert new entity and create new id
     * @param entity
     * @return
     */
    public Driver insert(Driver entity) {
        if (entity == null) {
            return null;
        }
        if (repo.contains(entity)) {
            return null;
        }
        if (entity.getId() == null) {
            // if no id is present, create a new id
            entity.setId(nextEntityId());
        }
        else {
            // if id present, look if already used
            Driver found = get(entity.getId());
            if (found != null) {
                // if id used, insert is not possible
                return null;
            }
        }
        repo.add(entity);
        return entity;
    }

    /**
     * update existing entity with existing id
     * @param entity
     * @return
     */
    public Driver update(Driver entity) {
        if (entity == null) {
            // no changes
            return null;
        }
        if (entity.getId() == null) {
            // no id, update not possible
            return null;
        }
        Driver found = get(entity.getId());
        if (found == null) {
            // entity with id not found, update not possible
            return null;
        }
        // replace entity
        repo.remove(found);
        repo.add(entity);
        return entity;
    }

    /**
     * remove existing entity
     * @param entity
     */
    public void remove(Driver entity) {
        if (entity == null) {
            return;
        }
        repo.remove(entity);
    }
}
