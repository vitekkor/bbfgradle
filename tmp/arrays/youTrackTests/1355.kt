// Original bug: KT-44196

public interface Foo {
    public fun foo()
}

public class Bar : Foo {
    public override fun foo() {} // `public` is redundant here even in explicit API mode but there's no warning
}
