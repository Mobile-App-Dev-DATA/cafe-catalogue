package com.example.cafecatalogue

import java.util.ArrayList

class CafeList {
    companion object {
        fun generate(): ArrayList<Cafe> {
            val outList = ArrayList<Cafe>()
            outList.add(Cafe(
                "Hinata Cafe",
                Suburb.FREMANTLE,
                "Hinata Cafe in Fremantle has a cozy and welcoming vibe that makes it easy to relax or catch up with friends. The food is fresh and simple, with unique Japanese-inspired dishes that don’t feel too heavy. It’s a nice spot if you want something different from the usual cafe options."
            ))

            outList.add(Cafe(
                "Rustic Spoon Cafe",
                Suburb.FREMANTLE,
                "This cafe has a warm, homely feel with plenty of charm. The meals are hearty and well-presented, especially their breakfast dishes which are always a standout. Service is friendly and quick, making it a great place for an easy, relaxed meal."
            ))

            outList.add(Cafe(
                "Gesha Cafe",
                Suburb.FREMANTLE,
                "Gesha Cafe has a bright, open interior that makes it feel spacious and inviting. The coffee is consistently smooth, and the menu has plenty of creative breakfast options. It can get busy on weekends, but that just shows how popular the place is."
            ))

            outList.add(Cafe(
                "Cafe Guilty Pleasure",
                Suburb.MTLAWLEY,
                "Cafe Guilty Pleasure has a quirky atmosphere with plenty of character in its decor. Their desserts are the highlight, rich and sweet without being over the top. Their coffee pairs well with the sweets, making it a spot worth visiting when you’re craving something fun and different."
            ))

            outList.add(Cafe(
                "Cheol's Cafe",
                Suburb.MTLAWLEY,
                "Cheol’s Cafe has a clean, modern look with a calm atmosphere that’s easy to settle into. The menu has Korean-inspired dishes, with flavors that feel fresh and comforting. Drinks are well-made and pair nicely with the food."
            ))

            outList.add(Cafe(
                "Secondeli Cafe",
                Suburb.MTLAWLEY,
                "Secondeli Cafe gives off a relaxed, down-to-earth vibe that makes it easy to enjoy. Their breakfast plates are generous and full of flavor, with plenty of variety to choose from."
            ))

            outList.add(Cafe(
                "Boubar",
                Suburb.NEDLANDS,
                "Boubar is a wonderful cafe to spend some time with friends. The peaceful atmosphere is gives coffee lovers a genuine experience and also great conversation."
            ))

            outList.add(Cafe(
                "Kith Eatery",
                Suburb.NEDLANDS,
                "Kith Eatery, located on Aberdare Road in Nedlands, has a delicious breakfast and lunch menu that is sure to leave you satisfied."
            ))

            outList.add(Cafe(
                "Tiamo Cafe",
                Suburb.NEDLANDS,
                "Tiamo Cafe is a family-owned Italian-style restaurant that has an extensive menu and delicious meals. Come into the iconic street corner for a great time."
            ))

            outList.add(Cafe(
                "Sinnamon",
                Suburb.BENTLEY,
                "Sinnamon is located right next to the popular study spot Building 418, or the Architecture building. This cosy cafe invites students and staff alike to come in and have a chat, do some study, or just chill in the relaxing ambiance."
            ))

            outList.add(Cafe(
                "Common Grounds",
                Suburb.BENTLEY,
                "Common Grounds is the perfect cafe to share a coffee with a friend after a lecture, boasting a wide variety of delicious pastries and drinks. The surrounding trees give Common Grounds a peaceful serene atmosphere to take a break from the stresses of uni life."
            ))

            outList.add(Cafe(
                "Basement Cafe",
                Suburb.BENTLEY,
                "The Basement Cafe, located conveniently next to Curtin's Engineering Pavillion, offers a great selection of cheap eats for students rushing in-between classes. Make sure to catch their half price meals before closing!"
            ))
            return outList
        }
    }
}