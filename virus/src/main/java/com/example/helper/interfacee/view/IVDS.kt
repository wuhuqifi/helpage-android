package com.example.helper.interfacee.view

import android.os.Handler
import com.example.helper.base.IView
import com.example.helper.beans.DataStreams

interface IVDS: IView<DataStreams> {
    fun getmHandler(): Handler?
}