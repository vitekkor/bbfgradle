// Original bug: KT-44939

abstract class A {
    open var attribute = "a"
        protected set
}

abstract class B : A() {
    override var attribute = "b"
        public set
}

class C : B() {
    public override var attribute = super.attribute // REDUNDANT_VISIBILITY_MODIFIER is not reported
}
