package com.example.practica33_constraintsit2024;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.view.View;
import java.util.Set;
import java.util.HashSet;

import java.util.ArrayList;
import java.util.Collections;

public class ActividadIntro extends AppCompatActivity {
    private Spinner spinnerCategoria;
    private Spinner spinnerSubcategoria;
    private RecyclerView recyclerView;
    private List<LugarTuristico> lugaresCompletos;
    private ArrayAdapter<String> categoriaAdapter;
    private ArrayAdapter<String> subcategoriaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_intro);

        spinnerCategoria = findViewById(R.id.cbListaCategorias);
        spinnerSubcategoria = findViewById(R.id.item2);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Cargar datos de la API
        cargarDatos();

        // Configurar listeners de los spinners
        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String categoriaSeleccionada = parent.getItemAtPosition(position).toString();
                actualizarSubcategorias(categoriaSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerSubcategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtrarLugares();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void cargarDatos() {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        apiService.getLugaresTuristicos().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    lugaresCompletos = response.body().getData();
                    Set<String> categorias = new HashSet<>();

                    // Obtener categorías únicas
                    for (LugarTuristico lugar : lugaresCompletos) {
                        categorias.add(lugar.getCategoria());
                    }

                    // Configurar adapter del spinner de categorías
                    List<String> listaCategorias = new ArrayList<>(categorias);
                    Collections.sort(listaCategorias);
                    categoriaAdapter = new ArrayAdapter<>(
                            ActividadIntro.this,
                            android.R.layout.simple_spinner_item,
                            listaCategorias
                    );
                    categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCategoria.setAdapter(categoriaAdapter);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(ActividadIntro.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void actualizarSubcategorias(String categoriaSeleccionada) {
        Set<String> subcategorias = new HashSet<>();

        // Filtrar subcategorías según la categoría seleccionada
        for (LugarTuristico lugar : lugaresCompletos) {
            if (lugar.getCategoria().equals(categoriaSeleccionada)) {
                subcategorias.add(lugar.getSubcategoria());
            }
        }

        List<String> listaSubcategorias = new ArrayList<>(subcategorias);
        Collections.sort(listaSubcategorias);
        subcategoriaAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                listaSubcategorias
        );
        subcategoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubcategoria.setAdapter(subcategoriaAdapter);
    }

    private void filtrarLugares() {
        String categoriaSeleccionada = spinnerCategoria.getSelectedItem().toString();
        String subcategoriaSeleccionada = spinnerSubcategoria.getSelectedItem().toString();

        List<LugarTuristico> lugaresFiltrados = new ArrayList<>();
        for (LugarTuristico lugar : lugaresCompletos) {
            if (lugar.getCategoria().equals(categoriaSeleccionada) &&
                    lugar.getSubcategoria().equals(subcategoriaSeleccionada)) {
                lugaresFiltrados.add(lugar);
            }
        }

        recyclerView.setAdapter(new LugarAdapter(lugaresFiltrados));
    }
}


