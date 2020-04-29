package com.example.dithi.view.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dithi.R;
import com.example.dithi.model.Product;
import com.example.dithi.service.ApiService;
import com.example.dithi.service.ProductApi;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerActivity extends AppCompatActivity {
    ProductApi productApi;
    ProductAdapter productAdapter;
    RecyclerView recyclerView;
    Button bt_save;
    EditText et_name, et_des, et_type, et_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        productApi = ApiService.getInstance().create(ProductApi.class);

        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.rv);
        et_name = findViewById(R.id.et_name);
        et_des = findViewById(R.id.et_des);
        et_price = findViewById(R.id.et_price);
        et_type = findViewById(R.id.et_type);
        bt_save = findViewById(R.id.bt_save);
        bt_save.setOnClickListener(this::onInsert);

        //init rec
        productAdapter = new ProductAdapter();
        recyclerView.setAdapter(productAdapter);
        RecyclerView.ItemDecoration itemDecoration=new DividerItemDecoration(this,RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        onFetchList();
    }


    public void onFetchList() {
        productApi.onFetchProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> products = response.body();
                    productAdapter.setProductList(products);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ManagerActivity.this, "Faild Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cleatText() {
        et_name.setText("");
        et_price.setText("");
        et_type.setText("");
        et_des.setText("");
    }

    private void onInsert(View view) {
        String name = et_name.getText().toString();
        String des = et_des.getText().toString();
        String type = et_type.getText().toString();
        String price = et_price.getText().toString();

        productApi.onInsert(name, des, type, price).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    List<Product> list = productAdapter.getProductList();
                    list.add(response.body());
                    productAdapter.setProductList(list);
                    cleatText();
                    Toast.makeText(ManagerActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(ManagerActivity.this, "Failed Internet", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
