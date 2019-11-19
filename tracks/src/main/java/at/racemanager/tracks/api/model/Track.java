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
package at.racemanager.tracks.api.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * entity for a race track
 *
 * @author rolhai
 */
public class Track {

    private Long id;

    @NotNull
    private String gradPrix;

    @NotNull
    private String circuit;

    @NotNull
    private String location;

    @NotNull
    @Size(min = 2, max = 2)
    private String country;

    /**
     * @param gradPrix
     * @param circuit
     * @param location
     * @param country
     */
    public Track(Long id, String gradPrix, String circuit, String location, String country) {
        super();
        this.id = id;
        this.gradPrix = gradPrix;
        this.circuit = circuit;
        this.location = location;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Track [id=" + id + ", gradPrix=" + gradPrix + ", circuit=" + circuit + ", location=" + location
                + ", country=" + country + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCircuit() {
        return circuit;
    }

    public void setCircuit(String circuit) {
        this.circuit = circuit;
    }

    public String getGradPrix() {
        return gradPrix;
    }

    public void setGradPrix(String gradPrix) {
        this.gradPrix = gradPrix;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((circuit == null) ? 0 : circuit.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Track other = (Track) obj;
        if (circuit == null) {
            if (other.circuit != null)
                return false;
        } else if (!circuit.equals(other.circuit))
            return false;
        return true;
    }

}
