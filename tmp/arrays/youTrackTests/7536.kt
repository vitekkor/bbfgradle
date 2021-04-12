// Original bug: KT-23005

@org.intellij.lang.annotations.Language("TEXT", prefix = "abc", suffix = "ghi")
val test1 = "def"

// language="TEXT" prefix="abc" suffix=ghi
val test2 = "def"
