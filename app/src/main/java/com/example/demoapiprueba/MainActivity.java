package com.example.demoapiprueba;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private List<Post> listPost;
    private PostAdapter adapter;
    private ListView listViewPost;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listPost = new ArrayList<Post>();

        // getPosts();

        adapter = new PostAdapter(listPost);

        listViewPost = findViewById(R.id.listViewPost);
        listViewPost.setAdapter(adapter);

        swipeRefreshLayout = findViewById(R.id.swipePost);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void getPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostService postService = retrofit.create(PostService.class);
        Call< List<Post> > call = postService.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                for(Post post :  response.body()) {
                    listPost.add(post);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });
    }


    @Override
    public void onRefresh() {
        getPosts();
        swipeRefreshLayout.setRefreshing(false);
    }
}
