// Original bug: KT-44939

abstract class A {
    open var attribute = "a"
        protected set
}

class C : A() {
    public override var attribute = super.attribute // REDUNDANT_VISIBILITY_MODIFIER is not reported
        public set 
}
