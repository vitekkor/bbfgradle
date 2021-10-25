// Original bug: KT-27109

fun computeGrade(score: Int): Grade =
        when (score) {
            in 90..100 -> Grade.A
            in 75 until 90 -> Grade.B
            in 60 until 75 -> Grade.C
            in 0 until 60 -> Grade.D
            else -> throw IllegalStateException("Wrong score value!")
        }

enum class Grade { A, B, C, D }
