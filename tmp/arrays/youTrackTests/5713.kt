// Original bug: KT-32755

class A {
    fun doSomething(): String = "from A"
}

class B {
    fun doSomethingElse(): String = "from B"
}

enum class Sample {
    A, B
}

fun getTypeInferenceError(s: Sample): () -> String {
    return when (s) {
        Sample.A -> ({ A().doSomething() })
        Sample.B -> B()::doSomethingElse
    }
}



fun main() {
    println(getTypeInferenceError(Sample.A))
    println(getTypeInferenceError(Sample.B))
}
