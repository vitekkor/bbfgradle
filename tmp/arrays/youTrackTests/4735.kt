// Original bug: KT-6069

fun main(args : Array<String>) {
    try {
    }
    catch (e: Throwable) {
        println("1")
    }
    catch (e: Throwable) {
        println("2") // missed dead code
    }
    catch (e: Exception) {
        println("3") // missed dead code
    }
}
