package com.lewisjmorgan.malzahar.riot

import com.github.kittinunf.fuel.core.Response
import io.mockk.every
import io.mockk.mockk
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertFailsWith

/**
 * Created by lewis on 5/12/18.
 */
class RiotApiRateLimiterSpek : Spek({
  describe("rate limiting") {
    val limiter by memoized { RiotApiRateLimiter() }
    on("getting a retry amount from a response") {
      val expectedRateLimitTypes = listOf("application", "method", "service")
      val response = mockk<Response>()
      val expectedRetryAfter = 3L

      for (rateLimitType in expectedRateLimitTypes) {
        every { response.headers } returns mapOf(
            "X-Rate-Limit-Type" to listOf(rateLimitType),
            "Retry-After" to listOf(expectedRetryAfter.toString())
        )
        it("should return seconds of Retry-After as a long when there is a X-Rate-Limit-Type = $rateLimitType") {
          assert(limiter.getRetrySeconds(response) == expectedRetryAfter)
        }
      }
      it("should throw an exception for an X-Rate-Limit-Type that is not an expected rate limit type") {
        assertFailsWith(RiotRateLimitTypeException::class, {
          every { response.headers } returns mapOf("X-Rate-Limit-Type" to listOf("1337HAX"))
          limiter.getRetrySeconds(response)
        })
      }
    }
  }
})
