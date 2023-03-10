package com.cyberwalker.fashionstore.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cyberwalker.fashionstore.dump.vertical
import com.cyberwalker.fashionstore.ui.theme.highlight
import com.cyberwalker.fashionstore.ui.theme.ltgray
import com.cyberwalker.fashionstore.ui.theme.medium_18


@Composable
fun TabPromo(
    promo: List<String>
) {
    var selectedPromo by remember {
        mutableStateOf(0)
    }
    LazyRow(
        modifier = Modifier
            .vertical()
            .width(200.dp)
            .rotate(-90F), verticalAlignment = Alignment.CenterVertically
    ) {
        items(promo.size) {
            Box(
                modifier = Modifier
                    .size(70.dp, 28.dp)
                    .clickable {
                        selectedPromo = it
                    }
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        if (selectedPromo == it) highlight
                        else ltgray
                    ),
                contentAlignment = Alignment.Center,

            ) {
                Text(
                    text = promo[it],
                    style = MaterialTheme.typography.medium_18,
                    color = if (selectedPromo == it) Color.White else Color.Black
                )
            }
        }
    }
}