package com.example.movilestp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.movilestp2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        binding.btConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.convertir(binding.etDolares, binding.etEuros, binding.rbDolarEuro,binding.rbEuroDolar);
            }
        });

        binding.rbDolarEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.etDolares.setEnabled(true);
                binding.etEuros.setEnabled(false);

            }
        });
        binding.rbEuroDolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.etDolares.setEnabled(false);
                binding.etEuros.setEnabled(true);

            }
        });
        vm.getMlSaldo().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                binding.etConvertido.setText(s);
            }
        });

    }
}