package com.lewisjmorgan.malzahar.riot.lol.summoner

import com.lewisjmorgan.malzahar.riot.RiotApi
import com.lewisjmorgan.malzahar.riot.test.fromTestApi
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

/**
 * Created by lewis on 5/10/18.
 */
class SummonerApiSpeck : Spek({
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
})