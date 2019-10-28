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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    private List<Team> repo = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    void initRepo() {
        // https://en.wikipedia.org/wiki/List_of_Formula_One_constructors#Teams

        repo.add(new Team("Ferrari", "Ferrari", CountryIsoCode.IT.toString()));
        repo.add(new Team("Mercedes", "Mercedes", CountryIsoCode.DE.toString()));
        repo.add(new Team("McLaren", "Renault", CountryIsoCode.GB.toString()));
        repo.add(new Team("Red Bull Racing", "Honda", CountryIsoCode.AT.toString()));
        repo.add(new Team("Renault", "Renault", CountryIsoCode.FR.toString()));
        repo.add(new Team("Williams", "Mercedes", CountryIsoCode.GB.toString()));
        repo.add(new Team("Alfa Romeo", "Ferrari", CountryIsoCode.CH.toString()));
        repo.add(new Team("Haas", "Ferrari", CountryIsoCode.US.toString()));
        repo.add(new Team("Racing Point", "BWT Mercedes", CountryIsoCode.GB.toString()));
        repo.add(new Team("Scuderia Toro Rosso", "Honda", CountryIsoCode.IT.toString()));

        //repo.add(new Team("", "", CountryIsoCode.AT.toString()));
    }

    public List<Team> getTeams() {
        return new ArrayList<>(repo);
    }

    public void update(Team team) {
        if (repo.contains(team)) {
            repo.remove(team);
        }
        repo.add(team);
    }

    public void remove(Team team) {
        repo.remove(team);
    }

}
