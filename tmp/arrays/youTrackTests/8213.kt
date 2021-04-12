// Original bug: KT-19187

sealed class Animal {
    object AnimalWithLongArmsThatSwingsFromTreeToTree: Animal()
    object LongGreenAnimalWithSharpTeeth: Animal()
    object BigGreyAnimalWithATrunkInsteadOfANose: Animal()
}

open class Cage<Animal>(var animal: Animal)
