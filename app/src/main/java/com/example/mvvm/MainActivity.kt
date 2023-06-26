package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Instancia da ViewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Observadores para ver a mudanção do campo (Gerenciar o estado)
        setObserver()

        binding.buttonLogin.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_login) {
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()

            viewModel.doLogin(email, password)
        }
    }

    private fun setObserver() {
        viewModel.welcome().observe(this, Observer {
            binding.textHello.text = it
        })
        viewModel.login().observe(this, Observer {
            if(it){
                Toast.makeText(this,"Sucesso!",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Falha!",Toast.LENGTH_LONG).show()
            }
        })
    }
}