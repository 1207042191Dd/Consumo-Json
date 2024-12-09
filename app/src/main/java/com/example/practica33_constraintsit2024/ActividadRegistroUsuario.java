package com.example.practica33_constraintsit2024;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActividadRegistroUsuario extends AppCompatActivity {

    private EditText txtCorreo, txtFechaNac, txtCedula, txtNombres, txtCiudad, txtTelefono;
    private RadioButton radioButtonMasculino, radioButtonFemenino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Habilitar EdgeToEdge y configurar el diseño principal
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actividad_registro_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar elementos de la interfaz
        txtCorreo = findViewById(R.id.txtcorreo);
        txtFechaNac = findViewById(R.id.txtFechaNac);
        radioButtonMasculino = findViewById(R.id.radioButton);
        radioButtonFemenino = findViewById(R.id.radioButton2);
        txtCedula = findViewById(R.id.txtNombre);
        txtNombres = findViewById(R.id.txtApellido);
        txtCiudad = findViewById(R.id.txtciudad);
        txtTelefono = findViewById(R.id.txtPhone);


        // Botón para limpiar campos
        Button btnLimpiar = findViewById(R.id.btGrabar);

        btnLimpiar.setOnClickListener(v -> {
            // Mostrar un cuadro de confirmación
            new AlertDialog.Builder(this)
                    .setTitle("Confirmar")
                    .setMessage("¿Está seguro de que desea limpiar todo?")
                    .setPositiveButton("Sí", (dialog, which) -> {
                        limpiarCampos();
                        Toast.makeText(ActividadRegistroUsuario.this, "Campos limpios", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });


        Button btnGrabar = findViewById(R.id.btGrabar2);
        btnGrabar.setOnClickListener(v -> enviarDatos());
    }


    // Método para limpiar los campos
    private void limpiarCampos() {
        txtCorreo.setText("");
        txtFechaNac.setText("");
        txtCedula.setText("");
        txtNombres.setText("");
        txtCiudad.setText("");
        txtTelefono.setText("");
        radioButtonMasculino.setChecked(false);
        radioButtonFemenino.setChecked(false);
    }




private void enviarDatos() {
    Intent intent = new Intent(this, ActividadIntro.class);
    intent.putExtra("correo", txtCorreo.getText().toString());
    intent.putExtra("fecha", txtFechaNac.getText().toString());
    intent.putExtra("cedula", txtCedula.getText().toString());
    intent.putExtra("nombres", txtNombres.getText().toString());
    intent.putExtra("ciudad", txtCiudad.getText().toString());
    intent.putExtra("telefono", txtTelefono.getText().toString());



    String genero = radioButtonMasculino.isChecked() ? "Masculino" : (radioButtonFemenino.isChecked() ? "Femenino" : "No especificado");
    intent.putExtra("genero", genero);

    startActivity(intent);


}


}


