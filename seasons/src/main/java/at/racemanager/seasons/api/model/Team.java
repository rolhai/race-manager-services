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
import javax.validation.constraints.Size;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

/**
 * entity for a racing team
 *
 * @author rolhai
 */
public class Team extends PanacheMongoEntity {

    @NotNull
    public String constructor;

    @NotNull
    public String engine;

    @NotNull
    @Size(min = 2, max = 2)
    public String country;

    public List<Driver> drivers = new ArrayList<>();
}
