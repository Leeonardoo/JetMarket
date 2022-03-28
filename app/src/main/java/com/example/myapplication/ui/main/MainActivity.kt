package com.example.myapplication.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.R
import com.example.myapplication.ui.NavGraphs
import com.example.myapplication.ui.components.common.DrawerController
import com.example.myapplication.ui.components.common.DrawerControllerImpl
import com.example.myapplication.ui.destinations.ProductsScreenDestination
import com.example.myapplication.ui.destinations.UsersScreenDestination
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val coroutineScope = rememberCoroutineScope()
            val navController = rememberAnimatedNavController()
            val navHostEngine = rememberAnimatedNavHostEngine()

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val drawerController = remember { DrawerControllerImpl(drawerState) }

            val viewModel = viewModel<MainViewModel>()

            BackHandler(enabled = drawerState.isOpen) {
                coroutineScope.launch { drawerController.close() }
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
                                    drawerController = drawerController
                                )
                            }
                        },
                        drawerState = drawerState
                    ) {
                        DestinationsNavHost(
                            navGraph = NavGraphs.root,
                            engine = navHostEngine,
                            navController = navController,
                            dependenciesContainerBuilder = {
                                dependency(drawerController)
                                dependency(viewModel)
                            }
                        ) {

                        }
                        /*NavHost*/
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DrawerItems(navController: NavController, drawerController: DrawerController) {
    val coroutineScope = rememberCoroutineScope()

    Spacer(modifier = Modifier.statusBarsHeight())

    NavigationDrawerItem(
        label = { Text(text = stringResource(R.string.products_label)) },
        selected = true,
        icon = { Icon(Icons.Outlined.Store, null) },
        onClick = {
            navController.navigate(ProductsScreenDestination.route)
            coroutineScope.launch { drawerController.close() }
        }
    )

    NavigationDrawerItem(
        label = { Text(text = stringResource(R.string.my_purchases_label)) },
        selected = false,
        icon = { Icon(Icons.Outlined.ShoppingCart, null) },
        onClick = {
            coroutineScope.launch { drawerController.close() }
        }
    )

    NavigationDrawerItem(
        label = { Text(text = stringResource(R.string.profile_label)) },
        selected = false,
        icon = { Icon(Icons.Outlined.Person, null) },
        onClick = {
            coroutineScope.launch { drawerController.close() }
        }
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
        onClick = {
            navController.navigate(UsersScreenDestination.route)
            coroutineScope.launch { drawerController.close() }
        }
    )

    Divider(modifier = Modifier.padding(horizontal = 16.dp))

    Spacer(modifier = Modifier.height(16.dp))

    NavigationDrawerItem(
        label = { Text(text = stringResource(R.string.settings_label)) },
        selected = false,
        icon = { Icon(Icons.Outlined.Settings, null) },
        onClick = {
            coroutineScope.launch { drawerController.close() }
        }
    )

    NavigationDrawerItem(
        label = { Text(text = stringResource(R.string.logout)) },
        selected = false,
        icon = { Icon(Icons.Outlined.Logout, null) },
        onClick = {
            coroutineScope.launch { drawerController.close() }
        }
    )
}