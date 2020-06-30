package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.singUppButton as singUppButton

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //  Splash
        Thread.sleep(2000) // HACK:
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Analycs Event
        val analytics :FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message","Integraciòn de Firebase completa")
        analytics.logEvent("InitScreen",bundle)
        // Setup
        setup()
    }
        private fun setup(){
            title = "Autenticación"
            singUppButton.setOnClickListener {
                if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.text.toString(),
                        passwordEditText.text.toString()).addOnCompleteListener {
                        if(it.isSuccessful){
                            ShowwHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                           showAlert()
                        }
                    }
                }
            }

                loginButton.setOnClickListener {
                    if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){
                        FirebaseAuth.getInstance()
                            .signInWithEmailAndPassword(emailEditText.text.toString(),
                            passwordEditText.text.toString()).addOnCompleteListener {
                            if(it.isSuccessful){
                                ShowwHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                            } else {
                                showAlert()
                            }
                        }
                    }
                }

        }
        private fun showAlert(){
        val builder =AlertDialog.Builder(this)
            builder.setTitle("Error")
            builder.setMessage("Se ha producido un error autenticando al usuario intente otra vez xd")
            builder.setPositiveButton("Aceptar", null)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        private fun ShowwHome(email: String, provider: ProviderType){
            val homeIntent = Intent(this, HomeActivity::class.java).apply {
                putExtra("email",email)
                putExtra("provider",provider.name)
            }
            startActivity(homeIntent)
        }
    }
