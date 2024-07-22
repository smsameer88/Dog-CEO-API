package com.sameer.dogbreeds.ui.navigation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.sameer.dogbreeds.ui.component.dogBreedDetails.DogBreedDetails
import com.sameer.dogbreeds.ui.component.dogBreedsList.DogBreedsList
import com.sameer.dogbreeds.ui.component.favouriteDogBreeds.FavouriteDogBreeds
import com.sameer.dogbreeds.utils.Constants.DETAILS_ROUTE
import com.sameer.dogbreeds.utils.Constants.FAVOURITE
import com.sameer.dogbreeds.utils.Constants.NAME
import com.sameer.dogbreeds.utils.Constants.SUB_BREEDS
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalAnimationApi
@Composable
fun DogBreedsNavigation() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController, startDestination = Screen.Home.route) {
        composable(
            Screen.Home.route,
        ) {
            DogBreedsList(navController, hiltViewModel())
        }
        composable(
            "${Screen.Details.route}${DETAILS_ROUTE}",
            arguments = listOf(navArgument(NAME) { type = NavType.StringType },
                navArgument(SUB_BREEDS) { type = NavType.StringType },
                navArgument(FAVOURITE) { type = NavType.BoolType })
        ) {
            DogBreedDetails(
                navController,
                hiltViewModel(),
                it.arguments?.getString(NAME) ?: "",
                it.arguments?.getString(SUB_BREEDS) ?: "",
                it.arguments?.getBoolean(FAVOURITE) ?: false
            )
        }
        composable(
            Screen.Favourites.route,
        ) {
            FavouriteDogBreeds(navController = navController, hiltViewModel())
        }
    }
}
