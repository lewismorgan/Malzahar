package com.lewisjmorgan.malzahar

import khttp.get

/**
 * Created by lewis on 4/21/18.
 */
class Riot(private val key: String) {
  // Allows extension methods
  companion object

  fun getSummonerByName(name: String): String {
    val r = buildUrl("summoner/v3/summoners/by-name/$name")
    println(r.statusCode)
    println(r.text)

    return "Reats"
  }

  private fun buildUrl(pathway: String) = get("https://na1.api.riotgames.com/lol/$pathway?api_key=$key")
}
