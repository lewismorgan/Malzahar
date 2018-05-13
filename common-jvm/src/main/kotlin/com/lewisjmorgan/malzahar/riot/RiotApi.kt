package com.lewisjmorgan.malzahar.riot

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.rx.rx_responseString
import com.google.common.flogger.FluentLogger
import io.reactivex.Single

class RiotApi(key: String) {
  companion object {
    val logger = FluentLogger.forEnclosingClass()!!
  }

  init {
    FuelManager.instance.basePath = "https://na1.api.riotgames.com/lol"
    FuelManager.instance.baseParams = listOf("api_key" to key)
    FuelManager.instance.removeAllResponseInterceptors()
    FuelManager.instance.addResponseInterceptor { riotValidatorResponseInterceptor(RiotApiRateLimiter()) }
  }

  /**
   * Creates a request to the Riot API using the specified path and maps the response message to a
   * string for manipulation.
   * @param path String
   * @return Single<Result<String, FuelError>>
   */
  fun getJsonResponseString(path: String): Single<String> {
    return getJsonResponseString(path, listOf())
  }

  /**
   * Creates a request to the Riot API using the specified path and parameters. The response is then mapped
   * to a string for manipulation.
   * @param path String
   * @param params List<Pair<String, String>>
   * @return Single<String>
   */
  fun getJsonResponseString(path: String, params: List<Pair<String, String>>): Single<String> {
    return request(path, params).map { (_, result) -> result }
  }

  /**
   * Creates a request to the API with the given path and parameters. Note that requests are created
   * without a specified thread subscription. It's recommended to subscribe onto the IO.
   * @param path String
   * @param params List<Pair<String, String>>
   * @return Single<Pair<Response, String>>
   */
  fun request(path: String, params: List<Pair<String, String>>): Single<Pair<Response, String>> {
    // TODO Making this public leaks the Fuel dependency when it's not really necessary for end-users
    logger.atFinest().log("Requesting created for: $path with ${params.size} parameters")
    for ((key, value) in params) {
      logger.atFinest().log("Param: $key:$value")
    }
    return Fuel.get(path, params).rx_responseString().map { (response, result) -> Pair(response, result.get()) }
  }
}
