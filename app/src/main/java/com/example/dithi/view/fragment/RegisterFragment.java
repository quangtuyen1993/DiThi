package com.example.dithi.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dithi.R;
import com.example.dithi.model.User;
import com.example.dithi.service.ApiService;
import com.example.dithi.service.UserApi;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFragment extends Fragment {
    /*
     TODO: Rename parameter arguments, choose names that match
     the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    */


    private UserApi userApi;
    private Button bt_login, bt_register;
    private EditText et_email, et_name, et_password;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        userApi = ApiService.getInstance().create(UserApi.class);
        bt_login = view.findViewById(R.id.bt_login);
        bt_register = view.findViewById(R.id.bt_register);
        et_name = view.findViewById(R.id.et_name);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);

        bt_login.setOnClickListener(v -> NavHostFragment.findNavController(RegisterFragment.this).popBackStack());
        bt_register.setOnClickListener(this::onRegister);
    }

    private void onRegister(View view) {
        String name = et_name.getText().toString();
        String email = et_email.getText().toString();
        String pass = et_password.getText().toString();

        userApi.onRegister(email, pass, name).enqueue(new Callback<User>() {
            String message = "";

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    message = " Hello " + user.getName();
                } else {
                    message = "Error";

                }
                Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    ;

}
