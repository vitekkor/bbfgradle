// Original bug: KT-8540

interface Doc
interface ProgramElementDoc : Doc

open class DocNodeAdapter : Doc
public class ExecMemAdapter : DocNodeAdapter(), ProgramElementDoc
public class ProElAdapeter : DocNodeAdapter(), ProgramElementDoc

fun f(a: String?): ProgramElementDoc? = a?.let {
    if (true) {
        ExecMemAdapter()
    } else {
        ProElAdapeter()
    }
}
