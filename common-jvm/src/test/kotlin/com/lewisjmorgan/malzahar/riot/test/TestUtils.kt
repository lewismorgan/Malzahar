package com.lewisjmorgan.malzahar.riot.test

import com.lewisjmorgan.malzahar.riot.RiotApi
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Creates a new Riot instance using the key in key.txt
 */
fun RiotApi.Companion.fromTestApi(): RiotApi {
  val path = Paths.get("key.txt")
  if (Files.exists(path)) {
    val lines = Files.readAllLines(path)
    return RiotApi(lines[0])
  } else {
    Files.createFile(path)
    logger.atInfo().log("Created key file at ${path.toAbsolutePath()}")
    throw Exception("No API Key set in key.txt. Please add your key to the file.")
  }
}