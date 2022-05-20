package ucv.edu.pe.mindperformance.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ucv.edu.pe.mindperformance.Practica
import ucv.edu.pe.mindperformance.R

class PracticaAdapter(private val practicaList:List<Practica>) : RecyclerView.Adapter<PracticaViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PracticaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PracticaViewHolder(layoutInflater.inflate(R.layout.item_practica,parent,false))
    }

    override fun onBindViewHolder(holder: PracticaViewHolder, position: Int) {
        val item = practicaList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = practicaList.size

}