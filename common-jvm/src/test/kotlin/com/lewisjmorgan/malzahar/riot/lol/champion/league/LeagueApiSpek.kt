package com.lewisjmorgan.malzahar.riot.lol.champion.league

import com.lewisjmorgan.malzahar.riot.RiotApi
import com.lewisjmorgan.malzahar.riot.test.fromTestApi
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

/**
 * Created by lewis on 5/18/18.
 */
class LeagueApiSpek : Spek({
  describe("League v3 API") {
    val api by memoized { RiotApi.fromTestApi() }
    val queueType = "RANKED_SOLO_5x5"
    on("getting challenger leagues by queue") {
      it("returns the challenger leagues for $queueType") {
        api.getChallengerLeagueByQueue(queueType)
            .test()
            .assertComplete()
            .assertValue { it.queue == queueType }
      }
    }
    on("getting master league by queue") {
      it("returns the master league for $queueType") {
        api.getMasterLeagueByQueue(queueType)
            .test()
            .assertComplete()
            .assertValue { it.queue == queueType }
      }
    }
    on("getting a league by id") {
      val leagueId = "6e77e2d0-03d8-11e8-9e8c-c81f66cf135e"
      it("returns the league for the id $leagueId") {
        api.getLeagueById(leagueId)
            .test()
            .assertComplete()
            .assertValue { it.leagueId == leagueId }
      }
    }
    on("getting league positions by summoner") {
      val summonerId = 21661091L
      it("returns the league positions for summoner with id $summonerId") {
        api.getLeaguePositionsBySummoner(summonerId)
            .test()
            .assertComplete()
            .assertValue { it.isNotEmpty() }
      }
    }
  }
})