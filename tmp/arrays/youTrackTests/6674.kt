// Original bug: KT-29654

enum class Button(val caution: CautionLevel) {
    POWER(CautionLevel.LOW),
    RESET(CautionLevel.LOW),
    VOLTAGE(CautionLevel.HIGH)
}

enum class CautionLevel(val buttons: List<Button>) {
    HIGH(listOf(Button.VOLTAGE)),
    LOW(listOf(Button.POWER, Button.RESET))
}

fun main(args: Array<String>) {
   println("\nButtons w/ their caution level:")
   Button.values().asSequence()
     .forEach { 
       println("\tButton: $it Caution: ${it.caution}") 
     }
   println("\nCaution Levels w/ associated buttons:")
   CautionLevel.values().asSequence()
     .forEach { 
       println("\tCaution Level: $it Associated Buttons: ${it.buttons}") 
     }
}
