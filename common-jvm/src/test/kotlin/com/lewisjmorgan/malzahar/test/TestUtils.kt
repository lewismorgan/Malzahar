package com.lewisjmorgan.malzahar.test

import com.lewisjmorgan.malzahar.Riot
import java.util.*

/**
 * Creates a new Riot instance using the key in key.properties
 */
fun Riot.Companion.fromTestApi(): Riot {
  val properties = Properties()
  properties.load(Riot::class.java.getResourceAsStream("key.properties"))
  val key = properties.getProperty("key")!!
  return Riot(key)
}