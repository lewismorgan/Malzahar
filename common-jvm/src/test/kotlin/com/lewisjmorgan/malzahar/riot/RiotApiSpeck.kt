package com.lewisjmorgan.malzahar.riot

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import com.lewisjmorgan.malzahar.riot.test.toURL
import io.mockk.every
import io.mockk.spyk
import io.reactivex.Single
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.api.lifecycle.CachingMode

class RiotApiSpeck : Spek({
  describe("Riot API") {
    val api by memoized(CachingMode.GROUP) { spyk<RiotApi>() }
    val path = ""
    describe("creating a request") {
      on("a successful request response") {
        val expectedMessageBody = "The test worked!"
        mockCreateRequest(api, mapOf(), 200, "OK", expectedMessageBody)
        it("should have a response message") {
          api.request(path, listOf())
              .test()
              .assertComplete()
              .assertValue { (response, _) -> response.responseMessage == "OK" }
        }
        it("should return a message body") {
          api.request(path, listOf())
              .test()
              .assertComplete()
              .assertValue { (_, message) -> message == expectedMessageBody }
        }
      }
      // TODO Tests for thrown RiotRateLimitException using mocks.
      // Request method handles this through retryWhen for the error and then creating a timer for x seconds
    }
    describe("getting a response string") {
      on("a proper request") {
        val expectedResult = "This is a response string"
        mockCreateRequest(api, mapOf(), 200, "OK", expectedResult)
        it("should return a mapped response result") {
          api.getJsonResponseString("", listOf())
              .test()
              .assertValue { it == expectedResult }
        }
      }
    }
  }
})

internal fun mockCreateRequest(api: RiotApi, headers: Map<String, List<String>>, statusCode: Int,
                               responseMessage: String, resultValue: String) {
  val response = createResponse(headers, statusCode, responseMessage)
  every {
    api invoke "createRequest" withArguments listOf("", listOf<Pair<String, String>>())
  } returns Single.just(Pair(response, Result.Success<String, FuelError>(resultValue)))
}

internal fun createResponse(headers: Map<String, List<String>>, statusCode: Int, responseMessage: String) =
    Response("http://.".toURL(), headers = headers, statusCode = statusCode, responseMessage = responseMessage)
