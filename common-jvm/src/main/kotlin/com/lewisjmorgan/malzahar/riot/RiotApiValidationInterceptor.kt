package com.lewisjmorgan.malzahar.riot

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response

/**
 * Ensures that only status code of 200 is received. Otherwise, an exception is thrown.
 * @return (Request, Response) -> Response
 */
internal fun riotValidatorResponseInterceptor(): (Request, Response) -> Response = { _, response ->
  // A response of 200 is the only one that should be considered a valid response
  // https://developer.riotgames.com/response-codes.html
  when (response.statusCode) {
    200 -> response
    429 -> response // TODO Rate Limiting
    else -> throw RiotApiException(response.statusCode, response.responseMessage)
  }
}
