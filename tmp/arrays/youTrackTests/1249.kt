// Original bug: KT-32185

package test

import java.time.*

class Test : StupidTimestampSource {
   private var earlierOffset = defaultEarlierOffset
   private var laterOffset = defaultLaterOffset
   override fun earlier(): Timestamp {
      return Timestamp(now().instant + earlierOffset)
   }

   override fun later(): Timestamp {
      return Timestamp(now().instant + laterOffset)
   }

   override fun now(): Timestamp {
      return Timestamp(Instant.now())
   }

   val weAreDoomed
      get() = when (Instant.now().atZone(ZoneOffset.UTC).year) {
         2012 -> "yes"
         2019 -> "no"
         else -> "maybe"
      }

   companion object {
      val defaultEarlierOffset = Duration.ofDays(-1)!!
      val defaultLaterOffset = Duration.ofDays(1)!!
   }

   data class Timestamp(
      val instant: Instant
   ) : StupidTimestamp {

      override val milliseconds get() = instant.toEpochMilli()
   }
}

interface StupidTimestamp {
   val milliseconds: Long
}

interface StupidTimestampSource {
   fun earlier(): StupidTimestamp
   fun later(): StupidTimestamp
   fun now(): StupidTimestamp
}
