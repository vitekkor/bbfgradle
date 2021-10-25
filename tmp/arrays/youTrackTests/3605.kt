// Original bug: KT-28846

package oldpackage 

@Deprecated(message = "Deprecated", replaceWith = ReplaceWith("NewTypeAlias", imports = ["newpackage.NewTypeAlias"]))
typealias OldTypeAlias = String

fun test() {
    val x: OldTypeAlias
}
