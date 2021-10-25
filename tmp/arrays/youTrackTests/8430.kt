// Original bug: KT-21162

data class MyDto(
        val parameter1: String,
        val parameter2: String = "",
        val parameter3: String = "", 
        val parameter4: String
)
