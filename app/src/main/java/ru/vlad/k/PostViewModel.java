package ru.vlad.k;

import android.app.Application;
import android.content.Context;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;

import java.util.List;

import ru.vlad.k.db.Post;
import ru.vlad.k.db.PostDao;
import ru.vlad.k.db.PostsDatabase;


@EBean
public class PostViewModel extends AndroidViewModel {

    private PostDao postDao;

    public PostViewModel(@NonNull Context ctx) {
        super((Application) ctx.getApplicationContext());
        postDao = PostsDatabase.getInstance(ctx).postDao();
    }

    LiveData<List<Post>> getAllPosts() {
        return postDao.findAll();
    }

    @Background
    void savePost(Post post) {
        postDao.save(post);
    }

    @Background
    void deletePost(Post post) {
        postDao.delete(post);
    }
}
