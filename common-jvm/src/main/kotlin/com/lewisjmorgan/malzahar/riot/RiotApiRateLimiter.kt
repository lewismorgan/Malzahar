package com.lewisjmorgan.malzahar.riot

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response

// TODO Application Rate Limits
// TODO Method Rate Limits
// TODO Service Rate Limits

class RiotApiRateLimiter {
  /**
   * Retries a request based on the headers in the response.
   * @param request Request
   * @param response Response
   * @return Response
   */
  fun retryRequestLater(request: Request, response: Response): Response {
    TODO("not implemented")
  }
}
