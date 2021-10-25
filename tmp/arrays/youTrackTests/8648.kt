// Original bug: KT-20028

import org.json.JSONException
import org.json.JSONObject

class TestExtraConst {
    companion object {
        private const val JSON = "{}"

        fun getJsonObject(): JSONObject? {
            return try {
                JSONObject(JSON)
            } catch (e: JSONException) {
                null
            }
        }
    }
}
