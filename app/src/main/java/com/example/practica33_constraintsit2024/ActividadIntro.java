package com.example.practica33_constraintsit2024;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActividadIntro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Habilitar EdgeToEdge y configurar el diseño principal
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actividad_intro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar elementos de la interfaz
        TextView txtCorreo = findViewById(R.id.txtemail);
        TextView txtFecha = findViewById(R.id.txtfecha);
        TextView txtCedula = findViewById(R.id.id);
        TextView txtNombres = findViewById(R.id.nombre);
        TextView txtCiudad = findViewById(R.id.txtcity);
        TextView txtTelefono = findViewById(R.id.txtphon);
        TextView txtGenero = findViewById(R.id.txtgenre);

        // Obtener datos enviados desde ActividadRegistroUsuario
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            txtCorreo.setText(extras.getString("correo", "Sin dato"));
            txtFecha.setText(extras.getString("fecha", "Sin dato"));
            txtCedula.setText(extras.getString("cedula", "Sin dato"));
            txtNombres.setText(extras.getString("nombres", "Sin dato"));
            txtCiudad.setText(extras.getString("ciudad", "Sin dato"));
            txtTelefono.setText(extras.getString("telefono", "Sin dato"));
            txtGenero.setText(extras.getString("genero", "Sin dato"));
        }
    }
}
