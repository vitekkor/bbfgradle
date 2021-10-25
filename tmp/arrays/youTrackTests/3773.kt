// Original bug: KT-37128

inline fun <reified T: Any> T.compareUsing(other: Any?, crossinline compare: T.(T) -> Boolean): Boolean {
  if (this === other) return true
  return other is T && compare(other)
}

interface Animal<FOOD> where FOOD: Comparable<FOOD> {
  val food: FOOD
}

abstract class BaseAnimal<FOOD>(override val food: FOOD): Animal<FOOD> where FOOD: Comparable<FOOD> {
  override fun equals(other: Any?) = compareUsing(other) { food == it.food }
}

