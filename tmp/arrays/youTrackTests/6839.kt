// Original bug: KT-14393

class RenderingProfile : RenderingProfileHolder {
    override var renderingProfile: RenderingProfile get() = this
        set(value) {

        }

}

interface RenderingProfileHolder {
    var renderingProfile: RenderingProfile
}

