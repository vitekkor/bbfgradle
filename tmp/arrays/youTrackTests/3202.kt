// Original bug: KT-39175

        val name = if (System.currentTimeMillis() % 2L == 0L) null else "asd"
        val isNameEmpty = name.isNullOrEmpty()
        val title = when {
            isNameEmpty -> "Hi"
            else -> name
        }
