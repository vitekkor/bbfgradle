// Original bug: KT-18496

import org.intellij.lang.annotations.Language

inline val @receiver:Language("SQL") String.sql get() = this

val id = 1
val s = """SELECT * FROM Table WHERE id = ${id}""".sql
