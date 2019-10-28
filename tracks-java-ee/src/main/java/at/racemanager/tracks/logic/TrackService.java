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

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import at.racemanager.tracks.api.model.Track;


/**
 * business logic for tracks
 *
 * @author rolhai
 */
@RequestScoped
public class TrackService {

    @Inject
    TrackRepository repo;

    public List<Track> getTracks() {
        return repo.getTracks();
    }

    public void update(Track track) {
        repo.update(track);
    }

    public void remove(Track track) {
        repo.remove(track);
    }
}
