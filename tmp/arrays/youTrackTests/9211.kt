// Original bug: KT-6840

    private fun x(s: String?) {
        val substring = s?.substring(1)
        // substring != null ==> s?.substring(1) != null ==> (see KT-2127) s != null
        if (substring != null) {
            println(s.any())
        }
    }
