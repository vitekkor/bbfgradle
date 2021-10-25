// Original bug: KT-31317

    /**
     * Least significant (right) part of the common base UUID: `00000000-0000-1000-8000-00805f9b34fb`.
     *
     * Unsigned notation: `0x8000_00805f9b34fb`
     */
    private const val commonBaseLeastSigBits = 0x0000_00805f9b34fbL or (0x8000L shl 48)
