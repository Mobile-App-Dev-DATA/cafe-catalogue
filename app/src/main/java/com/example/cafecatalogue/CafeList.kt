package com.example.cafecatalogue

import java.util.ArrayList

class CafeList {
    companion object {
        fun generate(): ArrayList<Cafe> {
            val outList = ArrayList<Cafe>()
            outList.add(Cafe("home cafe", Suburb.BENTLEY, "a nice homely cafe", true))
            outList.add(Cafe("hommie spot", Suburb.NEDLANDS, "a spot for the hommies to drink coffee", true))
            outList.add(Cafe("shit cafe", Suburb.FREMANTLE, "a shit cafe.  do not go here", false))
            outList.add(Cafe("imposter cafe", Suburb.WILSON, "kinda sus", false))
            return outList
        }
    }
}