// Original bug: KT-15407

enum class Language(val creator: String) {
    KOTLIN("Andrey Breslav"),
    JAVA("James Gosling"),
    RUST("Graydon Hoare"),
    PYTHON("Guido van Rossum"),
    JAVASCRIPT("Brendan Eich"),
    RUBY("Yukihiro Matsumoto"),
    SWIFT("Chris Lattner "),
    GO("2Rob,Ken");
}
fun createdAt(language: Language) =
    when (language) {
        Language.KOTLIN -> 2011
        Language.JAVA -> 1995
        Language.RUST -> 2010
        Language.PYTHON -> 1991
        Language.JAVASCRIPT -> 1995
        Language.RUBY -> 1995
        Language.SWIFT -> 2014
        Language.GO -> 2009
    }
