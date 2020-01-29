package com.manoelh.cores

import org.junit.Assert
import org.junit.Test

class MainActivityTest {

    @Test
    fun `verify if method is returning the correct value of unique colors`(){
        val parameters = arrayListOf("#ffffff", "#ffffff", "#1fffff")
        Assert.assertEquals(filterUniqueColors(parameters), 2)
    }

    @Test
    fun `verify if method that return unique colors break with empty list`(){
        val parameters = arrayListOf<String>()
        Assert.assertEquals(filterUniqueColors(parameters), 0)
    }



    private fun filterUniqueColors(cores: MutableList<String>): Int{
        if (cores.isNotEmpty()) {
            val numberOfUniqueColors = cores.groupBy { it }.keys.size
            return numberOfUniqueColors
        }
        return 0
    }
}