package com.example.rvtraining

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var healthcareVisitAdapter: HealthcareVisitAdapter
    private lateinit var healthcareVisitList: List<HealthcareVisit>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        healthcareVisitList = listOf(
            HealthcareVisit(
                visitType = "House Call",
                description = "A doctor visits your home for a general check-up or follow-up.",
                location = "Patient's Home",
                phoneNumber = "+1 555-0100",
                doctorName = "Dr. Sarah Lee",
                appointmentTime = "Monday, 10:00 AM",
                specialInstructions = "Ensure a quiet space for the doctor to conduct the check-up."
            ),
            HealthcareVisit(
                visitType = "Clinic Visit",
                description = "Consultation at a healthcare clinic.",
                location = "Greenwood Medical Clinic",
                phoneNumber = "+1 555-0123",
                doctorName = "Dr. John Smith",
                appointmentTime = "Wednesday, 2:00 PM",
                specialInstructions = "Bring previous medical records if available."
            ),
            HealthcareVisit(
                visitType = "Telehealth Visit",
                description = "A remote consultation via video call.",
                location = "N/A",
                phoneNumber = "+1 555-0145",
                doctorName = "Dr. Emily Wong",
                appointmentTime = "Friday, 1:00 PM",
                specialInstructions = "Ensure a stable internet connection."
            ),
            HealthcareVisit(
                visitType = "Emergency Visit",
                description = "Immediate care for urgent medical issues.",
                location = "City Hospital",
                phoneNumber = "+1 555-0199",
                doctorName = "Dr. Mark Johnson",
                appointmentTime = "Open 24/7",
                specialInstructions = "Bring ID and insurance information."
            ),
            HealthcareVisit(
                visitType = "Routine Check-Up",
                description = "Annual health examination.",
                location = "Downtown Health Center",
                phoneNumber = "+1 555-0167",
                doctorName = "Dr. Mary Wilson",
                appointmentTime = "Tuesday, 9:00 AM",
                specialInstructions = "Fasting may be required."
            ),
            HealthcareVisit(
                visitType = "Specialist Consultation",
                description = "Consultation with a specialist.",
                location = "Specialty Medical Center",
                phoneNumber = "+1 555-0188",
                doctorName = "Dr. Alice Brown",
                appointmentTime = "Thursday, 3:00 PM",
                specialInstructions = "Referral may be required."
            ),
            HealthcareVisit(
                visitType = "Physical Therapy",
                description = "Rehabilitation treatment for physical injuries.",
                location = "Health Plus Therapy Center",
                phoneNumber = "+1 555-0111",
                doctorName = "Dr. Chris Green",
                appointmentTime = "Monday, 11:00 AM",
                specialInstructions = "Wear comfortable clothing."
            ),
            HealthcareVisit(
                visitType = "Vaccination Appointment",
                description = "Receive vaccines as per schedule.",
                location = "Community Health Center",
                phoneNumber = "+1 555-0222",
                doctorName = "Nurse Amy Johnson",
                appointmentTime = "Thursday, 1:00 PM",
                specialInstructions = "Bring vaccination card."
            ),
            HealthcareVisit(
                visitType = "Dental Check-Up",
                description = "Routine examination of teeth and gums.",
                location = "Bright Smiles Dental Clinic",
                phoneNumber = "+1 555-0333",
                doctorName = "Dr. Rachel Adams",
                appointmentTime = "Friday, 10:00 AM",
                specialInstructions = "Floss before the appointment."
            ),
            HealthcareVisit(
                visitType = "Eye Examination",
                description = "Vision check and eye health assessment.",
                location = "Vision Care Center",
                phoneNumber = "+1 555-0444",
                doctorName = "Dr. Steven Clarke",
                appointmentTime = "Saturday, 2:00 PM",
                specialInstructions = "Bring your current glasses or contacts."
            ),
            HealthcareVisit(
                visitType = "Nutrition Counseling",
                description = "Consultation with a nutritionist for dietary advice.",
                location = "Healthy Choices Nutrition Clinic",
                phoneNumber = "+1 555-0555",
                doctorName = "Dietitian Laura Hill",
                appointmentTime = "Wednesday, 3:00 PM",
                specialInstructions = "Bring a food diary for review."
            ),
            HealthcareVisit(
                visitType = "Skin Consultation",
                description = "Assessment of skin conditions.",
                location = "Skin Health Clinic",
                phoneNumber = "+1 555-0666",
                doctorName = "Dr. Michelle Davis",
                appointmentTime = "Tuesday, 1:00 PM",
                specialInstructions = "Avoid using any skincare products before the visit."
            ),
            HealthcareVisit(
                visitType = "Mental Health Therapy",
                description = "Therapeutic sessions for mental health support.",
                location = "Mind Wellness Center",
                phoneNumber = "+1 555-0777",
                doctorName = "Therapist James Taylor",
                appointmentTime = "Monday, 4:00 PM",
                specialInstructions = "Arrive 10 minutes early."
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        healthcareVisitAdapter = HealthcareVisitAdapter(healthcareVisitList)
        recyclerView.adapter = healthcareVisitAdapter

        val backButton: ImageButton = findViewById(R.id.backButton)

        backButton.setOnClickListener {
            onBackPressed()
        }

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return false
            }

            private fun filter(text: String?) {
                val filteredList = healthcareVisitList.filter {
                    it.visitType.contains(text.toString(), ignoreCase = true) ||
                            it.description.contains(text.toString(), ignoreCase = true)
                }
                healthcareVisitAdapter.filterList(filteredList)
            }
        })
    }
}
