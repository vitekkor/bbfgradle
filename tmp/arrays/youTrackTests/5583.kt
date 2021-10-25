// Original bug: KT-32452

interface IService {
    suspend fun encode(input: String): String
}

open class KAgent<T : Any> {

    fun <PAR1, RET : Any, T> call(
        function: suspend T.(PAR1) -> RET, p1: PAR1
    ): RET {
        throw Exception()
    }

    fun <PAR1, RET : Any, T> call(
        function: suspend T.(PAR1) -> List<RET>, p1: PAR1
    ): List<RET> {
        return listOf()
    }

}

class MyService : IService, KAgent<IService>() {
    override suspend fun encode(input: String) =
        call(IService::encode, input)                                 // Compile error here
}
