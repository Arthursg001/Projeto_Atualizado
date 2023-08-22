package dev.samuel.teste.agenda.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import dev.samuel.teste.agenda.R;
import dev.samuel.teste.agenda.controller.EventoDAO;
import dev.samuel.teste.agenda.model.Evento;

/*
Autor: Juan Francisco Sánchez González
Fecha: 07/02/2023
Clase: Actividad que contiene la barra de menú lateral (NavigationDrawer). Para cargar el contenido de la activity
se utiliza un ViewFlipper.
*/

public class EventActivity extends AppCompatActivity {
    private final static int CONT_ACTIVIDAD = 1;
    private DrawerLayout drawerLayout;
    private NavigationView nav;
    private ViewFlipper vf;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Princiapal();

        toolbar();
    }

    private void toolbar() {
        iniciandoToolbar();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent sendIntent;
                if (item.getItemId() == R.id.nav_item_one) {
                    // Se inicia Actividad 1
                    sendIntent = new Intent(EventActivity.this, UserActivity.class);
                    startActivity(sendIntent);
                } else if (item.getItemId() == R.id.nav_item_two) {
                    // Se inicia Actividad 2
                    sendIntent = new Intent(EventActivity.this, EventActivity.class);
                    startActivity(sendIntent);
                } else if (item.getItemId() == R.id.nav_item_three) {
                    // Se inicia Actividad 3
                    sendIntent = new Intent(EventActivity.this, Mes_Activity.class);
                    startActivity(sendIntent);
                }

                // Close the navigation drawer when an item is selected
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void iniciandoToolbar() {
        toolbar = findViewById(R.id.toolbarEvent);
        vf = findViewById(R.id.vf);
        vf.setDisplayedChild(CONT_ACTIVIDAD);
        drawerLayout = findViewById(R.id.drawer_layout);
        nav = findViewById(R.id.nav_view);
    }

    private void Princiapal() {
        EventoDAO dao = new EventoDAO();

        EditText tituloEvent = findViewById(R.id.edit_nome_evento);
        EditText descricaoEvent = findViewById(R.id.edit_descricao_evento);
        Button salvar = findViewById(R.id.bt_Salvar);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EventActivity.this, "Salvar", Toast.LENGTH_SHORT).show();

                String nome = tituloEvent.getText().toString();
                String descricao = descricaoEvent.getText().toString();

                Evento criarEvento = new Evento(nome,descricao);

                dao.salva(criarEvento);

                finish();

            }
        });
    }
}