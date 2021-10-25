// Original bug: KT-28914

const val tableName1: String = "dual" // Error highlighting.
const val tableName2: String = "dual" // Error highlighting.

// language=Oracle
val sql1a = "select 0 from $tableName1"
// language=Oracle
val sql1b = "select 0 from " + tableName2 // Also error, but not the subject of this issue.
