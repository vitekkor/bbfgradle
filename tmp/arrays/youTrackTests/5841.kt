// Original bug: KT-32320

package my.ada.adad.ad

import my.ada.adad.ad.Fixtures.Register.Domain.UserRepository

class Fixtures {
    class Register {
        object Domain {
            object UserRepository {
                val authSuccess = true

                val authError = false
            }
        }
    }
}

fun test() {
    Fixtures.Register.Domain.UserRepository.authSuccess
}
