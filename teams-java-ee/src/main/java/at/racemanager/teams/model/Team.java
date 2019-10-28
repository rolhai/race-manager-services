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
package at.racemanager.teams.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * entity for a team
 *
 * @author rolhai
 */
public class Team {

    @NotNull
    private String constructor;

    @NotNull
    private String engine;

    @NotNull
    @Size(min = 2, max = 2)
    private String country;

    /**
     * @param constructor
     * @param engine
     * @param country
     */
    public Team(@NotNull String constructor, @NotNull String engine, @NotNull @Size(min = 2, max = 2) String country) {
        super();
        this.constructor = constructor;
        this.engine = engine;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Team [constructor=" + constructor + ", engine=" + engine + ", country=" + country + "]";
    }

    public String getConstructor() {
        return constructor;
    }

    public void setConstructor(String constructor) {
        this.constructor = constructor;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
