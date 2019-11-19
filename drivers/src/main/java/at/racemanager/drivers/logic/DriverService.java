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

import java.util.Set;

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

    /**
     * repository for drivers
     */
    @Inject
    DriverRepository repo;

    /**
     * get all entities
     * @return all entities
     */
    public Set<Driver> getAll() {
        return repo.getAll();
    }

    /**
     * get the entity with id
     * @param entityId if of the entity
     * @return the entity with id
     */
    public Driver get(long entityId) {
        if (entityId < 1) {
            throw new ResourceNotFoundException();
        }
        return repo.get(entityId);
    }

    /**
     * create entity with new id
     * @param entity entity
     * @return
     */
    public Driver create(Driver entity) {
        if (entity == null) {
            throw new InvalidRequestParameterException();
        }
        Driver result = DriverBuilder
                .create(entity)
                .id(null)
                .build();
        result = repo.insert(result);
        if (result == null || result.getId() == null) {
            throw new ResourceNotCreatedException();
        }
        return result;
    }

    public Driver save(long entityId, Driver entity) {
        if (entityId < 1) {
            throw new ResourceNotFoundException();
        }
        if (entity == null) {
            throw new InvalidRequestParameterException();
        }
        Driver result = DriverBuilder
                .create(entity)
                .id(entityId)
                .build();
        Driver found = get(entityId);
        if (found == null) {
            result = repo.insert(result);
        }
        else {
            result = repo.update(result);
        }
        if (result == null || result.getId() == null) {
            throw new ResourceNotCreatedException();
        }
        return result;
    }

    /**
     * update changed values and return complete entity
     * @param entityId id of the entity
     * @param changes values to change
     * @return the complete entity with changes
     */
    public Driver update(long entityId, Driver changes) {
        if (entityId < 1) {
            throw new ResourceNotFoundException();
        }
        if (changes == null) {
            throw new InvalidRequestParameterException();
        }
        Driver found = repo.get(entityId);
        if (found == null) {
            throw new ResourceNotFoundException();
        }
        Driver result = DriverBuilder.merge(found, changes).build();
        result = repo.update(result);
        if (result == null || result.getId() == null) {
            throw new ResourceNotCreatedException();
        }
        return result;
    }

    /**
     * remove entity with id
     * @param entityId id of the entity
     */
    public void remove(long entityId) {
        if (entityId < 1) {
            throw new ResourceNotFoundException();
        }
        Driver found = repo.get(entityId);
        if (found == null) {
            throw new ResourceNotFoundException();
        }
        repo.remove(found);
    }
}
