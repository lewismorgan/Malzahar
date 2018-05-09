package com.lewisjmorgan.malzahar.riot.test

import com.lewisjmorgan.malzahar.riot.RiotApi
import java.util.*

// TODO Load API Key from a text file or arguments instead of properties

/**
 * Creates a new Riot instance using the key in key.properties
 */
fun RiotApi.Companion.fromTestApi(): RiotApi {
  val properties = Properties()
  properties.load(RiotApi::class.java.getResourceAsStream("/key.properties"))
  val key = properties.getProperty("key")!!
  return RiotApi(key)
}