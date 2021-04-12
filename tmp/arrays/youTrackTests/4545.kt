// Original bug: KT-25017

private fun testFold() {
    ;{ ->
        val a = 1
        ;{ ->
        //the inner lambda cannot auto-indent correctly
        val b = 1
    }()
    }()
}
