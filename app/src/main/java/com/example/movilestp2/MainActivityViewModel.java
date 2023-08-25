package com.example.movilestp2;

import android.app.Application;
import android.content.Context;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private String saldo;
    private MutableLiveData<String> mlSaldo;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
    }

    public MutableLiveData<String> getMlSaldo() {
        if (mlSaldo == null) {

           mlSaldo = new MutableLiveData<String>();
        }
        return mlSaldo;
    }

    //1 Dolar estadounidense =	0,9205 Euros
    //1 Euro =	1,0863 Dolares
    public void convertir(EditText dolar, EditText euro, RadioButton rdDolar, RadioButton rdEuro) {
        if (!rdDolar.isChecked() & !rdEuro.isChecked()) {
            Toast.makeText(context, "Debe seleccionar una opción", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (rdDolar.isChecked()) {

                if (dolar.getText().toString().isEmpty()){
                    Toast.makeText(context, "Debe ingresar un monto en dolares", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    try {
                        Double montoDolar = (Double) Double.parseDouble(String.valueOf(dolar.getText()));
                        montoDolar = montoDolar * 0.93;
                        mlSaldo.setValue("€" + montoDolar + " euros.");
                        return;
                    } catch (NumberFormatException e) {
                        Toast.makeText(context, "Error al convertir", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                if (rdEuro.isChecked()) {

                    if (euro.getText().toString().isEmpty()) {
                        Toast.makeText(context, "Debe ingresar un monto en euros", Toast.LENGTH_SHORT).show();
                        return;
                    } else {

                        try {
                            Double montoEuro = (Double) Double.parseDouble(String.valueOf(euro.getText()));
                            montoEuro = montoEuro * 1.08;
                            mlSaldo.setValue("$" + montoEuro + " dolares.");
                            return;
                        } catch (NumberFormatException e) {
                            Toast.makeText(context, "Error al convertir", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }

}
