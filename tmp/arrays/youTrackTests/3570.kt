// Original bug: KT-38359

open class ItemProperties
open class Item<P : ItemProperties>
open class Type<B : Item<P>, P : ItemProperties>
open class Handler<B : Item<*>>(typeClass: Class<out Type<B, *>>)

class GoodType : Type<Item<ItemProperties>, ItemProperties>()
val good = Handler(GoodType::class.java)

open class LineItem<P : ItemProperties> : Item<P>()
open class LineType<P : ItemProperties> : Type<LineItem<P>, P>()

class BadType : LineType<ItemProperties>()
val bad = Handler(BadType::class.java)
