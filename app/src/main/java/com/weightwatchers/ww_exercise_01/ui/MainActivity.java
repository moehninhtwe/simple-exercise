package com.weightwatchers.ww_exercise_01.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.weightwatchers.ww_exercise_01.R;
import com.weightwatchers.ww_exercise_01.data.model.FoodRecipe;
import com.weightwatchers.ww_exercise_01.presenter.FoodRecipePresenter;
import com.weightwatchers.ww_exercise_01.presenter.RecipeViewPresenter;
import com.weightwatchers.ww_exercise_01.viewmodel.CustomViewModelFactory;

public class MainActivity extends AppCompatActivity
    implements RecipeViewPresenter.RecipeView, RecipeAdapter.OnItemClickListener {
    private RecipeAdapter recipeAdapter;
    private RecyclerView rvRecipe;
    private FoodRecipePresenter foodRecipePresenter;
    private ProgressBar pgLoading;
    private TextView tvErrorLoading;
    private CoordinatorLayout clView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvRecipe = findViewById(R.id.rc_view);
        pgLoading = findViewById(R.id.pb_loading);
        tvErrorLoading = findViewById(R.id.tv_title);
        clView = findViewById(R.id.cl_view);

        rvRecipe.setLayoutManager(new GridLayoutManager(this, 2));
        recipeAdapter = new RecipeAdapter(this, this);
        foodRecipePresenter = new FoodRecipePresenter(this);
        subscribeToGetFoodRecipes();
    }

    private void subscribeToGetFoodRecipes() {
        foodRecipePresenter = ViewModelProviders.of(this, new CustomViewModelFactory(this))
            .get(FoodRecipePresenter.class);
        foodRecipePresenter.getRecipeList().observe(this, foodRecipes -> {
            recipeAdapter.setRecipeList(foodRecipes);
            rvRecipe.setAdapter(recipeAdapter);
        });
    }

    @Override public void showSnackBar(String title) {
        Snackbar snackbar = Snackbar.make(clView, title, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        TextView tvSnackBarText =
            snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        tvSnackBarText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        tvSnackBarText.setTextSize(getResources().getDimension(R.dimen.snack_bar_text_size));
        snackBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        snackbar.show();
    }

    @Override public void startLoading() {
        pgLoading.setVisibility(View.VISIBLE);
    }

    @Override public void finishLoading() {
        pgLoading.setVisibility(View.GONE);
    }

    @Override public void showErrorLoading() {
        tvErrorLoading.setVisibility(View.VISIBLE);
    }

    @Override public void onRecipeClick(FoodRecipe foodRecipe) {
        foodRecipePresenter.onClickView(foodRecipe);
    }
}
