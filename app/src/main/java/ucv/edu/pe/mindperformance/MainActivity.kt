package ucv.edu.pe.mindperformance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MindPerformance)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val ventana = findViewById<Button>(R.id.btn_login)
        ventana.setOnClickListener{
            val ventana1 = Intent(this, DrawerMainActivity::class.java)
            startActivity(ventana1)
        }*/
        setup()
    }

    private fun setup() {
        title = "Authentication"
        val email = findViewById<TextView>(R.id.txt_email)
        val password = findViewById<TextView>(R.id.txt_password)
        val reg = findViewById<TextView>(R.id.btn_registrarse)
        reg.setOnClickListener {
            if (email.text.isNotEmpty() && password.text.isNotEmpty()){
                FirebaseAuth.getInstance().
                createUserWithEmailAndPassword(
                    email.text.toString(),
                    password.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful){
                            showHome(it.result?.user?.email?:"", ProviderType.BASIC)
                        }else{
                            alert()
                        }
                }
            }else{
                vacios()
            }
        }

        val login = findViewById<Button>(R.id.btn_login)
        login.setOnClickListener {
            if (email.text.isNotEmpty() && password.text.isNotEmpty()){
                FirebaseAuth.getInstance().
                signInWithEmailAndPassword(
                    email.text.toString(),
                    password.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email?:"", ProviderType.BASIC)
                    }else{
                        alert()
                    }
                }
            }else{
                vacios()
            }
        }
    }

    private fun alert(){
        val error = Toast.makeText(this,"Se ha producido un error autenticando el usuario", Toast.LENGTH_LONG )
        error.show()
    }

    private fun vacios(){
        val vacio = Toast.makeText(this,"Rellene los campos", Toast.LENGTH_LONG )
        vacio.show()
    }

    private fun showHome(email:String,providerType: ProviderType){
        val v1 = Intent(this, DrawerMainActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", providerType)
        }
        startActivity(v1)
    }


}