package com.manoelh.cores.activities

import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.manoelh.cores.R
import com.manoelh.cores.model.Cores
import com.manoelh.cores.service.ServiceGenerator
import com.manoelh.cores.service.RetrofitService
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adicionaEventosListeners()
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        carregarCores()
    }

    private fun adicionaEventosListeners(){
        imageViewRecarregarCores.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.imageViewRecarregarCores -> carregarCores()
        }
    }

    private fun carregarCores(){
        retrofitConverter()
    }

    fun retrofitConverter() {
        val service: RetrofitService = ServiceGenerator.createService(RetrofitService::class.java)
        val result: MutableList<String> = arrayListOf()
        val call: Call<Cores?>? = service.converterUnidade(result)

        call?.enqueue(object : Callback<Cores?> {
            override fun onResponse(call: Call<Cores?>?, response: Response<Cores?>) {

                if (response.isSuccessful) {
                    val respostaServidor: Cores? = response.body()
                    //verifica aqui se o corpo da resposta não é nulo
                    if (respostaServidor != null) {
                        val cores = respostaServidor
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

    private fun constroiToast(message: String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}
