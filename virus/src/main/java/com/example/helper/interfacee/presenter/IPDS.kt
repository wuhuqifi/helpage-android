package com.example.helper.interfacee.presenter

import android.app.Activity
import com.example.helper.base.IPresenter
import com.example.helper.interfacee.view.IVDS

interface IPDS:IPresenter<IVDS> {
    fun fresh()
}