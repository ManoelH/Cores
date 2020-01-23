package com.manoelh.cores

import org.junit.Assert
import org.junit.Test

class MainActivityTest {

    @Test
    fun `verifica se metodo esta retornando o valor correto de cores unicas`(){
        val paremetros = arrayListOf("#ffffff", "#ffffff", "#1fffff")
        Assert.assertEquals(filtraQuantidadeDeCoresUnicas(paremetros), 2)
    }

    @Test
    fun `verifica se metodo que retorna cores unicas quebra com List vazio de cores`(){
        val paremetros = arrayListOf<String>()
        Assert.assertEquals(filtraQuantidadeDeCoresUnicas(paremetros), 0)
    }


    /*METODO COPIADO DA MAIN ACTIVITY PARA EVITAR O USO DO MESMO NA FORMA PUBLICA,
       FOI TENTADA A UTILIZACAO DO JAVA REFLECTION MAS SEM EXITO*/

    private fun filtraQuantidadeDeCoresUnicas(cores: MutableList<String>): Int{
        val numeroDeCoresUnicas: MutableList<String> = arrayListOf()
        if (cores.isNotEmpty()) {
            cores.forEach {
                if (!numeroDeCoresUnicas.contains(it))
                    numeroDeCoresUnicas.add(it)
            }
            //setaTextViewComQuantidadeDeCoresUnicasEncontradas(numeroDeCoresUnicas.size)
        }
        return numeroDeCoresUnicas.size
    }
}