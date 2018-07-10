package com.weightwatchers.ww_exercise_01.presenter;

import com.weightwatchers.ww_exercise_01.data.model.FoodRecipe;

public interface RecipeViewPresenter {
    interface RecipeView {
        void showSnackBar(String title);
        void startLoading();
        void finishLoading();
        void showErrorLoading();
    }

    interface RecipePresenter extends BasePresenter<RecipeView> {
        void getFoodRecipe();

        void onClickView(FoodRecipe foodRecipe);
    }
}
