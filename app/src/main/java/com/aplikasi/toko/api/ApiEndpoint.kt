package com.aplikasi.toko.api

import com.aplikasi.toko.response.itemTransaksi.itemTransaksiResponsePost
import com.aplikasi.toko.response.login.LoginResponse
import com.aplikasi.toko.response.produk.ProdukResponse
import com.aplikasi.toko.response.produk.ProdukResponsePost
import com.aplikasi.toko.response.transaksi.TransaksiResponse
import com.aplikasi.toko.response.transaksi.TransaksiResponsePost
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email : String,
        @Field("password") password : String
    ) : Call<LoginResponse>

    @GET("produk")
    fun getproduk(@Header("Authorization") token : String ) : Call<ProdukResponse>

    @FormUrlEncoded
    @POST("Produk")
    fun postProduk(
        @Header("Authorization") token : String,
        @Field("admin_id") admin_id : Int,
        @Field("nama") nama : String,
        @Field("harga") harga : Int,
        @Field("stok") stok : Int
    ) : Call<ProdukResponsePost>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "produk", hasBody = true)

    fun deleteProduk(
        @Header("Authorization") token : String,
        @Field("id") id : Int,

    ) : Call<ProdukResponsePost>

    @FormUrlEncoded
    @HTTP(method = "PUT", path = "produk", hasBody = true)

    fun putProduk(
        @Header("Authorization") token : String,
        @Field("id") id : Int,
        @Field("admin_id") admin_id : Int,
        @Field("nama") nama : String,
        @Field("harga") harga : Int,
        @Field("stok") stok : Int,


        ) : Call<ProdukResponsePost>


    @FormUrlEncoded
    @POST("transaksi")
    fun postTransaksi(
        @Header("Authorization") token : String,
        @Field("admin_id") admin_id : Int,
        @Field("total") total : Int
    ) : Call<TransaksiResponsePost>

    @FormUrlEncoded
    @POST("item_transaksi")
    fun postItemTransaksi(
        @Header("Authorization") token : String,
        @Field("transaksi_id") transaksi_id : Int,
        @Field("produk_id") produk_id : Int,
        @Field("qty") qty : Int,
        @Field("harga_saat_transaksi") harga_saat_transaksi : Int
    ) : Call<itemTransaksiResponsePost>

    @GET("transaksi_bulan_ini")
    fun getTransaksi(@Header("Authorization") token : String ) : Call<TransaksiResponse>

}