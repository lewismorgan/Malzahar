package com.lewisjmorgan.malzahar.riot

import com.google.common.flogger.FluentLogger
import io.reactivex.Single
import khttp.extensions.get
import khttp.responses.Response

/**
 * Created by lewis on 4/21/18.
 */
class Riot(private val key: String) {
  // Allows extension methods
  companion object {
    val logger = FluentLogger.forEnclosingClass()!!
  }

  fun getSummonerByName(name: String): String {
    buildUrl("summoner/v3/summoners/by-name/$name")
        .doOnError {
          logger.atSevere().log("Error trying to get summoner $name", it)
        }
        .doFinally {
          logger.atInfo().log("Got summoner $name")
        }
        .subscribe { response: Response?, error: Throwable? ->
          if (error != null) {
            // TODO: Handle Error here
            return@subscribe
          }

          when (response?.statusCode) {
            200 -> System.out.print("Status OK")
            else -> System.out.print("Status not OK.")
          }
        }
    return "Reats"
  }

  private fun buildUrl(pathway: String): Single<Response> = get("https://na1.api.riotgames.com/lol/$pathway?api_key=$key")
}
