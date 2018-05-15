package com.lewisjmorgan.malzahar.riot

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response

// TODO Test this method individually
/**
 * Ensures that only status code of 200 is received. Otherwise, an appropriate exception is thrown.
 *
 * @param rateLimiter RiotApiRateLimiter
 * @return (Request, Response) -> Response
 */
internal fun riotValidatorResponseInterceptor(rateLimiter: RiotApiRateLimiter): (Request, Response) -> Response = { request, response ->
  // A response of 200 is the only one that should be considered a valid response
  // https://developer.riotgames.com/response-codes.html
  if (!response.headers.containsKey("Content-Type") || !response.headers["Content-Type"]!!.contains("application/json;charset=utf-8")) {
    throw RiotApiException(-1, "Response did not specify content type or was not JSON-UTF8")
  }

  when (response.statusCode) {
    200 -> response
    429 -> throw RiotRateLimitException(rateLimiter.getRetrySeconds(response))
    else -> throw RiotApiException(response.statusCode, response.responseMessage)
  }
}
