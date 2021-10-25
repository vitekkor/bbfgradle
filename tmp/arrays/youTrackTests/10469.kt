// Original bug: KT-5514

public fun chmod(
        dir: String? = "default",
        includes: String? = null,
        defaultexcludes: Boolean? = null,
        skipemptyfilesets: Boolean? = null,
        perm: String? = null,
        relative: Boolean? = null,
        `type`: String? = null,
        dest: String? = null,
        forwardslash: Boolean? = null,
        maxparallel: Int? = null,
        verbose: Boolean? = null,
        ignoremissing: Boolean? = null,
        force: Boolean? = null,
        parallel: Boolean? = null,
        error: String? = null,
        input: String? = null,
        output: String? = null,
        resolveexecutable: Boolean? = null,
        searchpath: Boolean? = null,
        resultproperty: String? = null,
        failifexecutionfails: Boolean? = null,
        append: Boolean? = null,
        osfamily: String? = null,
        vmlauncher: Boolean? = null,
        inputstring: String? = null,
        logerror: Boolean? = null,
        outputproperty: String? = null,
        errorproperty: String? = null,
        failonerror: Boolean? = null,
        newenvironment: Boolean? = null,
        spawn: Boolean? = null,
        timeout: Int? = null,
        os: String? = null) {
    System.out.println(dir)
}

fun main(args: Array<String>) {
    chmod(dir = "/home/user/temp", perm = "755")
}
