package com.training.victor.development.network.responses.models

data class MedicRespItem (val id: String,
                          val name: String,
                          val photoId: String,
                          val rating: String,
                          val address: String,
                          val lat: String,
                          val lng: String,
                          val highlighted: String,
                          val reviewCount: String,
                          val specialityIds: List<Int>,
                          val source: String,
                          val phoneNumber: String,
                          val email: String,
                          val website: String,
                          val openingHours: List<String>)