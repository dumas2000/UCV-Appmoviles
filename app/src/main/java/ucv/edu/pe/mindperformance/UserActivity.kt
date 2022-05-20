package ucv.edu.pe.mindperformance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)


        val regresar = findViewById<ImageView>(R.id.nav_header_user)
        regresar.setOnClickListener{
            val btnregresar = Intent(this, DrawerMainActivity::class.java)
            startActivity(btnregresar)
        }

        val snpsexo = findViewById<Spinner>(R.id.spinner_sexo)

        ArrayAdapter.createFromResource(
            this,
            R.array.sexo_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            snpsexo.adapter = adapter
        }

    }
}