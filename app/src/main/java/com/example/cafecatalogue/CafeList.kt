package com.example.cafecatalogue

data class Cafe(val name:String, val suburb:Suburb, val description:String)

class CafeList {
    val cafes = listOf(
        Cafe("Hinata Cafe", Suburb.FREMANTLE, "TODO"),
        Cafe("Kith Eatery", Suburb.NEDLANDS, "TODO"),
        Cafe("Lo Quay River Cafe", Suburb.WILSON,
            "A scenic cafe by the river with an indoors and outdoors area to enjoy the view of swans flying by and birds spying for some food. During winter, outdoor heating appliances are provided to warm guests under their veranda, allowing a free open space for chats with family and mates. It looks amazing but that’s about it, the food was not the greatest thing in the world.\n" +
                "\n" +
                "1/5 food and drinks\n" +
                "4/5 atmosphere\n" +
                "\n" +
                "TODO: implement images"),
        Cafe("Sinnamon", Suburb.BENTLEY, "TODO")
    )
}