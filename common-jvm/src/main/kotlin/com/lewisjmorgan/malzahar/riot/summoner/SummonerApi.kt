package com.lewisjmorgan.malzahar.riot.summoner

import com.beust.klaxon.Klaxon
import com.lewisjmorgan.malzahar.riot.RiotApi
import io.reactivex.Single

/**
 * Get a summoner by summoner name.
 * @param name String name of the summoner
 * @return Single<SummonerDto>
 */
fun RiotApi.getSummonerByName(name: String): Single<SummonerDto?> {
  return getJsonResponseString("summoner/v3/summoners/by-name/$name").map { result ->
    Klaxon().parse<SummonerDto>(result)
  }!!
}

/**
 * Gets a summoner by their id
 * @param summonerId Long
 * @return Single<SummonerDto?>
 */
fun RiotApi.getSummoner(summonerId: Long): Single<SummonerDto?> {
  return getJsonResponseString("summoner/v3/summoners/$summonerId").map { result ->
    Klaxon().parse<SummonerDto>(result)
  }
}

/**
 * Get a summoner by summoner ID.
 * @param accountId Long
 * @return Single<SummonerDto?>
 */
fun RiotApi.getSummonerByAccount(accountId: Long): Single<SummonerDto?> {
  return getJsonResponseString("summoner/v3/summoners/by-account/$accountId").map { result ->
    Klaxon().parse<SummonerDto>(result)
  }
}