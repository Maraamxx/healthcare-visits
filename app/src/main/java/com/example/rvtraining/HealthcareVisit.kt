package com.example.rvtraining

data class HealthcareVisit(
    val visitType: String,
    val description: String,
    val location: String,
    val phoneNumber: String  ,
    val doctorName: String,
    val appointmentTime: String,
    val specialInstructions: String
)
