// Original bug: KT-23903

/*
 * Represents a version of the Kotlin standard library.
 *
 * [major], [minor] and [patch] are integer components of a version,
 * they must be non-negative and not greater than 255 ([MAX_COMPONENT_VALUE]).
 *
 * @constructor Creates a version from all three components.
 */
public class KotlinVersion(val major: Int, val minor: Int, val patch: Int) 
