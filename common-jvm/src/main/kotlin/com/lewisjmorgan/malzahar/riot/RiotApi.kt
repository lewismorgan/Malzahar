package com.lewisjmorgan.malzahar.riot

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.rx.rx_responseString
import com.github.kittinunf.result.Result
import com.google.common.flogger.FluentLogger
import io.reactivex.Flowable
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class RiotApi(key: String = "") : IRiotApi {
  private val rateLimiter = RiotApiRateLimiter()
  private val responseInterceptor = riotValidatorResponseInterceptor(rateLimiter)

  companion object {
    val logger = FluentLogger.forEnclosingClass()!!
  }

  init {
    FuelManager.instance.basePath = "https://na1.api.riotgames.com/lol"
    FuelManager.instance.baseParams = listOf("api_key" to key)
    FuelManager.instance.removeAllResponseInterceptors()
    FuelManager.instance.addResponseInterceptor { responseInterceptor }
  }

  override fun getJsonResponseString(path: String): Single<String> {
    return getJsonResponseString(path, listOf())
  }

  override fun getJsonResponseString(path: String, params: List<Pair<String, Any>>): Single<String> {
    return request(path, params).map { (_, result) -> result }
  }

  override fun request(path: String, params: List<Pair<String, Any>>): Single<Pair<Response, String>> {
    // TODO Making this public leaks the Fuel dependency when it's not really necessary for end-users
    logger.atInfo().log("Requesting created for: $path with ${params.size} parameters")
    return createRequest(path, params)
        .retryWhen { errors ->
          errors.flatMap { error -> onResponseException(error) }
              .takeUntil { item -> item > 0L }
        }
        .map { (response, result) -> Pair(response, result.get()) }
  }

  private fun createRequest(path: String, params: List<Pair<String, Any>>): Single<Pair<Response, Result<String, FuelError>>> {
    return Fuel.get(path, params).rx_responseString()
  }

  private fun onResponseException(error: Throwable): Flowable<Long> {
    logger.atInfo().log("response exception for ${error.localizedMessage} for $error")
    return if (error is RiotRateLimitException) {
      logger.atInfo().log("Retrying in ${error.retry} seconds")
      Flowable.timer(error.retry, TimeUnit.SECONDS)
    } else Flowable.error(error)
  }

  @Suppress("unused")
  private fun createParamSummary(params: List<Pair<String, String>>): String {
    // TODO Fix Flogger Parameter logging for passed in URL string
    val builder = StringBuilder()
    if (params.isNotEmpty()) {
      for ((key, value) in params) {
        builder.append("Param: $key:$value")
      }
    }
    return builder.toString()
  }
}
