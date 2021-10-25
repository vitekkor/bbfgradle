// Original bug: KT-44634

data class Driver(
  val teamId: String,
)

data class Team(
  val id: String,
  val drivers: List<Driver>,
)

object Drivers {
  val Lewis: Driver = Driver(teamId = Teams.Mercedes.id)
}

object Teams {
  val Mercedes: Team = Team(id = "merc", drivers = listOf(Drivers.Lewis))
}

fun main() {
  println(Drivers.Lewis) // Driver(teamId = "merc")
  println(Teams.Mercedes) // Team(id = "merc", drivers = [null])
}
