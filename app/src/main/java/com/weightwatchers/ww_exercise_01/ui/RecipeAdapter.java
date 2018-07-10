package com.weightwatchers.ww_exercise_01.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.weightwatchers.ww_exercise_01.R;
import com.weightwatchers.ww_exercise_01.data.model.FoodRecipe;
import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<FoodRecipe> recipeList = new ArrayList<>();
    private Context context;
    private RequestOptions requestOptions;
    private OnItemClickListener onItemClickListener;

    public RecipeAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_placeholder);
        requestOptions.error(R.drawable.ic_loading_err_img);
    }

    public void setRecipeList(List<FoodRecipe> recipeList) {
        this.recipeList = recipeList;
    }

    @NonNull @Override public RecipeViewHolder onCreateViewHolder(
        @NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.recipe_item_view, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override public void onBindViewHolder(
        @NonNull RecipeViewHolder holder, int position) {
        Glide.with(context)
            .setDefaultRequestOptions(requestOptions)
            .load(recipeList.get(position).getImage())
            .into(holder.ivImage);
        holder.tvTitle.setText(recipeList.get(position).getTitle());
        holder.itemView.setOnClickListener(
            view -> onItemClickListener.onRecipeClick(recipeList.get(position)));
    }

    @Override public int getItemCount() {
        return recipeList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImage;
        private TextView tvTitle;

        public RecipeViewHolder(View view) {
            super(view);
            ivImage = view.findViewById(R.id.iv_pic);
            tvTitle = view.findViewById(R.id.tv_title);
        }
    }

    public interface OnItemClickListener {
        void onRecipeClick(FoodRecipe foodRecipe);
    }
}
