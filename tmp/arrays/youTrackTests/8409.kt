// Original bug: KT-21732

package bug

import bug.Position.woops
import bug.Position.ok

object Position
{
  var woops: Int = 0
  var ok: Int = 0
}

fun error()
{
  woops += 0
}

fun worksFine()
{
  Position.ok += 0
}
