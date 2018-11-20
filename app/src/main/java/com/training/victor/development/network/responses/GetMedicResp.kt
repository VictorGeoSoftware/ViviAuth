package com.training.victor.development.network.responses

import com.training.victor.development.network.responses.models.MedicRespItem

data class GetMedicResp (val doctors: List<MedicRespItem>, val lastKey: String)