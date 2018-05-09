package com.lewisjmorgan.malzahar.riot

import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.rx.rx_responseString
import com.github.kittinunf.result.Result
import com.google.common.flogger.FluentLogger
import com.lewisjmorgan.malzahar.riot.league.dto.SummonerDto
import io.reactivex.Single

class RiotApi(key: String) {
  companion object {
    val logger = FluentLogger.forEnclosingClass()!!
  }

  init {
    FuelManager.instance.basePath = "https://na1.api.riotgames.com/lol"
    FuelManager.instance.baseParams = listOf("api_key" to key)
    // TODO Add Response interceptor for intercepting results that are not 200
  }

  /**
   * Get a summoner by summoner name.
   * @param name String name of the summoner
   * @return Single<SummonerDto>
   */
  fun getSummonerByName(name: String): Single<SummonerDto?> {
    return getJsonResponseString("summoner/v3/summoners/by-name/$name").map { (_, result) ->
      Klaxon().parse<SummonerDto>(result.component1()!!)
    }!!
  }

  /**
   * Gets a summoner by their id
   * @param summonerId Long
   * @return Single<SummonerDto?>
   */
  fun getSummoner(summonerId: Long): Single<SummonerDto?> {
    return getJsonResponseString("summoner/v3/summoners/$summonerId").map { (_, result) ->
      Klaxon().parse<SummonerDto>(result.component1()!!)
    }
  }

  /**
   * Get a summoner by summoner ID.
   * @param accountId Long
   * @return Single<SummonerDto?>
   */
  fun getSummonerByAccount(accountId: Long): Single<SummonerDto?> {
    return getJsonResponseString("summoner/v3/summoners/by-account/$accountId").map { (_, result) ->
      Klaxon().parse<SummonerDto>(result.component1()!!)
    }
  }

  private fun getJsonResponseString(path: String): Single<Pair<Response, Result<String, FuelError>>> {
    val url = Fuel.get(path)

    logger.atInfo().log(url.url.toExternalForm())
    // TODO Figure out why subscribing on IO is not working.
    // TODO Handle FuelError if one arises by the Result (Not a response error)
    return Fuel.get(path).rx_responseString()
  }
}