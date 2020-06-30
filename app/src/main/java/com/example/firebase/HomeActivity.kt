package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*

enum class ProviderType {
    BASIC
 }
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        // setup
        val bundle:Bundle?=intent.extras
        val email:String?=bundle?.getString("email")
       setupo()
       /* val bundle:Bundle?=intent.extras
        val email:String?=bundle?.getString("email")
        val provider:String?=bundle?.getString("provider")
        setupe(email: email ?:"" , provider: provider ?:"" ) */
    }
    private fun setupo() {
        emailTextView.text = "Entraste satisfactoriamente "
        providerTextView.text = "Ahora tus datos estan registrados"
        closebutton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
    }}
   /* private fun setupe(email: String, provider:String ){
        title= "Inicio"
        emailTextView.text = email
        providerTextView.text = provider
        closebutton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    } */

}
