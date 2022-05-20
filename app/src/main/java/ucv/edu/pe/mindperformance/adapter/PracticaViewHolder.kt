package ucv.edu.pe.mindperformance.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ucv.edu.pe.mindperformance.Practica
import ucv.edu.pe.mindperformance.R

class PracticaViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val photo = view.findViewById<ImageView>(R.id.imgcurso)
    val curso = view.findViewById<TextView>(R.id.txtcurso)
    val tema = view.findViewById<TextView>(R.id.txttema)

    fun render(practicamodel: Practica){
        curso.text = practicamodel.nomcurso
        tema.text = practicamodel.tema
        Glide.with(photo.context).load(practicamodel.photo).into(photo)
            //.with(photo.context).load(practicamodel).into(photo)
    }
}