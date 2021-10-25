// Original bug: KT-41042

data class Person(
  val firstName: String,
  val lastName: String,
  val age: String,
  val address: String,
  val phoneNumbers: List<String>,
  val friends: Map<String, String>
)
