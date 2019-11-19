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
package at.racemanager.seasons.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

/**
 * entity for a race season
 *
 * @author rolhai
 */
@MongoEntity(collection="Season")
public class Season extends PanacheMongoEntity {

    @NotNull
    public Integer year;

    @NotNull
    public String name;

    public List<Team> teams = new ArrayList<>();

    public List<Track> tracks = new ArrayList<>();

    public static Season findByName(String name) {
        return find("name", name).firstResult();
    }

    public static Season findByYear(int year) {
        return find("year", year).firstResult();
    }
}
