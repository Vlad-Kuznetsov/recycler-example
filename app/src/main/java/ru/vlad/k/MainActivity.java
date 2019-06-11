package ru.vlad.k;

import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import ru.vlad.k.db.Post;


@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main_menu)
public class MainActivity extends AppCompatActivity implements PostsAdapter.OnDeleteButtonClickListener {

    private PostsAdapter postsAdapter;

    @Bean
    protected PostViewModel postViewModel;

    @ViewById
    protected RecyclerView rvPostsLis;

    @ViewById
    protected Toolbar toolbar;

    @AfterViews
    public void initViews() {

        setSupportActionBar(toolbar);
        postsAdapter = new PostsAdapter(this, this);

        postViewModel = ViewModelProviderUtils.registrateModel(this, PostViewModel.class, postViewModel);

        postViewModel.getAllPosts().observe(this, posts -> postsAdapter.setData(posts));

        rvPostsLis.setLayoutManager(new LinearLayoutManager(this));
        rvPostsLis.setHasFixedSize(true);
        rvPostsLis.setItemAnimator(new DefaultItemAnimator());
        rvPostsLis.setAdapter(postsAdapter);
    }




    @OptionsItem
    public void addPost(MenuItem item) {
        postViewModel.savePost(new Post("This is a post title", "This is a post content"));
    }

    @Override
    public void onDeleteButtonClicked(Post post) {
        postViewModel.deletePost(post);
    }
}
