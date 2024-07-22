package com.sameer.dogbreeds.ui.component.dogBreedsList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sameer.dogbreeds.R
import com.sameer.dogbreeds.utils.Constants.FAVOURITES

@Composable
fun DogBreedsList(
    navController: NavHostController,
    dogBreedsListViewModel: DogBreedsListViewModel = hiltViewModel()
) {
    LazyColumn {
        item {
            TopBar()
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            GoToFavouritesButton(navController = navController)
        }

        item {
            if (dogBreedsListViewModel.uiState.isLoading) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillParentMaxSize()
                        .background(Color.White)
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        dogBreedsListViewModel.uiState.dogBreeds?.let { list ->
            items(list) {
                ItemDogCard(
                    it,
                    onItemClicked = { dog ->
                        navController.navigate("details/${dog.name}/${dog.isFavourite}?subBreeds=${dog.subBreeds}")
                    }
                )
            }
        }

    }
}

@Composable
fun GoToFavouritesButton(navController: NavHostController) {
    Button(
        onClick = { navController.navigate(FAVOURITES) },
        modifier = Modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        shape = RectangleShape,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        )
    ) {
        Text(
            text = stringResource(R.string.see_favourites),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.surface
        )
    }
}