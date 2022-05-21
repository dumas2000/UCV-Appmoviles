package ucv.edu.pe.mindperformance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView


class CursoMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homecursodoc)
        val regresar = findViewById<ImageView>(R.id.nav_header_back)
        regresar.setOnClickListener{
            val btnregresar = Intent(this, DrawerMainActivity::class.java)
            startActivity(btnregresar)
        }
    }

}