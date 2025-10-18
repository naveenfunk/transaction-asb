package nz.co.test.transactions.ui

sealed class Route(val route: String) {

    data object HomeRoute : Route("home")
}
