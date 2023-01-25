package com.aplikasi.toko

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.toko.LoginActivity.Companion.sessionManager
import com.aplikasi.toko.adapter.ProdukAdapter
import com.aplikasi.toko.api.BaseRetrofit
import com.aplikasi.toko.response.produk.ProdukResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProdukFragment : Fragment() {

    private val api by lazy { BaseRetrofit().endpoint}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_produk,container,false)

        getProduk(view)

        val btnTambah = view.findViewById<Button>(R.id.btnTambah)
        btnTambah.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("status","tambah")
//            Toast.makeText(activity?.applicationContext,"click", Toast.LENGTH_LONG).show()
              findNavController().navigate(R.id.produkFormFragment,bundle)
        }

        return view
    }

    fun getProduk(view:View){
        val token = sessionManager.getString("TOKEN")

        api.getproduk(token.toString()).enqueue(object : Callback<ProdukResponse>{
            override fun onResponse(
                call: Call<ProdukResponse>,
                response: Response<ProdukResponse>
            ) {
                Log.d("ProdukData",response.body().toString())


                val txtTotalProduk = view.findViewById(R.id.txtTotalProduk) as TextView
                val rv = view.findViewById(R.id.rv_produk) as RecyclerView

                txtTotalProduk.text = response.body()!!.data.produk.size.toString() + "item"

                rv.setHasFixedSize(true)
                rv.layoutManager = LinearLayoutManager(activity)
                val rvAdapter = ProdukAdapter(response.body()!!.data.produk)
                rv.adapter = rvAdapter

            }

            override fun onFailure(call: Call<ProdukResponse>, t: Throwable) {
                Log.e("ProdukError",t.toString())
            }

        })
    }
}