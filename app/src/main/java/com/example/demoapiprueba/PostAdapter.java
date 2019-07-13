package com.example.demoapiprueba;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends BaseAdapter {

    private List<Post> listaPost;

    public PostAdapter(List<Post> listaPost) {
        this.listaPost = listaPost;
    }

    @Override
    public int getCount() {
        return listaPost.size();
    }

    @Override
    public Post getItem(int position) {
        return listaPost.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaPost.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        if(convertView==null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_post,null);
        }else{
            view = convertView;
        }

        Post post = this.getItem(position);

        TextView textViewTitle = view.findViewById(R.id.textViewTitle);
        TextView textViewBody = view.findViewById(R.id.textViewBody);

        textViewTitle.setText(post.getTitle());
        textViewBody.setText(post.getBody());

        return view;
    }
}
