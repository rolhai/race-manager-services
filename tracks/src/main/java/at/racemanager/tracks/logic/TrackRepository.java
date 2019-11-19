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
package at.racemanager.tracks.logic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import at.racemanager.tracks.api.model.Track;

/**
 * repository for tracks
 *
 * @author rolhai
 */
@ApplicationScoped
public class TrackRepository {

    private final Set<Track> repo = Collections.synchronizedSet(new HashSet<>());

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
        // https://en.wikipedia.org/wiki/List_of_Formula_One_circuits

        repo.add(new Track(1L, "Australian Grand Prix", "Adelaide Street Circuit", "Adelaide", CountryIsoCode.AU.toString()));
        repo.add(new Track(2L, "San Marino Grand Prix", "Autodromo Enzo e Dino Ferrari", "Imola", CountryIsoCode.IT.toString()));
        repo.add(new Track(3L, "Italian Grand Prix", "Autodromo Nazionale Monza", "Monza", CountryIsoCode.IT.toString()));
        repo.add(new Track(4L, "Monaco Grand Prix", "Circuit de Monaco", "Monte Carlo", CountryIsoCode.MC.toString()));
        //https://en.wikipedia.org/wiki/Circuit_de_Monaco

        //repo.add(new Track("", "", ""));

    }

    /**
     * determine all entities
     * @return all entities
     */
    public Set<Track> getAll() {
        return new HashSet<>(repo);
    }

    /**
     * determine the entity with id
     * @param entityId entity-id
     * @return the entity with id
     */
    public Track get(long entityId) {
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
    public Track insert(Track entity) {
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
            Track found = get(entity.getId());
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
    public Track update(Track entity) {
        if (entity == null) {
            // no changes
            return null;
        }
        if (entity.getId() == null) {
            // no id, update not possible
            return null;
        }
        Track found = get(entity.getId());
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
    public void remove(Track entity) {
        if (entity == null) {
            return;
        }
        repo.remove(entity);
    }
}
