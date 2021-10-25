// Original bug: KT-40824

@Target(AnnotationTarget.TYPE)
annotation class Anno(val value: String)

class Foo

typealias MyFoo = Foo

class C<T>(val t: T)

typealias MyC = C<@Anno("OK") MyFoo?>
