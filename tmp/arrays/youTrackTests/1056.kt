// Original bug: KT-27586


fun main() {
   println(doSomething().response.getOrThrow())   
}

open class BaseWrapper<T>(val response: T)
class Wrapper(result: Result<Long>, val message: String): BaseWrapper<Result<Long>>(result) 

private fun doSomething(): Wrapper {
   
   val lambda = { Result.success(33L) }
   
   return Wrapper(lambda(), "foo bar")
}
