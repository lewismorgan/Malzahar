package com.lewisjmorgan.malzahar.riot.summoner

/**
 * /lol/summoner/v3/summoners/by-account/{accountId}
 * /lol/summoner/v3/summoners/by-name/{summonerName}
 * /lol/summoner/v3/summoners/{summonerId}
 * Created by lewis on 5/1/18.
 */
class SummonerDto(val profileIconId: Int, val name: String, val summonerLevel: Long, val revisionDate: Long,
                  val id: Long, val accountId: Long)
