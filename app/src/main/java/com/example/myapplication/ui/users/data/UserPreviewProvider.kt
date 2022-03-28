package com.example.myapplication.ui.users.data

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.myapplication.ui.users.model.Photo
import com.example.myapplication.ui.users.model.User

class UserPreviewProvider : PreviewParameterProvider<User> {
    override val values = sequenceOf(
        User(
            id = 1,
            name = "Jefferson K. Lopez",
            email = "jefferson.k.lopez@example.com",
            photo = null
        ),
        User(
            id = 1,
            name = "Taís Russ",
            email = "tais.russ@example.com",
            photo = Photo(
                small = "https://images.unsplash.com/photo-1637921883625-b867cec7178d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
                medium = "https://images.unsplash.com/photo-1637921883625-b867cec7178d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
                large = "https://images.unsplash.com/photo-1637921883625-b867cec7178d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
            )
        ),
        User(
            id = 1,
            name = "Mark Mendez",
            email = "mark.mendez@example.com",
            photo = Photo(
                small = "https://images.unsplash.com/photo-1636837991401-4434810f73f9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
                medium = "https://images.unsplash.com/photo-1636837991401-4434810f73f9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
                large = "https://images.unsplash.com/photo-1636837991401-4434810f73f9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
            )
        ),
        User(
            id = 1,
            name = "Georgie Tyrell",
            email = "georgie.tyrell@example.com",
            photo = Photo(
                small = "https://images.unsplash.com/photo-1647613560920-9576ec9a4537?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=688&q=80",
                medium = "https://images.unsplash.com/photo-1647613560920-9576ec9a4537?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=688&q=80",
                large = "https://images.unsplash.com/photo-1647613560920-9576ec9a4537?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=688&q=80"
            )
        ),
        User(
            id = 1,
            name = "Alice Halle",
            email = "alice.halle@example.com",
            photo = Photo(
                small = "https://images.unsplash.com/photo-1638280930541-cb04fd8e1c08?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
                medium = "https://images.unsplash.com/photo-1638280930541-cb04fd8e1c08?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
                large = "https://images.unsplash.com/photo-1638280930541-cb04fd8e1c08?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
            )
        )
    )
}