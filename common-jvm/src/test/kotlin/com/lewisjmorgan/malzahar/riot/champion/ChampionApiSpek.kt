package com.lewisjmorgan.malzahar.riot.champion

import com.lewisjmorgan.malzahar.riot.RiotApi
import com.lewisjmorgan.malzahar.riot.test.fromTestApi
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

/**
 * Created by lewis on 5/12/18.
 */
class ChampionApiSpek : Spek({
  describe("Champions v3 API") {
    val api by memoized { RiotApi.fromTestApi() }
    on("getting a list of champions") {
      val champions = api.getChampions(true)
      it("should return a list of free to play champions") {
        champions.test()
            .assertComplete()
            .assertValue { list -> list.size > 1 }
      }
    }
    on("getting a champion by id") {
      val expectedId = 238L
      val obsChampion = api.getChampion(expectedId)
      it("should return a champion with id $expectedId") {
        obsChampion.test()
            .assertComplete()
            .assertValue { (id) -> id == expectedId }
      }
    }
  }
})