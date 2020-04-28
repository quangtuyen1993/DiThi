package com.example.dithi.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dithi.R;
import com.example.dithi.model.User;
import com.example.dithi.service.ApiService;
import com.example.dithi.service.UserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private Button bt_register, bt_login;
    private EditText et_email, et_password;
    private UserApi userApi;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);


    }

    private void init(View view) {
        userApi = ApiService.getInstance().create(UserApi.class);
        bt_register = view.findViewById(R.id.bt_register);
        bt_login = view.findViewById(R.id.bt_login);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);
        bt_register.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registerFragment));
        bt_login.setOnClickListener(this::onLogin);
    }

    private void onLogin(View view) {
        String email = et_email.getText().toString();
        String pass = et_password.getText().toString();
        userApi.onLogin(email, pass).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String message = "";
                if (response.isSuccessful()) {
                    User user = response.body();
                    assert user != null;
                    message = "Hello" + user.getEmail();
                } else {
                    message = "Email or password incorrect";
                }
                Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
