package com.lewisjmorgan.malzahar.riot

import com.github.kittinunf.fuel.core.Response
import com.google.common.flogger.FluentLogger

class RiotApiRateLimiter {
  companion object {
    val logger = FluentLogger.forEnclosingClass()!!
  }

  fun getRetrySeconds(response: Response): Long {
    // Included in any 429 response
    val limitType = response.headers["X-Rate-Limit-Type"]!!
    when (limitType[0]) {
      "application", "method", "service" -> {
        logger.atFine().log("Getting rate limit seconds from $response")
        return response.headers["Retry-After"]!![0].toLong()
      }
      else -> throw RiotRateLimitTypeException(limitType[0])
    }
    // TODO X-Rate-Limit-Type and Retry-After may not be given for underlying service enforcement
  }
}
