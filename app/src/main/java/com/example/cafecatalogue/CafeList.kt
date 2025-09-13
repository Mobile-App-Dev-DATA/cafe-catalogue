package com.example.cafecatalogue

import java.util.ArrayList

class CafeList {
    companion object {
        fun generate(): ArrayList<Cafe> {
            val outList = ArrayList<Cafe>()
            outList.add(Cafe("home cafe", Suburb.BENTLEY, "a nice homely cafe"))
            outList.add(Cafe("hommie spot", Suburb.NEDLANDS, "a spot for the hommies to drink coffee"))
            outList.add(Cafe("shit cafe", Suburb.FREMANTLE, "a shit cafe.  do not go here"))
            outList.add(Cafe("imposter cafe", Suburb.MTLAWLEY, "kinda sus"))
            return outList
        }
    }
}