package com.lewisjmorgan.malzahar.riot.match

import com.lewisjmorgan.malzahar.riot.RiotApi
import com.lewisjmorgan.malzahar.riot.test.fromTestApi
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

/**
 * Created by lewis on 5/19/18.
 */
class MatchApiSpek : Spek({
  describe("Match API v3") {
    val api by memoized { RiotApi.fromTestApi() }
    on("getting a match by match id") {
      val matchId = 0L
      val match = api.getMatchByMatchId(matchId)
      it("returns the match for the specified match id") {
        match.test()
            .assertComplete()
      }
    }
    on("getting matchlists by account id") {
      val accountId = 35140718L
      val matchlists = api.getMatchlistsByAccountId(accountId)
      it("returns the matchlists for the specified account id") {
        matchlists.test()
            .assertComplete()
      }
    }
  }
})