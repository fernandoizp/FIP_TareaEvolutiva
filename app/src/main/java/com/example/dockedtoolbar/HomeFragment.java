package com.example.dockedtoolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText nameEditText = view.findViewById(R.id.name_edittext);
        Button submitButton = view.findViewById(R.id.submit_button);
        ImageButton likeButton = view.findViewById(R.id.like_button);
        TextView seeProductLink = view.findViewById(R.id.tv_see_product);

        submitButton.setOnClickListener(v -> {
            String nombre = nameEditText.getText().toString();
            if (nombre.isEmpty()) {
                Toast.makeText(getContext(), "Por favor, introduce tu nombre", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "¡Formulario enviado para " + nombre + "!", Toast.LENGTH_SHORT).show();
            }
        });

        likeButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "¡Gracias por tu apoyo!", Toast.LENGTH_SHORT).show();
        });

        seeProductLink.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navigateTo(new ProductFragment());
            }
        });
    }
}
