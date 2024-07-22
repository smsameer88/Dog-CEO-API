package com.sameer.dogbreeds.ui.navigation

import com.sameer.dogbreeds.utils.Constants.DETAILS
import com.sameer.dogbreeds.utils.Constants.FAVOURITES
import com.sameer.dogbreeds.utils.Constants.HOME

sealed class Screen(val route: String) {
    object Home : Screen(HOME)
    object Details : Screen(DETAILS)
    object Favourites : Screen(FAVOURITES)
}
