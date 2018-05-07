package com.lewisjmorgan.malzahar.riot

import com.beust.klaxon.Klaxon
import com.google.common.flogger.FluentLogger
import com.lewisjmorgan.malzahar.riot.league.dto.SummonerDto
import io.reactivex.Single
import khttp.get
import khttp.responses.Response

/**
 * Created by lewis on 4/21/18.
 */
class Riot(private val key: String) {
  // TODO Rate Limiting https://developer.riotgames.com/rate-limiting.html
  // All url's built should handle the response rate limits and then parse the JSONObject

  companion object {
    val logger = FluentLogger.forEnclosingClass()!!
    val klaxon = Klaxon()
  }

  /**
   * Get a summoner by summoner name.
   *
   * https://developer.riotgames.com/api-methods/#summoner-v3/GET_getBySummonerName
   * @param name String
   * @return Single<SummonerDto> stream of a single SummonerDto.
   */
  fun getSummonerByName(name: String): Single<SummonerDto> {
    return Single.create { emitter ->
      val response = buildUrl("summoner/v3/summoners/by-name/$name")
      // https://developer.riotgames.com/response-codes.html
      // Only 200 response codes are guaranteed to return a response body as JSON.
      when (response.statusCode) {
        200 -> {
          emitter.onSuccess(klaxon.parse<SummonerDto>(response.text)!!)
        }
        else -> {
          logger.atWarning().log("Response code was ${response.statusCode}")
          // TODO Emit a proper Riot API Exception
          emitter.tryOnError(Exception(response.text))
        }
      }
    }
  }

  private fun buildUrl(pathway: String): Response = get("https://na1.api.riotgames.com/lol/$pathway?api_key=$key")
}
