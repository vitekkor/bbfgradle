// Original bug: KT-26950


interface TestObserver {

    // TODO: lorem ipsum bla bla bla
    //   thus we conclude, we have to do a lot more work to make this behave
    //   correctly in all cases
    fun test(id: Int, name: String)
}
