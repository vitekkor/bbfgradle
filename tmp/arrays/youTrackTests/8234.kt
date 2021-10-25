// Original bug: KT-13227

sealed class Stmt

class ForStmt : Stmt()

sealed class Expr : Stmt()

fun test(x: Stmt): String =
    // NO_ELSE_IN_WHEN: 
    // 'when' expression must be exhaustive, add necessary 'is ForStmt' branch or 'else' branch instead
    when (x) {
        is Expr -> "expr"
        is Stmt -> "stmt"
    }
