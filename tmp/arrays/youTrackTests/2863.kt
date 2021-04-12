// Original bug: KT-39633

interface Proxy<in T>

class A<T : Any>(val left: T) : Proxy<T>

abstract class Api {
  abstract fun <T> magic(): T
  inline fun <reified T : Any> match(proxy: Proxy<T>): T = magic()

  inline fun <reified T : Any> f(x: T): T = g(x)

  inline fun <reified T : Any> g(x: T) = match(A(x))
}
