// Original bug: KT-33427

class TestNumbers {
    internal var byteNumber = 210 //binary representation is lost
    internal var million = 1000000 //underscores are lost
    internal var hexBytes = -0x1321a2 //hexadecimal representaion with underscores are lost
}
