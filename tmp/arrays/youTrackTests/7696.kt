// Original bug: KT-26215

@Experimental
private annotation class SpecializedAPI

@SpecializedAPI
private fun unsafe() = 1

private class Usage @UseExperimental(SpecializedAPI::class) constructor(val value: Int = unsafe())
