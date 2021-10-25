// Original bug: KT-34706

abstract class Weapon(
	val name: String,
	val category: String
)

// IDEA cannot fold here two parameters inside brackets yet.
object DarkSword : Weapon(
	name = "Dark Sword 22",
	category = "Straight Sword"
)

// The same.
class DarkSwordX(season: Int) : Weapon(
	name = "Dark Sword $season",
	category = "Straight Sword"
)

// The same.
class DarkSwordXX(
	season:Int
) : Weapon(
	name = "Dark Sword $season",
	category = "Straight Sword"
)

//It should be folded like this:
//object DarkSword : Weapon(...)
