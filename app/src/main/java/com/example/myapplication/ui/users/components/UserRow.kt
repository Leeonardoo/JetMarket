package com.example.myapplication.ui.users.components

import android.content.res.Configuration
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.graphics.asAndroidColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.users.data.UserPreviewProvider
import com.example.myapplication.ui.users.model.User
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun UserRow(
    modifier: Modifier = Modifier,
    user: User,
    onClick: () -> Unit
) {
    Surface {
        Column {
            ListItem(
                modifier = modifier.clickable(onClick = onClick),
                icon = {
                    val placeholder = AppCompatResources.getDrawable(
                        LocalContext.current,
                        R.drawable.account_circle_42dp
                    )?.apply {
                        colorFilter =
                            tint(MaterialTheme.colorScheme.onSurfaceVariant).asAndroidColorFilter()
                    }

                    if (!user.photo?.small.isNullOrBlank()) {
                        Image(
                            modifier = Modifier.size(42.dp),
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = /*baseUrl + */user.photo?.small)
                                    .placeholder(placeholder)
                                    .apply(block = {
                                        transformations(CircleCropTransformation())
                                        crossfade(true)
                                    }).build()
                            ),
                            contentDescription = stringResource(R.string.profile_picture_desc)
                        )
                    } else {
                        Image(
                            modifier = Modifier.size(42.dp),
                            painter = rememberDrawablePainter(placeholder),
                            contentDescription = stringResource(R.string.profile_picture_desc)
                        )
                    }
                },
                secondaryText = { Text(text = user.email, maxLines = 1) },
                singleLineSecondaryText = true,
                trailing = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Outlined.MoreVert, stringResource(R.string.more))
                    }
                },
                text = { Text(text = user.name) }
            )

            Divider(modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}

@Preview
@Composable
fun PreviewUserRow(
    @PreviewParameter(UserPreviewProvider::class) user: User
) {
    MyApplicationTheme {
        UserRow(
            modifier = Modifier.fillMaxWidth(),
            user = user,
            onClick = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewUserRowNight(
    @PreviewParameter(UserPreviewProvider::class) user: User
) {
    MyApplicationTheme {
        UserRow(
            modifier = Modifier.fillMaxWidth(),
            user = user,
            onClick = {}
        )
    }
}