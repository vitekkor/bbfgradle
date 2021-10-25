// Original bug: KT-35175

/**
 * Random number generator, algorithm "xorwow" from p. 5 of Marsaglia, "Xorshift RNGs".
 *
 * Cycles after 2^160 * (2^32-1) repetitions.
 *
 * See http://www.jstatsoft.org/v08/i14/paper for details.
 */
