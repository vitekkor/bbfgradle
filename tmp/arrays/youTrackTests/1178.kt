// Original bug: KT-35244

package com.semarchy.commons.sql.dsl

fun main() {
	data class Col(val expr: String, val alias: String? = null) {
		fun toSql(): String {
			return if (alias == null) {
				expr
			} else {
				"$expr as $alias"
			}
		}
	}

	val columns = listOf(Col("C1"), Col("SYSDATE()", "DATE"))
	val select = """
                |select
                ${columns.joinToString(
		transform = { it.toSql().prependIndent("|  ") },
		separator = ",\n"
	)}
                |from T                
                """.trimMargin()
	println(select)
}
