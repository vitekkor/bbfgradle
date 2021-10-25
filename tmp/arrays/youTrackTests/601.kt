// Original bug: KT-41966

class A {
    object name;
    object Name
    object NAME
}

fun main() {
    println(runCatching { A.Name }.getOrNull())
    println(runCatching { A.name }.getOrNull())
    println(runCatching { A.NAME }.getOrNull())
}
