package com.lewisjmorgan.malzahar.riot.lol.match

data class MatchDto(val seasonId: Int, val queueId: Int, val gameId: Long, val participantIdentities: List<ParticipantIdentityDto>,
                    val gameVersion: String, val platformId: String, val gameMode: String, val mapId: Int,
                    val gameType: String, val teams: List<TeamStatsDto>, val participants: List<ParticipantDto>,
                    val gameDuration: Long, val gameCreation: Long)

data class ParticipantIdentityDto(val player: PlayerDto, val participantId: Int)

data class PlayerDto(val currentPlatformId: String, val summonerName: String, val matchHistoryUri: String,
                     val platformId: String, val currentAccountId: Long, val profileIcon: Int,
                     val summonerId: Long, val accountId: Long)

data class TeamStatsDto(val firstDragon: Boolean, val firstInhibitor: Boolean, val bans: List<TeamBansDto>,
                        val baronKills: Int, val firstRiftHerald: Boolean, val firstBaron: Boolean, val riftHeraldKills: Int,
                        val firstBlood: Boolean, val teamId: Int, val firstTower: Boolean, val vilemawKills: Int,
                        val inhibitorKills: Int, val towerKills: Int, val dominionVictoryScore: Int,
                        val win: String, val dragonKills: Int)

data class TeamBansDto(val pickTurn: Int, val championId: Int)

data class ParticipantDto(val stats: ParticipantStatsDto, val participantId: Int, val runes: List<RuneDto>,
                          val timeline: ParticipantTimelineDto, val teamId: Int, val spell2Id: Int,
                          val masteries: List<MasteryDto>, val highestAchievedSeasonTier: String,
                          val spellId: Int, val championId: Int)

data class ParticipantStatsDto(val physicalDamageDealt: Long, val neutralMinionsKilledTeamJungle: Int,
                               val magicDamageDealt: Long, val totalPlayerScore: Int, val deaths: Int,
                               val win: Boolean, val neutralMinionsKilledEnemyTeamJungle: Int,
                               val altarsCaptured: Int, val largestCriticalStrike: Int, val totalDamageDealt: Long,
                               val magicDamageDealtToChampions: Long, val visionWardsBoughtInGame: Int,
                               val damageDealtToObjectives: Long, val largestKillingSpree: Int, val item1: Int,
                               val quadraKills: Int, val teamObjective: Int, val totalTimeCrowdControlDealt: Int,
                               val longestTimeSpentLiving: Int, val wardsKilled: Int, val firstTowerAssist: Boolean,
                               val firstTowerKill: Boolean, val item2: Int, val item3: Int, val item0: Int,
                               val firstBloodAssist: Boolean, val visionScore: Long, val wardsPlaced: Int,
                               val item4: Int, val item5: Int, val item6: Int, val turretKills: Int,
                               val tripleKills: Int, val damageSelfMitigated: Long, val champLevel: Int,
                               val nodeNeutralizeAssist: Int, val firstInhibitorKill: Boolean, val goldEarned: Int,
                               val magicalDamageTaken: Long, val kills: Int, val doubleKills: Int,
                               val nodeCaptureAssist: Int, val trueDamageTaken: Long, val nodeNeutralize: Int,
                               val firstInhibitorAssist: Boolean, val assists: Int, val unrealKills: Int,
                               val neutralMinionsKilled: Int, val objectivePlayerScore: Int, val combatPlayerScore: Int,
                               val damageDealtToTurrets: Long, val altarsNeutralized: Int, val physicalDamageDealtToChampions: Long,
                               val goldSpent: Int, val trueDamageDealt: Long, val trueDamageDealtToChampions: Long,
                               val participantId: Int, val pentaKills: Int, val totalHeal: Long, val totalMinionsKilled: Int,
                               val nodeCapture: Int, val largestMultiKill: Int, val sightWardsBoughtInGame: Int,
                               val totalDamageDealtToChampions: Long, val totalUnitsHealed: Int, val inhibitorKills: Int,
                               val totalScoreRank: Int, val totalDamageTaken: Long, val killingSprees: Int,
                               val timeCCingOthers: Long, val physicalDamageTaken: Long)

data class RuneDto(val runeId: Int, val rank: Int)

data class ParticipantTimelineDto(val lane: String, val participantId: Int, val csDiffPerMinDeltas: Map<String, Double>,
                                  val goldPerMinDeltas: Map<String, Double>, val xpDiffPerMinDeltas: Map<String, Double>,
                                  val creepsPerMinDeltas: Map<String, Double>, val xpPerMinDeltas: Map<String, Double>,
                                  val role: String, val damageTakenDiffPerMinDeltas: Map<String, Double>,
                                  val damageTakenPerMinDeltas: Map<String, Double>)

data class MasteryDto(val masteryId: Int, val rank: Int)

data class MatchlistDto(val matches: List<MatchReferenceDto>, val totalGames: Int, val startIndex: Int,
                        val endIndex: Int)

data class MatchReferenceDto(val lane: String, val gameId: Long, val champion: Int, val platformId: String,
                             val season: Int, val queue: Int, val role: String, val timestamp: Long)

data class MatchTimelineDto(val frames: List<MatchFrameDto>, val frameInterval: Long)

data class MatchFrameDto(val timestamp: Long, val participantFrames: Map<Int, MatchParticipantFrameDto>,
                         val events: List<MatchEventDto>)

data class MatchParticipantFrameDto(val totalGold: Int, val teamScore: Int, val participantId: Int,
                                    val level: Int, val currentGold: Int, val minionsKilled: Int,
                                    val dominionScore: Int, val position: MatchPositionDto, val xp: Int,
                                    val jungleMinionsKilled: Int)

data class MatchPositionDto(val x: Int, val y: Int)

data class MatchEventDto(val eventType: String, val towerType: String, val teamId: Int,
                         val ascendedType: String, val killerId: Int, val levelUpType: String,
                         val pointCaptured: String, val assistingParticipantIds: List<Int>,
                         val wardType: String, val monsterType: String, val type: String,
                         val skillSlot: Int, val victimId: Int, val timestamp: Long, val afterId: Int,
                         val monsterSubType: String, val laneType: String, val itemId: Int,
                         val participantId: Int, val buildingType: String, val creatorId: Int,
                         val position: MatchPositionDto, val beforeId: Int)
