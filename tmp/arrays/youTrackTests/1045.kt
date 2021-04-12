// Original bug: KT-13108

interface CfType
interface CfStringType : CfType

interface CfValue<out T: CfType>
class CfStringStaticValue<out T: CfStringType>(val staticValue: String) : CfValue<T>
class CfRefFunction<out T : CfType>(val reference: String): CfValue<T>
