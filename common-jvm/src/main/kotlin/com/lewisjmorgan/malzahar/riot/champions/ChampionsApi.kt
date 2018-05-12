package com.lewisjmorgan.malzahar.riot.champions

import com.beust.klaxon.Klaxon
import com.lewisjmorgan.malzahar.riot.RiotApi
import io.reactivex.Single

/**
 * Retrieve all champions.
 * @receiver RiotApi.Companion
 * @param freeToPlay Boolean
 */
fun RiotApi.getChampions(freeToPlay: Boolean): Single<List<ChampionDto>> {
  return getJsonResponseString("", listOf("freeToPlay" to freeToPlay.toString())).map { result ->
    Klaxon().parse<List<ChampionDto>>(result)
  }
}
// TODO Add rest of champion requests
