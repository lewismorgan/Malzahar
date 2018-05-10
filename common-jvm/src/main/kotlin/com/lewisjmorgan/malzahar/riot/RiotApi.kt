package com.lewisjmorgan.malzahar.riot

import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.rx.rx_responseString
import com.github.kittinunf.result.Result
import com.google.common.flogger.FluentLogger
import com.lewisjmorgan.malzahar.riot.league.dto.SummonerDto
import io.reactivex.Single

// TODO Move summoner methods into API extension functions

class RiotApi(key: String) {
  companion object {
    val logger = FluentLogger.forEnclosingClass()!!
  }

  init {
    FuelManager.instance.basePath = "https://na1.api.riotgames.com/lol"
    FuelManager.instance.baseParams = listOf("api_key" to key)
    FuelManager.instance.removeAllResponseInterceptors()
    FuelManager.instance.addResponseInterceptor { riotValidatorResponseInterceptor() }
  }

  /**
   * Get a summoner by summoner name.
   * @param name String name of the summoner
   * @return Single<SummonerDto>
   */
  fun getSummonerByName(name: String): Single<SummonerDto?> {
    return getJsonResponseString("summoner/v3/summoners/by-name/$name").map { (result) ->
      Klaxon().parse<SummonerDto>(result!!)
    }!!
  }

  /**
   * Gets a summoner by their id
   * @param summonerId Long
   * @return Single<SummonerDto?>
   */
  fun getSummoner(summonerId: Long): Single<SummonerDto?> {
    return getJsonResponseString("summoner/v3/summoners/$summonerId").map { (result) ->
      Klaxon().parse<SummonerDto>(result!!)
    }
  }

  /**
   * Get a summoner by summoner ID.
   * @param accountId Long
   * @return Single<SummonerDto?>
   */
  fun getSummonerByAccount(accountId: Long): Single<SummonerDto?> {
    return getJsonResponseString("summoner/v3/summoners/by-account/$accountId").map { (result) ->
      Klaxon().parse<SummonerDto>(result!!)
    }
  }

  /**
   * Pushes a request to the Riot API with the provided path using reactive principles.
   * @param path String
   * @return Single<Result<String, FuelError>>
   */
  private fun getJsonResponseString(path: String): Single<Result<String, FuelError>> {
    val url = Fuel.get(path)
    logger.atFinest().log("Request URL ${url.url.toExternalForm()}")
    // TODO Figure out why subscribing on IO is not working.
    return Fuel.get(path).rx_responseString().map { pair -> pair.second }
  }
}
