package br.com.pimviii;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView tvUser = v.findViewById(R.id.tvUserEmail);
        SharedPreferences prefs = getActivity().getSharedPreferences(LoginActivity.PREFS, Context.MODE_PRIVATE);
        String user = prefs.getString(LoginActivity.KEY_USER, "Usuário");
        tvUser.setText(user);
        v.findViewById(R.id.btnLogout).setOnClickListener(view -> {
            prefs.edit().remove(LoginActivity.KEY_USER).apply();
            Toast.makeText(getContext(), "Desconectado", Toast.LENGTH_SHORT).show();
            // voltar ao Login
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        });
        return v;
    }
}
