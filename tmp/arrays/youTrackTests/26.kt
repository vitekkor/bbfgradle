// Original bug: KT-45458

val map: Map<String, Int> = hashMapOf(
        "&Aacute;" to 193,
        "&Aacute" to 193,
        "&aacute;" to 225,
        "&aacute" to 225,
        "&Abreve;" to 258,
        "&abreve;" to 259,
        "&ac;" to 8766,
        "&acd;" to 8767,
        "&acE;" to 8766,
        "&Acirc;" to 194,
        "&Acirc" to 194,
        "&acirc;" to 226,
        "&acirc" to 226,
        "&acute;" to 180,
        "&acute" to 180,
        "&Acy;" to 1040,
        "&acy;" to 1072,
        "&AElig;" to 198,
        "&AElig" to 198,
        "&aelig;" to 230,
        "&aelig" to 230,
        "&af;" to 8289,
        "&Afr;" to 120068
)
