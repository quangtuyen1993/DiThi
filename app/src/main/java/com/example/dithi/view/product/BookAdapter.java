package com.example.dithi.view.product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dithi.R;
import com.example.dithi.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    List<Book> list = new ArrayList<>();

    public void setList(List<Book> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_type, tv_author, tv_des;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_author = itemView.findViewById(R.id.tv_author);
            tv_des = itemView.findViewById(R.id.tv_des);
        }
        public void bind(Book book){
            tv_name.setText(book.getName());
            tv_author.setText(book.getAuthor());
            tv_des.setText(book.getDes());
            tv_type.setText(book.getType());
        }
    }

    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
            Book book=list.get(position);
            holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
