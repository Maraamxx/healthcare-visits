package com.example.rvtraining


import android.content.Intent
import android.net.Uri
import android.widget.TextView
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class VisitDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visit_details)

        // Get the intent data
        val visitType = intent.getStringExtra("VISIT_TYPE")
        val description = intent.getStringExtra("DESCRIPTION")
        val location = intent.getStringExtra("LOCATION")
        val phone = intent.getStringExtra("PHONE")
        val doctorName = intent.getStringExtra("DOCTOR_NAME") // Add more data if needed
        val appointmentTime = intent.getStringExtra("APPOINTMENT_TIME")
        val specialInstructions = intent.getStringExtra("SPECIAL_INSTRUCTIONS")

        // Find views
        val visitTypeText: TextView = findViewById(R.id.visitTypeText)
        val descriptionText: TextView = findViewById(R.id.descriptionText)
        val locationText: TextView = findViewById(R.id.locationText)
        val phoneText: TextView = findViewById(R.id.phoneText)
        val doctorNameText: TextView = findViewById(R.id.doctorNameText)
        val appointmentTimeText: TextView = findViewById(R.id.appointmentTimeText)
        val specialInstructionsText: TextView = findViewById(R.id.specialInstructionsText)

        // Set the data to the views
        visitTypeText.text = visitType
        descriptionText.text = description
        locationText.text = "Location: $location"
        phoneText.text = "Phone: $phone"
        doctorNameText.text = "Doctor: $doctorName"
        appointmentTimeText.text = "Appointment Time: $appointmentTime"
        specialInstructionsText.text = "Instructions: $specialInstructions"

        // Set up back button
        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()  // Close the activity
        }

        // Call Button
        val callButton: Button = findViewById(R.id.callButton)
        callButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phone")
            }
            startActivity(intent)
        }

        // Map Button - implement map viewing functionality here
        val mapButton: Button = findViewById(R.id.mapButton)
        mapButton.setOnClickListener {
            val latitude = 37.7749 // Replace with your latitude
            val longitude = -122.4194 // Replace with your longitude
            val uri = "geo:$latitude,$longitude?q=$latitude,$longitude(label)"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            intent.setPackage("com.google.android.apps.maps") // This ensures it opens in Google Maps
            startActivity(intent)
        }
//
//        // Appointment Button - implement appointment scheduling functionality here
//        val appointmentButton: Button = findViewById(R.id.appointmentButton)
//        appointmentButton.setOnClickListener {
//        }

        // Share Button - implement sharing functionality here
        val shareButton: Button = findViewById(R.id.shareButton)
        shareButton.setOnClickListener {
            val shareText = "Visit Type: $visitType\nDescription: $description\nLocation: $location\nPhone: $phone"
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareText)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Share Visit Details"))
        }
    }
}
