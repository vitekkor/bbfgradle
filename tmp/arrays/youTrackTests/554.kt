// Original bug: KT-42240

class DataSet (val label: String, val data: List<DataPoint>)
class DataPoint(val primary: Float, val secondary: Float)
class Axis (val primary: Boolean, val type: String, val position: String)
