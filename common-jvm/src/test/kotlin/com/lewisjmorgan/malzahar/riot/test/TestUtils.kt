package com.lewisjmorgan.malzahar.riot.test

import com.lewisjmorgan.malzahar.riot.Riot
import java.util.*

/**
 * Creates a new Riot instance using the key in key.properties
 */
fun Riot.Companion.fromTestApi(): Riot {
  val properties = Properties()
  properties.load(Riot::class.java.getResourceAsStream("/key.properties"))
  val key = properties.getProperty("key")!!
  return Riot(key)
}