package com.lewisjmorgan.malzahar.riot.league

data class MiniSeriesDto(val wins: Int, val losses: Int, val target: Int, val progress: String)

data class LeagueItemDto(val rank: String, val hotStreak: Boolean, val miniSeries: MiniSeriesDto?,
                         val wins: Int, val veteran: Boolean, val losses: Int, val freshBlood: Boolean,
                         val playerOrTeamName: String, val inactive: Boolean, val playerOrTeamId: String,
                         val leaguePoints: Int) {
  @Suppress("unused")
  constructor(rank: String, hotStreak: Boolean, wins: Int, veteran: Boolean, losses: Int, freshBlood: Boolean,
              playerOrTeamId: String, playerOrTeamName: String, inactive: Boolean, leaguePoints: Int) :
      this(rank, hotStreak, null, wins, veteran,
          losses, freshBlood, playerOrTeamName, inactive, playerOrTeamId, leaguePoints)
}

data class LeagueListDto(val leagueId: String, val tier: String, val entries: List<LeagueItemDto>,
                         val queue: String, val name: String)

data class LeaguePositionDto(val rank: String, val queueType: String, val hotStreak: Boolean, val miniSeries: MiniSeriesDto?,
                             val wins: Int, val veteran: Boolean, val losses: Int, val freshBlood: Boolean,
                             val leagueId: String, val playerOrTeamName: String, val inactive: Boolean,
                             val playerOrTeamId: String, val leagueName: String, val tier: String,
                             val leaguePoints: Int) {
  @Suppress("unused")
  constructor(rank: String, queueType: String, hotStreak: Boolean, wins: Int, veteran: Boolean, losses: Int,
              freshBlood: Boolean, leagueId: String, playerOrTeamName: String, inactive: Boolean,
              playerOrTeamId: String, leagueName: String, tier: String, leaguePoints: Int) :
      this(rank, queueType, hotStreak, null, wins, veteran, losses, freshBlood, leagueId,
          playerOrTeamName, inactive, playerOrTeamId, leagueName, tier, leaguePoints)
}