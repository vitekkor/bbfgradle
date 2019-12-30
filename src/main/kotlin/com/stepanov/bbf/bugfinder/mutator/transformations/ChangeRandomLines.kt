package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.executor.MutationChecker
import ru.spbstu.kotlin.generate.util.nextInRange
import java.util.*

class ChangeRandomLines : Transformation() {

    override fun transform() {
        val text = file.text.lines().toMutableList()
        for (i in 0..Random().nextInt(shuffleConst)) {
            val numLine = Random().nextInt(text.size)
            val insLine = Random().nextInt(text.size)
            Collections.swap(text, numLine, insLine)
            if (!MutationChecker.checkTextCompiling(getText(text))) {
                Collections.swap(text, numLine, insLine)
            }
        }
        file = psiFactory.createFile(getText(text))
    }

    private fun getText(text: MutableList<String>) = text.joinToString(separator = "\n")

    private val shuffleConst = Random().nextInRange(file.text.lines().size * 1, file.text.lines().size * 4)
}