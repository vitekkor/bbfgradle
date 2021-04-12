// Original bug: KT-17431

package weirdbug

fun main(args: Array<String>) {
  println("Start")
  doSth(A(Id.ID_B))
}

enum class Id {
  ID_A,
  ID_B
}

sealed class Base(val id: Id)
class A(id: Id) : Base(id)
class B(id: Id) : Base(id)



// ### FAILING VERSION ###
fun doSth(base: Base): Base = when (base) {
  is A -> process(base, f = ::doSomethingInCaseOfA)
  is B -> process(base, f = ::doSomethingInCaseOfB)
}

// ### WORKING VERSION ###
//private fun doSth(base: Base): Base = when (base) {
//  is A -> indirectProcess(base)
//  is B -> indirectProcess(base)
//}
//
//fun indirectProcess(a: A): A = process(a, f = ::doSomethingInCaseOfA)
//
//fun indirectProcess(b: B): B = process(b, f = ::doSomethingInCaseOfB)



fun doSomethingInCaseOfA(a: A) {
  // Normally I would do something useful with a
}

fun doSomethingInCaseOfB(b: B) {
  // Normally I would do something useful with b
}

inline fun <reified T : Base> process(t: T, f: (T) -> Unit): T {
  f(t)
  return getSomeBaseObject(t.id) as? T ?: throw CustomException()
}

fun getSomeBaseObject(id: Id): Base = if (id == Id.ID_A) A(id) else B(id)

class CustomException : RuntimeException()
