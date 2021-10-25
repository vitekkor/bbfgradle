// Original bug: KT-21378

private operator fun Any?.not(): Boolean {
	if (this == null) return true
	if(this is Collection<*>) return this.isEmpty()
	if(this is Map<*,*>) return this.isEmpty()
	if(this is Number)return this==0
	if(this is Boolean)return this==false
        return false // All other objects are truthy
}
