package com.example.dithi.view.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.dithi.R;
import com.example.dithi.model.Book;
import com.example.dithi.service.ApiService;
import com.example.dithi.service.BookApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookActivity extends AppCompatActivity {

    BookApi bookApi;
    RecyclerView rv;
    BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        init();

    }

    public void init() {
        rv = findViewById(R.id.rv_book);
        bookApi = ApiService.getInstance().create(BookApi.class);
        bookAdapter = new BookAdapter();
        rv.setAdapter(bookAdapter);
        RecyclerView.ItemDecoration itemDecoration=new DividerItemDecoration(this,RecyclerView.VERTICAL);
        rv.addItemDecoration(itemDecoration);
        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        onFetch();
    }

    public void onFetch() {
        bookApi.fetchAllBook().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful()) {
                    Log.e("Main",response.body().toString());
                    bookAdapter.setList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                    Log.e("Main",t.getMessage());
            }
        });
    }
}
