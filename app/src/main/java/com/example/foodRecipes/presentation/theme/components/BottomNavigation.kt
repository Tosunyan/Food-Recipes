package com.example.foodRecipes.presentation.theme.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodRecipes.R
import com.example.foodRecipes.presentation.navigation.NavigationDestination
import com.example.foodRecipes.presentation.theme.Gray400
import com.example.foodRecipes.presentation.theme.Orange500
import com.example.foodRecipes.presentation.theme.indication.ScaleIndicationNodeFactory
import com.inconceptlabs.designsystem.components.core.Icon
import com.inconceptlabs.designsystem.theme.AppTheme

data class BottomNavigationItem(
    @DrawableRes val iconRes: Int,
    val destination: NavigationDestination,
    val isSelected: Boolean = false,
)

@Composable
fun BottomNavigation(
    items: List<BottomNavigationItem>,
    onItemClick: (NavigationDestination) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(Color.White)
            .padding(vertical = 16.dp, horizontal = 24.dp)
    ) {
        items.forEach {
            Icon(
                painter = painterResource(id = it.iconRes),
                tint = if (it.isSelected) Orange500 else Gray400,
                modifier = Modifier.clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = LocalIndication.current,
                    onClick = {
                        items
                            .map { it.copy(isSelected = false) }
                        onItemClick(it.destination)
                    }
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ComponentPreview() {
    AppTheme(
        indication = ScaleIndicationNodeFactory
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            BottomNavigation(
                items = listOf(
                    BottomNavigationItem(
                        iconRes = R.drawable.ic_home,
                        destination = object : NavigationDestination {},
                        isSelected = true,
                    ),
                    BottomNavigationItem(
                        iconRes = R.drawable.ic_search,
                        destination = object : NavigationDestination {}
                    ),
                    BottomNavigationItem(
                        iconRes = R.drawable.ic_like,
                        destination = object : NavigationDestination {}
                    )
                ),
                onItemClick = {},
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            )
        }
    }
}