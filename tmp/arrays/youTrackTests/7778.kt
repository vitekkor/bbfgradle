// Original bug: KT-25587

@DslMarker
annotation class Ann

@Ann
class Project {
    fun aaaaa1() = 1
}

@Ann
class BuildType {
    fun aaaaa2() = 2
}

fun foo(x: Project.() -> Unit) {}
fun bar(x: BuildType.() -> Unit) {}

fun test() {
    foo {
        bar {
            aaaaa2()
        }
    }
}
