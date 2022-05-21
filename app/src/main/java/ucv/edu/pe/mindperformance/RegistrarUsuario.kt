package ucv.edu.pe.mindperformance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class RegistrarUsuario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)
        val correo = findViewById<TextView>(R.id.txt_email)
        val pwd = findViewById<TextView>(R.id.txt_password)
        val nom1 = findViewById<TextView>(R.id.txt_nombre1)
        val nom2 = findViewById<TextView>(R.id.txt_nombre2)
        val ape1 = findViewById<TextView>(R.id.txt_apellido1)
        val ape2 = findViewById<TextView>(R.id.txt_apellido2)
        val tlf = findViewById<TextView>(R.id.txt_telefono)
        val reg = findViewById<Button>(R.id.btn_registrarse)

        reg.setOnClickListener {
            // Run volley
            val url = "http://miguelgrau.atwebpages.com/R_usuario.php"
            // Post parameters
            // Form fields and values

            if(correo.text.toString().isNotEmpty()&&
                pwd.text.toString().isNotEmpty()&&
                nom1.text.toString().isNotEmpty()&&
                nom2.text.toString().isNotEmpty()&&
                ape1.text.toString().isNotEmpty()&&
                ape2.text.toString().isNotEmpty()&&
                tlf.text.toString().isNotEmpty()){
                val jsonObject = JSONObject()
                jsonObject.put("correo",correo.text.toString())
                jsonObject.put("password",pwd.text.toString())
                jsonObject.put("nom1",nom1.text.toString())
                jsonObject.put("nom2",nom2.text.toString())
                jsonObject.put("ape1",ape1.text.toString())
                jsonObject.put("ape2",ape2.text.toString())
                jsonObject.put("telefono",tlf.text.toString())
                Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_LONG).show()

                // Volley post request with parameters
                val request = JsonObjectRequest(Request.Method.POST,url,jsonObject,
                    { response ->
                        Toast.makeText(this, "Procede", Toast.LENGTH_LONG).show()
                        // Process the json
                        try {
                            val vmai = Intent(this, MainActivity::class.java)
                            startActivity(vmai)
                            Toast.makeText(this, "Registrado Correctamente", Toast.LENGTH_LONG).show()
                            Log.d("Response:", "$response")
                        }catch (e:Exception){
                            Log.d("Exception:", "$e")
                        }

                    }, {
                        // Error in request
                        Toast.makeText(this, "Volley Error : $it", Toast.LENGTH_LONG).show()
                    })


                // Volley request policy, only one time request to avoid duplicate transaction
                request.retryPolicy = DefaultRetryPolicy(
                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                    // 0 means no retry
                    0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
                    1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )

                // Add the volley post request to the request queue
                VolleySingleton.getInstance(this).addToRequestQueue(request)
            }else{
                Toast.makeText(this, "COMPLETE LOS CAMPOS", Toast.LENGTH_SHORT).show()
            }
        }
    }
}