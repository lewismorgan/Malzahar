package com.lewisjmorgan.malzahar.riot.champions

data class ChampionDto(val id: Long, val freeToPlay: Boolean, val active: Boolean,
                       val botMmEnabled: Boolean, val botEnabled: Boolean, val rankedPlayEnabled: Boolean)
data class ChampionListDto(val champions: List<ChampionDto>)
