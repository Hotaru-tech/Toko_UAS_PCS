package com.aplikasi.toko

import com.aplikasi.toko.response.cart.Cart

interface CallbackInterface {
    fun passResultCallback(total:String,cart:ArrayList<Cart>)
}