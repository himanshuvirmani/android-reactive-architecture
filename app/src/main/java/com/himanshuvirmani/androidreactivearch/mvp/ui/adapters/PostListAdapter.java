package com.himanshuvirmani.androidreactivearch.mvp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.himanshuvirmani.androidreactivearch.R;
import com.himanshuvirmani.androidreactivearch.data.entity.Post;
import java.util.List;

/**
 * Created by john.francis on 09/02/16.
 */
public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostViewHolder> {

  private List<Post> postList;
  private PostInteractionListner listner;

  public PostListAdapter(List<Post> postList,PostInteractionListner listner) {
    this.postList = postList;
    this.listner =listner;
  }

  public interface PostInteractionListner{
    void postClicked(int id);
  }

  @Override public int getItemCount() {
    return postList.size();
  }

  @Override public PostViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    View itemView = LayoutInflater.
        from(viewGroup.getContext()).
        inflate(R.layout.post_item, viewGroup, false);


    return new PostViewHolder(itemView);
  }

  @Override public void onBindViewHolder(PostViewHolder postViewHolder, final int position) {
    Post post = postList.get(position);
    postViewHolder.title.setText("Title:" + post.getTitle());
    postViewHolder.body.setText("" + post.getBody());
    postViewHolder.id.setText("" + post.getId());

    postViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (listner != null) {
          listner.postClicked(postList.get(position).getId());
        }
      }
    });

  }

  public Post getItem(int position) {
    return postList.get(position);
  }

  public static class PostViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.post_title) protected TextView title;
    @Bind(R.id.post_body) protected TextView body;
    @Bind(R.id.post_id) protected TextView id;

    public PostViewHolder(View v) {
      super(v);
      ButterKnife.bind(this,v);
    }

  }
}
