package com.example.android_final_work_0513;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<ArticleResponse> mDataset;

    private final ListItemClickListener mOnClickListener;

    public MyAdapter(ListItemClickListener listener) {
        mOnClickListener = listener;
    }

    public void setData(List<ArticleResponse> myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.my_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nickname;
        //public String avatarUrl;
        public TextView description;
        public TextView lickcount;

        private ImageView mImageView;

        public MyViewHolder(@NonNull View v) {
            super(v);
            nickname = v.findViewById(R.id.nickname);
            description = v.findViewById(R.id.description);
            lickcount = v.findViewById(R.id.likecount);
            mImageView = v.findViewById(R.id.avatar);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            String videoUrl = mDataset.get(clickedPosition).feedurl;
            String setName = mDataset.get(clickedPosition).nickname;
            String setLikeCount = mDataset.get(clickedPosition).likecount;
            String setDescription = mDataset.get(clickedPosition).description;

            if (mOnClickListener != null) {
                mOnClickListener.onListItemClick(String.valueOf(clickedPosition), videoUrl, setName, setLikeCount, setDescription);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d("adapter", "onBindViewHolder: #" + position);
        //    numberViewHolder.bind(position);
        holder.nickname.setText(mDataset.get(position).nickname);

        Glide.with(holder.mImageView.getContext())
                .load(mDataset.get(position).avatar)
                .into(holder.mImageView);

        holder.description.setText(mDataset.get(position).description);
        holder.lickcount.setText(mDataset.get(position).likecount);

    }

    public interface ListItemClickListener {
        void onListItemClick(String titleindex, String videoUrl, String setName, String setLikeCount, String setDescription);
    }
}
