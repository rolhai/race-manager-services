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
package at.racemanager.seasons.logic;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.bson.types.ObjectId;

import at.racemanager.seasons.api.model.Driver;
import at.racemanager.seasons.api.model.Season;
import at.racemanager.seasons.api.model.Team;

/**
 * business logic for race seasons
 *
 * @author rolhai
 */
@RequestScoped
public class SeasonService {

    private static final Logger LOGGER = Logger.getLogger(SeasonService.class.getName());

    /**
     * @return a list of seasons
     */
    public List<Season> getSeasons() {
        return Season.listAll();
    }

    /**
     * @return a list of teams
     */
    public List<Team> getTeams() {
        return Team.listAll();
    }

    /**
     * @return a list of drivers
     */
    public List<Driver> getDrivers() {
        return Driver.listAll();
    }

    /**
     * add a season
     * @param season the season
     * @return a season with id
     */
    public Season addSeason(Season season) {
        if (season == null) {
            LOGGER.info("season not found");
            throw new BadRequestException();
        }

        season.persist();
        return season;
    }

    /**
     * add a team
     * @param seasonId id of the season
     * @param team the team
     * @return a team with id
     */
    public Team addTeam(ObjectId seasonId, Team team) {
        if (seasonId == null) {
            LOGGER.info("seasonId not found");
            throw new NotFoundException();
        }
        if (team == null) {
            LOGGER.info("team bad request");
            throw new BadRequestException();
        }

        Season season = Season.findById(seasonId);
        if (season == null) {
            LOGGER.info("season not found");
            throw new NotFoundException();
        }
        team.persistOrUpdate();
        season.teams.add(team);
        season.update();
        return team;
    }

    /**
     * add a driver
     * @param seasonId id of the season
     * @param teamId id of the team
     * @param driver the driver
     * @return a driver with id
     */
    public Driver addDriver(ObjectId seasonId, ObjectId teamId, Driver driver) {
        if (seasonId == null) {
            LOGGER.info("seasonId not found");
            throw new NotFoundException();
        }
        if (teamId == null) {
            LOGGER.info("teamId not found");
            throw new NotFoundException();
        }
        if (driver == null) {
            LOGGER.info("driver bad request");
            throw new BadRequestException();
        }

        Season season = Season.findById(seasonId);
        if (season == null) {
            LOGGER.info("season not found");
            throw new NotFoundException();
        }
        Team team = season.teams.stream()
                .filter(t -> teamId.equals(t.id))
                .findFirst()
                .orElse(null);
        if (team == null) {
            LOGGER.info("team not found");
            throw new NotFoundException();
        }
        driver.persistOrUpdate();
        team.drivers.add(driver);
        season.update();
        return driver;
    }

    /**
     * delete a season
     * @param seasonId id of the season
     */
    public void deleteSeason(ObjectId seasonId) {
        if (seasonId == null) {
            LOGGER.info("seasonId not found");
            throw new NotFoundException();
        }

        Season season = Season.findById(seasonId);
        if (season == null) {
            LOGGER.info("season not found");
            throw new NotFoundException();
        }
        season.delete();
    }

    /**
     * delete a team
     * @param seasonId id of the season
     * @param teamId id of the team
     */
    public void deleteTeam(ObjectId seasonId, Object teamId) {
        if (seasonId == null) {
            LOGGER.info("seasonId not found");
            throw new NotFoundException();
        }
        if (teamId == null) {
            LOGGER.info("teamId not found");
            throw new NotFoundException();
        }

        Season season = Season.findById(seasonId);
        if (season == null) {
            LOGGER.info("season not found");
            throw new NotFoundException();
        }
        Team team = season.teams.stream()
                .filter(t -> teamId.equals(t.id))
                .findFirst()
                .orElse(null);
        if (team == null) {
            LOGGER.info("team not found");
            throw new NotFoundException();
        }
        season.teams.remove(team);
        team.delete();
        season.update();
    }

    /**
     * delete a driver
     * @param seasonId id of the season
     * @param teamId id of the team
     * @param driverId id of the driver
     */
    public void deleteDriver(ObjectId seasonId, ObjectId teamId, ObjectId driverId) {
        if (seasonId == null) {
            LOGGER.info("seasonId not found");
            throw new NotFoundException();
        }
        if (teamId == null) {
            LOGGER.info("teamId not found");
            throw new NotFoundException();
        }
        if (driverId == null) {
            LOGGER.info("driverId not found");
            throw new NotFoundException();
        }
        Season season = Season.findById(seasonId);
        if (season == null) {
            LOGGER.info("season not found");
            throw new NotFoundException();
        }
        Team team = season.teams.stream()
                .filter(t -> teamId.equals(t.id))
                .findFirst()
                .orElse(null);
        if (team == null) {
            LOGGER.info("team not found");
            throw new NotFoundException();
        }
        Driver driver = team.drivers.stream()
                .filter(d -> driverId.equals(d.id))
                .findFirst()
                .orElse(null);
        if (driver == null) {
            LOGGER.info("driver not found");
            throw new NotFoundException();
        }
        team.drivers.remove(driver);
        driver.delete();
        season.update();
    }
}
