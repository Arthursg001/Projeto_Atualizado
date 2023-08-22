package dev.samuel.teste.agenda.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import dev.samuel.teste.agenda.R;

public class LoginActivity extends AppCompatActivity {

    private Button bt_acessar;
    private TextView text_telaCadrastro;
    private EditText editEmail, editSenha;
    private ProgressBar progressBar;

    String[] mensagens = {"Preencha todos os campos", "Login Realizado com sucesso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        IniciarComponentes();
        text_telaCadrastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });

        bt_acessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String senha = editSenha.getText().toString();

                fecharTeclado();

                if (email.isEmpty() || senha.isEmpty()) {

                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                } else {
                    AutenticarUsuario(v);
                }
            }
        });
    }

    private void AutenticarUsuario(View view) {

        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaPrinciapl();

                        }
                    }, 3000);

                } else {
                    String erro;
                    try {
                        throw task.getException();

                    } catch (FirebaseAuthWeakPasswordException e) {

                        erro = "Digite uma senha com 6 caracteres";

                    } catch (FirebaseAuthInvalidCredentialsException e) {

                        erro = "Email invalido";

                    } catch (Exception e) {

                        erro = "Erro ao logar usuário";

                    }
                    Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    private void fecharTeclado(){
        View view = this.getCurrentFocus();
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();

        if (usuarioAtual != null) {
            TelaPrinciapl();

        }
    }

    private void TelaPrinciapl() {
        Intent intent = new Intent(LoginActivity.this,UserActivity.class);
        startActivity(intent);
        finish();
    }

    private void IniciarComponentes() {
        editEmail = findViewById(R.id.edit_email);
        editSenha = findViewById(R.id.edit_senha);
        bt_acessar = findViewById(R.id.bt_acessar);
        progressBar = findViewById(R.id.progresseBar);
        text_telaCadrastro = findViewById(R.id.text_tela_cadrastro);

    }
}