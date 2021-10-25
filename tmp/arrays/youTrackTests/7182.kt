// Original bug: KT-26511

import java.util.*

typealias UserId = Int

object Main {
  class Session(val sessionDate: Date = Date(), val sessionData: String? = null)

  val userSessionData = mapOf<UserId, List<Session>>(
      1 to listOf(Session(Date(0)), Session(Date(20))),
      2 to listOf(),
      3 to listOf(Session(Date(20)), Session(Date(10)), Session(Date(60)), Session(Date(40)))
  )

  fun logLatestSessionDate() {
    userSessionData.forEach { (userId, sessions) ->
      val latestSession = sessions.maxBy { it.sessionDate } ?: return // Ignore user when we don't have any sessions yet
      println("User$userId has the last session at Date(${latestSession.sessionDate.time})")
    }
  }

  fun logLatestSessionDateCorrect() {
    userSessionData.forEach { (userId, sessions) ->
      val latestSession = sessions.maxBy { it.sessionDate } ?: return@forEach // Ignore user when we don't have any sessions yet
      println("User$userId has the last session at Date(${latestSession.sessionDate.time})")
    }
  }

  @JvmStatic fun main(args: Array<String>) {
    logLatestSessionDate() // Prints only User1
    println("---")
    logLatestSessionDateCorrect() // Correctly prints User1 AND User3
  }
}

