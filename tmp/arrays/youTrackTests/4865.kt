// Original bug: KT-23301

class X // cannot change this

@Builder
class Y
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@DslMarker
annotation class Builder

fun (@Builder X).build(body: Y.() -> Unit) {}
fun X.f() {
    build {
        build {} // should be error. It will be error, if I mark X as @Builder
    }
}

