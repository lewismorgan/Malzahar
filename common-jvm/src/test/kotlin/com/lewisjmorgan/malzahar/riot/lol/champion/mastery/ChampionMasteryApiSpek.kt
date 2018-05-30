package com.lewisjmorgan.malzahar.riot.lol.champion.mastery

import com.lewisjmorgan.malzahar.riot.RiotApi
import com.lewisjmorgan.malzahar.riot.champion.mastery.getMasteriesBySummoner
import com.lewisjmorgan.malzahar.riot.champion.mastery.getMasteryForChampion
import com.lewisjmorgan.malzahar.riot.champion.mastery.getTotalMasteryScore
import com.lewisjmorgan.malzahar.riot.test.fromTestApi
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

/**
 * Created by lewis on 5/18/18.
 */
class ChampionMasteryApiSpek : Spek({
  describe("Champion Mastery v3 API") {
    val api by memoized { RiotApi.fromTestApi() }
    val summonerId by memoized { 21661091L }

    on("getting champion masteries by summoner id $summonerId") {
      val result = api.getMasteriesBySummoner(summonerId)
      it("returns a list of ChampionMasteryDto for the summoner with the id $summonerId") {
        result.test()
            .assertComplete()
            .assertValue { !it.isEmpty() }
            .assertValue { it.map { mastery -> mastery.playerId }.filter { it == summonerId }.size == it.size }
      }
    }
    on("getting a mastery for a champion") {
      val expectedChampionId = 9L
      val result = api.getMasteryForChampion(summonerId, 9L)
      it("returns the mastery for the specified champion") {
        result.test()
            .assertComplete()
            .assertValue { it.championId == expectedChampionId }
            .assertValue { it.playerId == summonerId }
      }
    }
    on("getting total mastery score") {
      val result = api.getTotalMasteryScore(summonerId)
      it("returns the total mastery score") {
        result.test()
            .assertComplete()
      }
    }
  }
})