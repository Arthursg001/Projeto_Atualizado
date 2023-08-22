package dev.samuel.teste.agenda.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import dev.samuel.teste.agenda.R;
import dev.samuel.teste.agenda.controller.EventoDAO;

/*
Autor: Juan Francisco Sánchez González
Fecha: 07/02/2023
Clase: Actividad que contiene la barra de menú lateral (NavigationDrawer). Para cargar el contenido de la activity
se utiliza un ViewFlipper.
*/

public class Mes_Activity extends AppCompatActivity {
    private final static int CONT_ACTIVIDAD = 2;
    private DrawerLayout drawerLayout;
    private NavigationView nav;
    private ViewFlipper vf;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar();

        Button novoEvento = findViewById(R.id.bt_CriarEvento);

        novoEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Mes_Activity.this,
                        EventActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventoDAO dao = new EventoDAO();

        ListView listaEventos = findViewById(R.id.lista);

        listaEventos.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, dao.todos()

        ));
    }

    private void toolbar() {
        toolbar = findViewById(R.id.toolbarMes);

        // Componente ViewFlipper
        vf = (ViewFlipper)findViewById(R.id.vf);
        vf.setDisplayedChild(CONT_ACTIVIDAD);

        // Componente NavigationDrawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav = (NavigationView) findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent sendIntent;
                if (item.getItemId() == R.id.nav_item_one) {
                    // Se inicia Actividad 1
                    sendIntent = new Intent(Mes_Activity.this, UserActivity.class);
                    startActivity(sendIntent);
                } else if (item.getItemId() == R.id.nav_item_two) {
                    // Se inicia Actividad 2
                    sendIntent = new Intent(Mes_Activity.this, EventActivity.class);
                    startActivity(sendIntent);
                } else if (item.getItemId() == R.id.nav_item_three) {
                    // Se inicia Actividad 3
                    sendIntent = new Intent(Mes_Activity.this, Mes_Activity.class);
                    startActivity(sendIntent);
                }

                // Close the navigation drawer when an item is selected
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
}