// Original bug: KT-29172

// Supporting Code
typealias TestCoerce<Data> = (Any) -> Data?
typealias TestSelect<Data> = (Map<String, Any>) -> Data?

class TestSelectClass<Data>(val typeChecker: TestCoerce<Data>) {
    fun select(source: Map<String, Any>): Data? {
        TODO()
    }
}
