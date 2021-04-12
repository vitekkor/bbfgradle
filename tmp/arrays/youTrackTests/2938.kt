// Original bug: KT-40129


import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
object InlineInvocableObjectPropertyCauseKotlinCodeGenCompilationExceptionAtCallSite {
    private val io1 = object {
        inline operator fun invoke() {
            println("io1")
        }
    }

    object IO2 {
        inline operator fun invoke() {
            println("IO2")
        }
    }

    private fun caller() {
        val io3 = object {
            inline operator fun invoke() {
                println("io3")
            }
        }

        /*
         * Uncommenting the next following line causes the following exception
         * "e: org.jetbrains.kotlin.codegen.CompilationException: Back-end (JVM) Internal error: Failed to generate function callObjectProperty"
         * */
//        io1()
        IO2()
        io3()
    }

    @Test
    fun run_valid() {
        println("test run")
    }

}
