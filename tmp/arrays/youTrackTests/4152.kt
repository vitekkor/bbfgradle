// Original bug: KT-19751

interface Feature

abstract class Repro {

    class TTT : Repro()
    class TTF : Repro(), Feature
    class TFT : Repro(), Feature
    class TFF : Repro()
    class FTT : Repro(), Feature
    class FTF : Repro(), Feature
    class FFT : Repro(), Feature
    class FFF : Repro()

    companion object {
        fun invalid(arg1: Boolean, arg2: Boolean, arg3: Boolean): Repro {
            return if (arg1) {
                if (arg2) {
                    if (arg3) TTT() else TTF()
                }
                else {
                    if (arg3) TFT() else TFF()
                }
            }
            else {
                if (arg2) {
                    // ERROR: Kotlin: Type mismatch: inferred type is Any but Repro was expected
                    if (arg3) FTT() else FTF()
                }
                else {
                    if (arg3) FFT() else FFF()
                }
            }
        }

        // with the returns pushed one level down, the type inference is correct
        fun valid(arg1: Boolean, arg2: Boolean, arg3: Boolean): Repro {
            if (arg1) {
                return if (arg2) {
                    if (arg3) TTT() else TTF()
                }
                else {
                    if (arg3) TFT() else TFF()
                }
            }
            else {
                return if (arg2) {
                    if (arg3) FTT() else FTF()
                }
                else {
                    if (arg3) FFT() else FFF()
                }
            }
        }
    }
}

