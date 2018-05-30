package com.lewisjmorgan.malzahar.riot.champion.mastery

data class ChampionMasteryDto(val chestGranted: Boolean, val championLevel: Int, val championPoints: Int,
                              val championId: Long, val playerId: Long, val championPointsUntilNextLevel: Long,
                              val tokensEarned: Int, val championPointsSinceLastLevel: Long, val lastPlayTime: Long)
