// Original bug: KT-33742

class `New rule for operator style consistency` {

    fun `Concat ok`() = "" +
        ""

    /* Won't compile */
    //fun `Concat not ok`() = ""
    //    + ""

    fun `Boolean ok`(b1: Boolean, b2: Boolean) = b1 ||
        b2

    fun `Boolean ok, but we may want to (optionally) forbid this`(b1: Boolean, b2: Boolean) = b1
        || b2
}
