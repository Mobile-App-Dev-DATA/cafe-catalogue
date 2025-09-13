package com.example.cafecatalogue

import java.util.ArrayList

class CafeList {
    companion object {
        fun generate(): ArrayList<Cafe> {
            val outList = ArrayList<Cafe>()
            outList.add(Cafe(
                "Hinata Cafe",
                Suburb.FREMANTLE,
                ""
            ))

            outList.add(Cafe(
                "Rustic Spoon Cafe",
                Suburb.FREMANTLE,
                ""
            ))

            outList.add(Cafe(
                "Gesha Cafe",
                Suburb.FREMANTLE,
                ""
            ))

            outList.add(Cafe(
                "Cafe Guilty Pleasure",
                Suburb.MTLAWLEY,
                ""
            ))

            outList.add(Cafe(
                "Cheol's Cafe",
                Suburb.MTLAWLEY,
                ""
            ))

            outList.add(Cafe(
                "Secondeli Cafe",
                Suburb.MTLAWLEY,
                ""
            ))

            outList.add(Cafe(
                "Boubar",
                Suburb.NEDLANDS,
                ""
            ))

            outList.add(Cafe(
                "Kith Eatery",
                Suburb.NEDLANDS,
                ""
            ))

            outList.add(Cafe(
                "Tiamo Cafe",
                Suburb.NEDLANDS,
                ""
            ))

            outList.add(Cafe(
                "Sinnamon",
                Suburb.BENTLEY,
                ""
            ))

            outList.add(Cafe(
                "Common Grounds",
                Suburb.BENTLEY,
                ""
            ))

            outList.add(Cafe(
                "Basement Cafe",
                Suburb.BENTLEY,
                ""
            ))
            return outList
        }
    }
}