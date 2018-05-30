package com.lewisjmorgan.malzahar.riot.champion.mastery

import com.beust.klaxon.Klaxon
import com.lewisjmorgan.malzahar.riot.RiotApi
import io.reactivex.Single

private const val BASE_PATH = "champion-mastery/v3"

/**
 * Get all champion mastery entries sorted by number of champion points descending
 * @receiver RiotApi
 * @param summonerId Long
 * @return Single<List<ChampionMasteryDto>>
 */
fun RiotApi.getMasteriesBySummoner(summonerId: Long): Single<List<ChampionMasteryDto>> {
  return getJsonResponseString("$BASE_PATH/champion-masteries/by-summoner/$summonerId").map { result ->
    Klaxon().parseArray<ChampionMasteryDto>(result)
  }
}

/**
 * Get a champion mastery by player ID and champion ID.
 * @receiver RiotApi
 * @param summonerId Long
 * @param championId Long
 */
fun RiotApi.getMasteryForChampion(summonerId: Long, championId: Long): Single<ChampionMasteryDto> {
  return getJsonResponseString("$BASE_PATH/champion-masteries/by-summoner/$summonerId/by-champion/$championId").map { result ->
    Klaxon().parse<ChampionMasteryDto>(result)
  }
}

/**
 * Get a player's total champion mastery score, which is the sum of individual champion mastery levels.
 * @receiver RiotApi
 * @param summonerId Long
 * @return Single<Long>
 */
fun RiotApi.getTotalMasteryScore(summonerId: Long): Single<Long> {
  return getJsonResponseString("$BASE_PATH/scores/by-summoner/$summonerId").map { result ->
    result.toLong()
  }
}