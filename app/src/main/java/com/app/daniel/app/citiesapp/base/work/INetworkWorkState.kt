package com.app.daniel.app.citiesapp.base.work

import androidx.lifecycle.Observer
import androidx.work.WorkInfo

interface INetworkWorkState : Observer<List<WorkInfo>> {
    override fun onChanged(worksInfo: List<WorkInfo>)
}