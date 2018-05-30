package com.lewisjmorgan.malzahar.riot.lol.match

import com.beust.klaxon.Klaxon
import com.lewisjmorgan.malzahar.riot.RiotApi
import io.reactivex.Single

private const val BASE_PATH = "match/v3"

fun RiotApi.getMatchByMatchId(matchId: Long): Single<MatchDto> {
  return getJsonResponseString("$BASE_PATH/matches/$matchId").map { result ->
    Klaxon().parse<MatchDto>(result)
  }
}

fun RiotApi.getMatchlistsByAccountId(accountId: Long): Single<MatchlistDto> {
  return getJsonResponseString("$BASE_PATH/matchlists/by-account/$accountId").map { result ->
    Klaxon().parse<MatchlistDto>(result)
  }
}

fun RiotApi.getMatchTimelineByMatch(matchId: Long): Single<MatchTimelineDto> {
  return getJsonResponseString("$BASE_PATH/timelines/by-match/$matchId").map { result ->
    Klaxon().parse<MatchTimelineDto>(result)
  }
}
