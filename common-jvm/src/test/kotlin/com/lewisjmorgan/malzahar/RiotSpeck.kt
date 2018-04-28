package com.lewisjmorgan.malzahar

import com.lewisjmorgan.malzahar.test.fromTestApi
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

/**
 * Created by lewis on 4/21/18.
 */
class RiotSpeck : Spek({
  describe("interacting with Riot's core API") {
    val riot by memoized { Riot.fromTestApi() }
    on("getting a summoner by name") {
      val summoners = listOf("Reats", "A Fiddley Foe")
      summoners.forEach {
        it("returns a summoner named $it") {
          val summoner = riot.getSummonerByName(it)
          assertEquals(it, summoner)
        }
      }
    }
  }
})