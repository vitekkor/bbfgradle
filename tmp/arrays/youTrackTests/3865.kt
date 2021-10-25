// Original bug: KT-29952

/**
 * Specifies a code fragment that can be used to replace a deprecated function, property or class. Tools such
 * as IDEs can automatically apply the replacements specified through this annotation.
 *
 * @property expression the replacement expression. The replacement expression is interpreted in the context
 *     of the symbol being used, and can reference members of enclosing classes etc.
 *     For function calls, the replacement expression may contain argument names of the deprecated function,
 *     which will be substituted with actual parameters used in the call being updated. The imports used in the file
 *     containing the deprecated function or property are NOT accessible; if the replacement expression refers
 *     on any of those imports, they need to be specified explicitly in the [imports] parameter.
 * @property imports the qualified names that need to be imported in order for the references in the
 *     replacement expression to be resolved correctly.
 */
