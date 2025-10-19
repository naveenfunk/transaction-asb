package nz.co.test.transactions.ui.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import nz.co.test.transactions.R

/*
* TODO This widget can be used at one place but due to lack of time I've to use
*  in both the screens separately. Also I wanted to add back button function but
*  couldn't do it due to time constraints.
* */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
) {
    TopAppBar(
        title = {
            Text(
                stringResource(R.string.app_name),
                style = AppTheme.typography.largeText
            )
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = colorResource(R.color.teal_700)
        ),
    )
}