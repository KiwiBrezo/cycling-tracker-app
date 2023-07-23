package si.um.feri.cycling_tracker_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import si.um.feri.cycling_tracker_app.R
import si.um.feri.cycling_tracker_app.adapters.holder.RideCardData

class RideCardAdapter(private val mList: List<RideCardData>) : RecyclerView.Adapter<RideCardAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.voznja_name_textview)
        val dateTextView: TextView = itemView.findViewById(R.id.date_of_voznja_textview)
        val durationTextView: TextView = itemView.findViewById(R.id.duration_of_voznja_textview)
    }

    interface OnClickListener {
        fun onClick(position: Int, model: RideCardData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ride_card_view, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rideCardModel = mList[position]

        holder.nameTextView.text = rideCardModel.rideNameText
        holder.durationTextView.text = rideCardModel.durationOfRide
        holder.dateTextView.text = rideCardModel.dateOfRide

        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, rideCardModel)
            }
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
}