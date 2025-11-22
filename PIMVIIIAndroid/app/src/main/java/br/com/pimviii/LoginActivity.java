package br.com.pimviii;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    public static final String PREFS = "pim_prefs";
    public static final String KEY_USER = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences prefs = getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        // Se já existe usuário salvo, pula login
        String savedUser = prefs.getString(KEY_USER, null);
        if (savedUser != null) {
            startMain();
            return;
        }

        // Campos de email e senha
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtPassword = findViewById(R.id.edtPassword);

        // Botões principais
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnCreate = findViewById(R.id.btnCreateAccount);

        // Botões de redes sociais
        Button btnGoogle = findViewById(R.id.btnGoogle);
        Button btnFacebook = findViewById(R.id.btnFacebook);
        Button btnX = findViewById(R.id.btnX);

        // Login padrão
        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String pass = edtPassword.getText().toString().trim();

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Informe e-mail e senha.", Toast.LENGTH_SHORT).show();
                return;
            }

            prefs.edit().putString(KEY_USER, email).apply();
            Toast.makeText(this, "Login realizado!", Toast.LENGTH_SHORT).show();
            startMain();
        });

        // Criar conta
        btnCreate.setOnClickListener(v -> showCreateAccountDialog());

        // Redes sociais (simuladas)
        btnGoogle.setOnClickListener(v -> socialLogin("Google"));
        btnFacebook.setOnClickListener(v -> socialLogin("Facebook"));
        btnX.setOnClickListener(v -> socialLogin("X"));
    }

    private void socialLogin(String provider) {
        SharedPreferences prefs = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        String fakeUser = provider.toLowerCase() + "_user@example.com";

        prefs.edit().putString(KEY_USER, fakeUser).apply();

        Toast.makeText(this,
                "Autenticação via " + provider + " realizada!",
                Toast.LENGTH_SHORT).show();

        startMain();
    }

    private void showCreateAccountDialog() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Criar conta");

        final EditText input = new EditText(this);
        input.setHint("email@exemplo.com");
        b.setView(input);

        b.setPositiveButton("Criar", (dialog, which) -> {
            String email = input.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(this, "Digite um e-mail válido.", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs =
                    getSharedPreferences(PREFS, Context.MODE_PRIVATE);

            prefs.edit().putString(KEY_USER, email).apply();

            Toast.makeText(this,
                    "Conta criada com sucesso!",
                    Toast.LENGTH_SHORT).show();

            startMain();
        });

        b.setNegativeButton("Cancelar", null);
        b.show();
    }

    private void startMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
