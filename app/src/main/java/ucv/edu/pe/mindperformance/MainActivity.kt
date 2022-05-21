package ucv.edu.pe.mindperformance


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MindPerformance)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val reg1 = findViewById<TextView>(R.id.btn_registrarnuevo)
        reg1.setOnClickListener {
            val regis = Intent(this, RegistrarUsuario::class.java)
            startActivity(regis)
        }
        val correo = findViewById<TextView>(R.id.txt_email)
        val pwd = findViewById<TextView>(R.id.txt_password)
        val log = findViewById<TextView>(R.id.btn_login)

        log.setOnClickListener {
            // Run volley
            val url = "http://miguelgrau.atwebpages.com/L_usuario.php"
            // Post parameters
            // Form fields and values

            if(correo.text.toString().isNotEmpty()&&
                pwd.text.toString().isNotEmpty()){
                val jsonObject = JSONObject()
                jsonObject.put("correo",correo.text.toString())
                jsonObject.put("password",pwd.text.toString())
                Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_LONG).show()
                // Volley post request with parameters
                val request = JsonObjectRequest(
                    Request.Method.POST,url,jsonObject,
                    { response ->
                        Toast.makeText(this, "$response", Toast.LENGTH_LONG).show()
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

