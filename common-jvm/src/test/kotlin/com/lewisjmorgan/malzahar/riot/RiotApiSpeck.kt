package com.lewisjmorgan.malzahar.riot

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import com.lewisjmorgan.malzahar.riot.test.fromTestApi
import com.lewisjmorgan.malzahar.riot.test.toURL
import io.mockk.every
import io.mockk.spyk
import io.reactivex.Single
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

// TODO Mock all the return methods so there is no actual calls to the Riot API
class RiotApiSpeck : Spek({
  describe("Riot API") {
    val api by memoized {
      // TODO Create from a mock
      RiotApi.fromTestApi()
    }
    describe("creating a request") {
      on("a valid path") {
        val path = "/static-data/v3/tarball-links"
        it("should return an OK response") {
          api.request(path, listOf())
              .test()
              .assertComplete()
              .assertValue { (response, _) -> response.responseMessage == "OK" }
        }
        it("should return a message body for a 200 request") {
          api.request(path, listOf())
              .test()
              .assertComplete()
              .assertValue { (_, message) -> !message.isEmpty() }
        }
      }
      on("an invalid request path") {
        it("should throw a RiotApiException result for an empty request") {
          api.request("", listOf())
              .test()
              .assertError(RiotApiException::class.java)
              .assertNotComplete()
        }
      }
      on("a request that threw a RiotRateLimitException") {
        val mock = spyk<RiotApi>()
        val headers = mapOf(
            "X-Rate-Limit-Type" to listOf("application"),
            "Retry-After" to listOf(3L.toString()))
        every {
          mock invoke "createRequest" withArguments listOf("", listOf<Pair<String, String>>())
        } returns createFakeSuccessResponse(headers)

        it("retries the request") {
          mock.request("", listOf())
              .test()
              .assertComplete()
        }
      }
    }
    on("getting a json response string") {
      it("should return a response message") {
        api.getJsonResponseString("/static-data/v3/tarball-links")
            .test()
            .assertComplete()
            .assertValue { message -> !message.isEmpty() }
      }
    }
  }
})

internal fun createFakeResponse(headers: Map<String, List<String>>): Response {
  return Response("http://.".toURL(), headers = headers)
}

internal fun createFakeSuccessResponse(headers: Map<String, List<String>>): Single<Pair<Response, Result<String, FuelError>>> {
  return Single.just(Pair(createFakeResponse(headers), Result.Success("")))
}