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
package at.racemanager.teams.logic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import at.racemanager.teams.model.Team;

/**
 * repository for teams
 *
 * @author rolhai
 */
@ApplicationScoped
public class TeamRepository {

    private final Set<Team> repo = Collections.synchronizedSet(new HashSet<>());

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
    void initRepo() {
        // https://en.wikipedia.org/wiki/List_of_Formula_One_constructors#Teams

        repo.add(new Team(1L, "Ferrari", "Ferrari", CountryIsoCode.IT.toString()));
        repo.add(new Team(2L, "Mercedes", "Mercedes", CountryIsoCode.DE.toString()));
        repo.add(new Team(3L, "McLaren", "Renault", CountryIsoCode.GB.toString()));
        repo.add(new Team(4L, "Red Bull Racing", "Honda", CountryIsoCode.AT.toString()));
        repo.add(new Team(5L, "Renault", "Renault", CountryIsoCode.FR.toString()));
        repo.add(new Team(6L, "Williams", "Mercedes", CountryIsoCode.GB.toString()));
        repo.add(new Team(7L, "Alfa Romeo", "Ferrari", CountryIsoCode.CH.toString()));
        repo.add(new Team(8L, "Haas", "Ferrari", CountryIsoCode.US.toString()));
        repo.add(new Team(9L, "Racing Point", "BWT Mercedes", CountryIsoCode.GB.toString()));
        repo.add(new Team(10L, "Scuderia Toro Rosso", "Honda", CountryIsoCode.IT.toString()));

        //repo.add(new Team("", "", CountryIsoCode.AT.toString()));
    }

    /**
     * determine all entities
     * @return all entities
     */
    public Set<Team> getAll() {
        return new HashSet<>(repo);
    }

    /**
     * determine the entity with id
     * @param entityId entity-id
     * @return the entity with id
     */
    public Team get(long entityId) {
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
    public Team insert(Team entity) {
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
            Team found = get(entity.getId());
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
    public Team update(Team entity) {
        if (entity == null) {
            // no changes
            return null;
        }
        if (entity.getId() == null) {
            // no id, update not possible
            return null;
        }
        Team found = get(entity.getId());
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
    public void remove(Team entity) {
        if (entity == null) {
            return;
        }
        repo.remove(entity);
    }

}
