// Original bug: KT-39752

class Self3 { private val self: Self3 = this }

class Self4 {
    private val self: Self4

    constructor() { self = this }

    constructor(other: Self4) { self = other }
}
