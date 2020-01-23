package com.manoelh.cores

import org.junit.Assert
import org.junit.Test

class MainActivityTest {

    private fun filtraQuantidadeDeCoresUnicas(cores: MutableList<String>): Int{
        val numeroDeCoresUnicas: MutableList<String> = arrayListOf()
        if (cores.isNotEmpty()) {
            cores.forEach {
                if (!numeroDeCoresUnicas.contains(it))
                    numeroDeCoresUnicas.add(it)
            }
            //setaTextViewComQuantidadeDeCoresUnicasEncontradas(numeroDeCoresUnicas)
        }
        return numeroDeCoresUnicas.size
    }

    @Test
    fun `verifica se metodo esta retornando o valor correto de cores`(){
        val paremetros = arrayListOf("#ffffff", "#ffffff", "#1fffff")
        Assert.assertEquals(filtraQuantidadeDeCoresUnicas(paremetros), 2)
    }
}