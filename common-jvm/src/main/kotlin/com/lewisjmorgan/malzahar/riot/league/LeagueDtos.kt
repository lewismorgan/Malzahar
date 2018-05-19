package com.lewisjmorgan.malzahar.riot.league

data class MiniSeriesDto(val wins: Int, val losses: Int, val target: Int, val progress: String)

data class LeagueItemDto(val rank: String, val hotStreak: Boolean, val miniSeries: MiniSeriesDto? = null,
                         val wins: Int, val veteran: Boolean, val losses: Int, val freshBlood: Boolean,
                         val playerOrTeamName: String, val inactive: Boolean, val playerOrTeamId: String,
                         val leaguePoints: Int)

data class LeagueListDto(val leagueId: String, val tier: String, val entries: List<LeagueItemDto>,
                         val queue: String, val name: String)

data class LeaguePositionDto(val rank: String, val queueType: String, val hotStreak: Boolean, val miniSeries: MiniSeriesDto? = null,
                             val wins: Int, val veteran: Boolean, val losses: Int, val freshBlood: Boolean,
                             val leagueId: String, val playerOrTeamName: String, val inactive: Boolean,
                             val playerOrTeamId: String, val leagueName: String, val tier: String,
                             val leaguePoints: Int)