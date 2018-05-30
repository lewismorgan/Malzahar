package com.lewisjmorgan.malzahar.riot.lol.champion.league

import com.beust.klaxon.Klaxon
import com.lewisjmorgan.malzahar.riot.RiotApi
import io.reactivex.Single

private const val BASE_PATH = "league/v3"

/**
 * Get the challenger league for given queue.
 * @receiver RiotApi
 * @param queue String
 * @return Single<List<LeagueDto>>
 */
fun RiotApi.getChallengerLeagueByQueue(queue: String): Single<LeagueListDto> {
  return getJsonResponseString("$BASE_PATH/challengerleagues/by-queue/$queue").map { result ->
    Klaxon().parse<LeagueListDto>(result)
  }
}

fun RiotApi.getLeagueById(leagueId: String): Single<LeagueListDto> {
  return getJsonResponseString("$BASE_PATH/leagues/$leagueId").map { result ->
    Klaxon().parse<LeagueListDto>(result)
  }
}

fun RiotApi.getMasterLeagueByQueue(queue: String): Single<LeagueListDto> {
  return getJsonResponseString("$BASE_PATH/masterleagues/by-queue/$queue").map { result ->
    Klaxon().parse<LeagueListDto>(result)
  }
}

fun RiotApi.getLeaguePositionsBySummoner(summonerId: Long): Single<List<LeaguePositionDto>> {
  return getJsonResponseString("$BASE_PATH/positions/by-summoner/$summonerId").map { result ->
    Klaxon().parseArray<LeaguePositionDto>(result)
  }
}
