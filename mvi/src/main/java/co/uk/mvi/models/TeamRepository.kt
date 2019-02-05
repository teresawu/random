package co.uk.mvi.models

import co.uk.mvi.list.models.Team

class TeamRepository {
    fun fetchTeamList(): List<Team> {
        return listOf(
                Team("Team 1", "url1"),
                Team("Team 2", "url2"),
                Team("Team 3", "url3"),
                Team("Team 4", "url4"),
                Team("Team 5", "url5"),
                Team("Team 6", "url6"),
                Team("Team 7", "url7"))
    }
}