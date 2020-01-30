package com.manoelh.cores.activities

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.manoelh.cores.R
import com.manoelh.cores.adapter.ColorsListAdapter
import com.manoelh.cores.model.Colors
import com.manoelh.cores.service.ServiceRequest
import kotlinx.android.synthetic.main.activity_main.*

private const val NUMBER_ONE = 1

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var mColors: Colors = Colors(arrayListOf())
    private val mServiceRequest = ServiceRequest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addListeners()
        searchColorFromAPI()
    }

    private fun addListeners(){
        imageViewRefreshColors.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.imageViewRefreshColors -> searchColorFromAPI()
        }
    }


    private fun searchColorFromAPI() {
        switchProgressVisibility()
        val colors: Colors? = Colors(arrayListOf())
        mServiceRequest.searchColorFromAPI(apiCallback(colors))
    }

    private fun apiCallback(colors: Colors?): (Colors?) -> Unit {
        return {
            if(it == null)
                buildToast(getString(R.string.response_unsuccessful))

            else{
                mColors = it
                initializeRecyclerView()
                filterUniqueColors(mColors.result)
                switchProgressVisibility()
            }
        }
    }

    private fun initializeRecyclerView() {
        recyclerView.adapter = ColorsListAdapter(mColors.result)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun filterUniqueColors(cores: MutableList<String>){
        if (cores.isNotEmpty()) {
            val numberOfUniqueColors = cores.groupBy { it }.keys.size
            setTextViewWithColorsFound(numberOfUniqueColors)
        }
    }

    private fun setTextViewWithColorsFound(numberOfUniqueColors: Int) {
        if (numberOfUniqueColors > NUMBER_ONE)
            textViewColors.text = "$numberOfUniqueColors ${getString(R.string.unique_colors)}"
        else
            textViewColors.text = getString(R.string.cor_unique_default_value)
    }

    private fun buildToast(message: String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    private fun switchProgressVisibility(){
        if (progressBar.isVisible) {
            progressBar.visibility = ProgressBar.INVISIBLE
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
        else {
            progressBar.visibility = ProgressBar.VISIBLE
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }
}
