package com.lewisjmorgan.malzahar.riot

import com.lewisjmorgan.malzahar.riot.test.fromTestApi
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class RiotApiSpeck : Spek({
  describe("Riot Summoner API") {
    // TODO Tests for jsonResponseString using mocks
    val api by memoized { RiotApi.fromTestApi() }

    on("getting a json response string") {
      it("should return a RiotApiException result for empty request") {
        api.getJsonResponseString("")
            .test()
            .assertError(RiotApiException::class.java)
            .assertNotComplete()
      }
      it("should return a message body for a 200 request") {
        api.getJsonResponseString("/static-data/v3/tarball-links")
            .test()
            .assertComplete()
            .assertValue { result -> !result.component1().isNullOrEmpty() }
      }
    }
  }
})
