package com.example.whatscooking.main;

import com.example.whatscooking.data.daos.RecipeInfoDao;
import com.example.whatscooking.di.ActivityScope;
import com.example.whatscooking.main.recipeslist.RecipesListFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent
public interface MainComponent {

    RecipeInfoDao recipeDao();

    @Subcomponent.Factory
    interface Factory {
        MainComponent create();
    }

    /*With this Dagger knows MainFragment requests injection and
      that it has to provide its dependencies
     */
    void inject(RecipesListFragment fragment);
}
