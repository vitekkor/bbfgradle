// Original bug: KT-13228

@Target(AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
annotation class Foo

data class RequestDto(
        val messages: List<@Foo String>
)
