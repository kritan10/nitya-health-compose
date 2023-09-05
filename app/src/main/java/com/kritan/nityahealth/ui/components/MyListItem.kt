package com.kritan.nityahealth.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.ui.theme.mListBgColor

@Composable
fun MyListItem(
    @DrawableRes leading: Int,
    title: String,
    trailing: String?,
    modifier: Modifier = Modifier
        .background(mListBgColor)
        .padding(16.dp)
) {
    Row(
        modifier
    ) {
        Icon(
            painter = painterResource(leading),
            contentDescription = "",
            modifier = Modifier.padding(top = 4.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title, style = MaterialTheme.typography.bodyMedium)
        if (trailing != null) {
            Spacer(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .weight(1f)
            )
            Text(text = trailing, style = MaterialTheme.typography.bodyMedium)
        }
    }
}