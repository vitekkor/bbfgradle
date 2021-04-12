// Original bug: KT-38739

interface Converter<Source, Target> {
    fun convert(source: Source): Target
}

class MyConverter : Converter<String, Int> {
    override fun convert(source: String): Int = TODO("not implemented")
}

class OtherConverter : Converter<String, Double> {
    override fun convert(source: String): Double = TODO("not implemented")
}

class Consumer(private val converter: Converter<String, Double>)
