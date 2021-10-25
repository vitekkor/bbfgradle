// Original bug: KT-7055

class Foo {

    val prop1 = "1"
    val prop2 = "2"

    val works = listOf<Foo.() -> String>(
            { this.prop1 },
            { this.prop2 }
    )

    val alsoWorks = mapOf(
            "a".to<String, Foo.() -> String>   { this.prop1 },
            "b".to<String, Foo.() -> String>   { this.prop2 }
    )

    val worksButLooksAweful = mapOf(
            "a" to
                    fun Foo.(): String = this.prop1,
            "b" to
                    fun Foo.(): String = this.prop2
    )

    val doesntWork = mapOf<String, Foo.() -> String>(
            "a" to  { this.prop1 },
            "b" to  { this.prop2 }
    )
}
