package com.example.rvtraining

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HealthcareVisitAdapter(private var visitList: List<HealthcareVisit>) :
    RecyclerView.Adapter<HealthcareVisitAdapter.VisitViewHolder>() {

    // ViewHolder Class
    class VisitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val visitType: TextView = itemView.findViewById(R.id.visitType)
        val description: TextView = itemView.findViewById(R.id.description)
        val location: TextView = itemView.findViewById(R.id.location)
        val phoneNumber: TextView = itemView.findViewById(R.id.phoneNumber)
        // Removed backButton from ViewHolder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_healthcare_visit, parent, false)
        return VisitViewHolder(view)
    }

    override fun onBindViewHolder(holder: VisitViewHolder, position: Int) {
        val visit = visitList[position]
        holder.visitType.text = visit.visitType
        holder.description.text = visit.description
        holder.location.text = "Location: ${visit.location}"
        holder.phoneNumber.text = "Phone: ${visit.phoneNumber}"

        // Set item click listener for the item view
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, VisitDetailsActivity::class.java).apply {
                putExtra("VISIT_TYPE", visit.visitType)
                putExtra("DESCRIPTION", visit.description)
                putExtra("LOCATION", visit.location)
                putExtra("PHONE", visit.phoneNumber)
                putExtra("DOCTOR_NAME", visit.doctorName) // Add this field if it exists
                putExtra("APPOINTMENT_TIME", visit.appointmentTime) // Add this field if it exists
                putExtra("SPECIAL_INSTRUCTIONS", visit.specialInstructions) // Add this field if it exists
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return visitList.size
    }

    // Filter method for search functionality
    fun filterList(filteredList: List<HealthcareVisit>) {
        visitList = filteredList
        notifyDataSetChanged()
    }
}
