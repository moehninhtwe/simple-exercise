package com.weightwatchers.ww_exercise_01.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.weightwatchers.ww_exercise_01.presenter.FoodRecipePresenter;
import com.weightwatchers.ww_exercise_01.presenter.RecipeViewPresenter;

public class CustomViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private RecipeViewPresenter.RecipeView recipeView;

    public CustomViewModelFactory(RecipeViewPresenter.RecipeView recipeView) {
        this.recipeView = recipeView;
    }

    @Override public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new FoodRecipePresenter(recipeView);
    }
}
