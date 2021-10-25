// Original bug: KT-31924

object Fixtures {
    object Register {
        object Domain {
            object UserRepository {
                val authSuccess = true

                val authError = false
            }
        }
    }
}
