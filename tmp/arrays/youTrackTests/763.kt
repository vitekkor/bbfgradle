// Original bug: KT-44297

data class SceneItemData(
    val sceneId: String?,
    val name: String?,
    val isActive: Boolean
)

class SceneItem(
    val itemData: SceneItemData,
    private val onSceneOptionsClicked: (String) -> Unit
) {
    
    val sceneId: String? by itemData::sceneId
    private val name: String? by itemData::name
    val isActive: Boolean by itemData::isActive
}
