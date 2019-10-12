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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import at.racemanager.tracks.api.model.Track;

/**
 * @author rolhai
 */
@ApplicationScoped
public class TrackRepository {

    private List<Track> repo = new ArrayList<>();

    @PostConstruct
    void initRepo() {
        // https://en.wikipedia.org/wiki/List_of_Formula_One_circuits

        repo.add(new Track("Adelaide Street Circuit", "Adelaide", CountryIsoCode.AU.toString()));
        repo.add(new Track("", "", ""));
        repo.add(new Track("", "", ""));
        repo.add(new Track("", "", ""));
        repo.add(new Track("", "", ""));

    }

    public List<Track> getTracks() {
        return new ArrayList<>(repo);
    }

    public void update(Track track) {
        if (repo.contains(track)) {
            repo.remove(track);
        }
        repo.add(track);
    }

    public void remove(Track track) {
        repo.remove(track);
    }
}
