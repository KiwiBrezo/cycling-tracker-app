package si.um.feri.cycling_tracker_app.activites

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import si.um.feri.cycling_tracker_app.R


class LoginActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    private val jsonMediaType: MediaType? = MediaType.parse("application/json; charset=utf-8")

    private lateinit var loginBtn : Button
    private lateinit var usernameInput : EditText
    private lateinit var passwordInput : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn = findViewById(R.id.login_btn)
        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)

        bindComponents()
    }

    private fun bindComponents() {
        loginBtn.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val userneme = this.usernameInput.text.toString()
        val password = this.passwordInput.text.toString()

        if (userneme.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "There is no username or password", Toast.LENGTH_SHORT).show()
        }

        val jsonObject = JSONObject()
        try {
            jsonObject.put("username", userneme)
            jsonObject.put("password", password)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val body = RequestBody.create(jsonMediaType, jsonObject.toString())

        val request = Request.Builder()
            .url("http://10.0.2.2:8080/api/v1/login")
            .post(body)
            .build()

        client.newCall(request).execute().use { response ->
            if (response.code() != 200) {
                Toast.makeText(this, "Failed to log into the system!", Toast.LENGTH_SHORT).show()
                return
            }

            println(response.body()!!.string())
        }
    }
}