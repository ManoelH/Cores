package com.manoelh.cores.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.manoelh.cores.R
import com.manoelh.cores.adapter.ListaDeCoresAdapter
import com.manoelh.cores.model.Cores
import com.manoelh.cores.service.ServiceGenerator
import com.manoelh.cores.service.RetrofitService
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val NUMERO_UM = 1

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var mCores: Cores = Cores(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adicionaEventosListeners()
        buscaCoresDaAPI()
    }

    private fun adicionaEventosListeners(){
        imageViewRecarregarCores.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.imageViewRecarregarCores -> buscaCoresDaAPI()
        }
    }

    fun buscaCoresDaAPI() {
        val service: RetrofitService = ServiceGenerator.createService(RetrofitService::class.java)
        val result: MutableList<String> = arrayListOf()
        val call: Call<Cores?>? = service.converterUnidade(result)
        call?.enqueue(object : Callback<Cores?> {
            override fun onResponse(call: Call<Cores?>?, response: Response<Cores?>) {

                if (response.isSuccessful) {
                    val respostaServidor: Cores? = response.body()

                    if (respostaServidor != null) {
                        mCores = respostaServidor
                        inicializaRecyclerView()
                        filtraQuantidadeDeCoresUnicas(mCores.result)
                    }
                    else {
                        constroiToast("Resposta nula do servidor")
                    }
                }

                else {
                    constroiToast("Resposta não foi sucesso")
                    // segura os erros de requisição
                    val errorBody: ResponseBody = response.errorBody()
                }
            }

            override fun onFailure(call: Call<Cores?>?, t: Throwable?) {
                constroiToast("Erro na chamada ao servidor")
            }
        })
    }

    private fun inicializaRecyclerView() {
        recyclerView.adapter = ListaDeCoresAdapter(mCores.result)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun filtraQuantidadeDeCoresUnicas(cores: MutableList<String>){
        val numeroDeCoresUnicas: MutableList<String> = arrayListOf()
        if (cores.isNotEmpty()) {
            cores.forEach {
                if (!numeroDeCoresUnicas.contains(it))
                    numeroDeCoresUnicas.add(it)
            }
            setaTextViewComQuantidadeDeCoresUnicasEncontradas(numeroDeCoresUnicas)
        }
    }

    private fun setaTextViewComQuantidadeDeCoresUnicasEncontradas(numeroDeCoresUnicas: MutableList<String>) {
        if (numeroDeCoresUnicas.size > NUMERO_UM)
            textViewQuantidadeDeCores.text =
                "${numeroDeCoresUnicas.size} ${getString(R.string.cores_unicas)}"
    }

    private fun constroiToast(message: String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}
