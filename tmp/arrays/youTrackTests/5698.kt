// Original bug: KT-32882

class MyEditText() : EditText() {
    private var myState = Any()
    override fun onCreateDrawableState() {
        myState == null
    }
}

open class EditText {
    constructor() {
        onCreateDrawableState() // Calling non-final function onCreateDrawableState in constructor
    }

    open fun onCreateDrawableState() {

    }
}
