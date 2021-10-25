// Original bug: KT-29519

public annotation class RestrictsSuspension(
  val value: String = "Restricted suspending functions can only invoke member or extension suspending functions on their restricted coroutine scope"
)

@RestrictsSuspension("Architectural breach, Only DB Actions are allowed in the DB layer") 
object DB {
  suspend fun dbAction(): Unit = TODO()
}

@RestrictsSuspension("Architectural breach, Only UI Actions are allowed in the UI layer") 
object UI {
  suspend fun uiAction(): Unit = 
    DB.dbAction() // This does not compile because `dbAction` is suspended in `DB` not `UI`
}
