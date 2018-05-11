package com.lewisjmorgan.malzahar.riot

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.rx.rx_responseString
import com.github.kittinunf.result.Result
import com.google.common.flogger.FluentLogger
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
   * Pushes a request to the Riot API with the provided path using reactive principles.
   * @param path String
   * @return Single<Result<String, FuelError>>
   */
  internal fun getJsonResponseString(path: String): Single<Result<String, FuelError>> {
    // TODO Refactor method to call a lower level "request" which allows accessing the request, response
    val url = Fuel.get(path)
    logger.atFinest().log("Request URL ${url.url.toExternalForm()}")
    // TODO Figure out why subscribing on IO is not working.
    return Fuel.get(path).rx_responseString().map { pair -> pair.second }
  }

}
