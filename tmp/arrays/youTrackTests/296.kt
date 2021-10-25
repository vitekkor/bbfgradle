// Original bug: KT-45562

interface CustomTypeFace

tailrec fun tailrecDefaultError(fake: Int, ctf: CustomTypeFace = object : CustomTypeFace {}) {
    tailrecDefaultError(0)
    // Or, to avoid the endless recursion.
    // if (fake <= 0 || fake > 10) return
    // else return tailrecDefaultError(fake = fake - 1)
}
