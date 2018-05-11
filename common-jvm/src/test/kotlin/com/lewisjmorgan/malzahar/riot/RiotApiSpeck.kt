package com.lewisjmorgan.malzahar.riot

import com.lewisjmorgan.malzahar.riot.summoner.getSummoner
import com.lewisjmorgan.malzahar.riot.summoner.getSummonerByAccount
import com.lewisjmorgan.malzahar.riot.summoner.getSummonerByName
import com.lewisjmorgan.malzahar.riot.test.fromTestApi
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on


class RiotApiSpeck : Spek({
  describe("Riot Summoner API") {
    val riotApi by memoized { RiotApi.fromTestApi() }
    on("getting a summoner by name") {
      val summoners = listOf("Reats", "A Fiddley Foe")
      summoners.forEach {
        it("returns summoner with name $it") {
          riotApi.getSummonerByName(it).test()
              .assertValue { summoner -> summoner.name == it }
        }
      }
    }
    on("getting a summoner by id") {
      val id = 21661091L
      it("returns a summoner with id $id") {
        riotApi.getSummoner(id).test()
            .assertValue { summoner -> summoner.id == id }
      }
    }
    on("getting a summoner by account") {
      val accountId = 35140718L
      it("returns a summoner with account id $accountId") {
        riotApi.getSummonerByAccount(accountId).test()
            .assertValue { summoner -> summoner.accountId == accountId }
      }
    }
  }
})
