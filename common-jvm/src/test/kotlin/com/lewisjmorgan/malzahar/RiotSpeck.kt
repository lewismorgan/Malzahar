package com.lewisjmorgan.malzahar

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
    val riot by memoized { Riot("RGAPI-6451c260-0fac-4434-af49-47febb7b1ae3") }
    on("getting a summoner by name") {
      val summoner = riot.getSummonerByName("Reats")
      it("returns a summoner") {
        assertEquals("Reats", summoner)
      }
    }
  }
})