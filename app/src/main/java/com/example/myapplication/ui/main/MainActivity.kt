package com.example.myapplication.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.ui.NavGraphs
import com.example.myapplication.ui.destinations.ProductsScreenDestination
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.utils.rememberViewModelStoreOwner
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberAnimatedNavController()
            val navHostEngine = rememberAnimatedNavHostEngine()
            val coroutineScope = rememberCoroutineScope()

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)

            val vmStoreOwner = rememberViewModelStoreOwner()
            val viewModel: MainViewModel = viewModel(vmStoreOwner)

            val openDrawer = {
                coroutineScope.launch {
                    drawerState.open()
                }
            }
            val closeDrawer = {
                coroutineScope.launch {
                    drawerState.close()
                }
            }

            MyApplicationTheme {
                ProvideWindowInsets {
                    ModalNavigationDrawer(
                        drawerContent = {
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 12.dp)
                                    .verticalScroll(state = rememberScrollState())
                            ) {
                                DrawerItems(
                                    navController = navController,
                                    openDrawer = { openDrawer() },
                                    closeDrawer = { closeDrawer() }
                                )
                            }
                        },
                        drawerState = drawerState
                    ) {
                        CompositionLocalProvider(
                            LocalNavGraphViewModelStoreOwner provides vmStoreOwner
                        ) {
                            DestinationsNavHost(
                                navGraph = NavGraphs.root,
                                engine = navHostEngine,
                                navController = navController
                            ) {

                            }
                            /*NavHost*/
                        }
                    }
                }
            }

            LaunchedEffect(true) {
                viewModel.switchDrawer.collectLatest {
                    openDrawer()
                }
            }
        }
    }
}

val LocalNavGraphViewModelStoreOwner =
    staticCompositionLocalOf<ViewModelStoreOwner> {
        TODO("Undefined")
    }

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DrawerItems(navController: NavController, openDrawer: () -> Unit, closeDrawer: () -> Unit) {
    Spacer(modifier = Modifier.statusBarsHeight())

    NavigationDrawerItem(
        label = { Text(text = stringResource(R.string.products_label)) },
        selected = true,
        icon = { Icon(Icons.Outlined.Store, null) },
        onClick = {
            navController.navigate(ProductsScreenDestination.route)
            closeDrawer()
        }
    )

    NavigationDrawerItem(
        label = { Text(text = stringResource(R.string.my_purchases_label)) },
        selected = false,
        icon = { Icon(Icons.Outlined.ShoppingCart, null) },
        onClick = closeDrawer
    )

    NavigationDrawerItem(
        label = { Text(text = stringResource(R.string.profile_label)) },
        selected = false,
        icon = { Icon(Icons.Outlined.Person, null) },
        onClick = closeDrawer
    )

    Divider(modifier = Modifier.padding(horizontal = 16.dp))

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = stringResource(R.string.admin_area_label),
        style = MaterialTheme.typography.titleSmall
    )

    Spacer(modifier = Modifier.height(16.dp))

    NavigationDrawerItem(
        label = { Text(text = stringResource(R.string.users_label)) },
        selected = false,
        icon = { Icon(Icons.Outlined.PeopleOutline, null) },
        onClick = closeDrawer
    )

    Divider(modifier = Modifier.padding(horizontal = 16.dp))

    Spacer(modifier = Modifier.height(16.dp))

    NavigationDrawerItem(
        label = { Text(text = stringResource(R.string.settings_label)) },
        selected = false,
        icon = { Icon(Icons.Outlined.Settings, null) },
        onClick = closeDrawer
    )

    NavigationDrawerItem(
        label = { Text(text = stringResource(R.string.logout)) },
        selected = false,
        icon = { Icon(Icons.Outlined.Logout, null) },
        onClick = closeDrawer
    )
}