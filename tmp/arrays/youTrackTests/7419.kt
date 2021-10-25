// Original bug: KT-27421

package inego.lib

@Experimental
annotation class ParanormalApi


@ParanormalApi
class StrangeThing {
    init {
        println("Behold, I'm going to make some weird things soon!")
    }
}

@UseExperimental(ParanormalApi::class)
fun createStrangeThing() = StrangeThing()
