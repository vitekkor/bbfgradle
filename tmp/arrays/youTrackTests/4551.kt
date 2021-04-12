// Original bug: KT-28908

class InjectClass(val host: String) {
    fun regularHost(host: String) {}
}
fun useInjection() {
    InjectClass("select 0 from dual").regularHost("select 0 from dual")
}
