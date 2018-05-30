package com.lewisjmorgan.malzahar.riot.lol.champion

import com.beust.klaxon.Klaxon
import com.lewisjmorgan.malzahar.riot.RiotApi
import io.reactivex.Single

/**
 * Retrieve all champions.
 * @receiver RiotApi.Companion
 * @param freeToPlay Boolean
 */
fun RiotApi.getChampions(freeToPlay: Boolean): Single<List<ChampionDto>> {
  return getJsonResponseString("platform/v3/champions", listOf("freeToPlay" to freeToPlay.toString())).map { result ->
    Klaxon().parse<ChampionListDto>(result)!!.champions
  }
}

/**
 * Retrieve champion by ID.
 * @receiver RiotApi
 * @param id Long
 * @return Single<ChampionDto>
 */
fun RiotApi.getChampion(id: Long): Single<ChampionDto> {
  return getJsonResponseString("platform/v3/champions/$id").map { result ->
    Klaxon().parse<ChampionDto>(result)
  }
}
