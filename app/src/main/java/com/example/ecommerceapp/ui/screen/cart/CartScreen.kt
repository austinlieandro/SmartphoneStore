package com.example.ecommerceapp.ui.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.di.Injection
import com.example.ecommerceapp.ui.ViewModelFactory
import com.example.ecommerceapp.ui.common.UiState
import com.example.ecommerceapp.ui.components.CartItem
import com.example.ecommerceapp.ui.components.OrderButton


@Composable
fun CartScreen(
    viewModel: CartViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    onOrderButtonClicked: (String) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAddedOrderHandphone()
            }
            is UiState.Success -> {
                CartContent(
                    uiState.data,
                    onProductCountChanged = {handphoneId, count ->
                        viewModel.updateOrderReward(handphoneId, count)
                    },
                    onOrderButtonClicked = onOrderButtonClicked
                )
            }
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartContent(
    state: CartState,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    onOrderButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val shareMessage = stringResource(
        R.string.share_message,
        state.orderHandphone.count(),
        state.price
    )
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.menu_cart),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(weight = 1f)
        ) {
            items(state.orderHandphone, key = { it.handphone.id}) { data ->
                CartItem(
                    handphoneId = data.handphone.id,
                    image = data.handphone.image,
                    title = data.handphone.name,
                    totalPrice = data.handphone.price * data.count,
                    count = data.count,
                    onProductCountChanged = onProductCountChanged,
                )
                Divider()
            }
        }
        OrderButton(
            text = stringResource(R.string.total_order, state.price),
            enabled = state.orderHandphone.isNotEmpty(),
            onClick = {
                onOrderButtonClicked(shareMessage)
            },
            modifier = Modifier.padding(16.dp)
        )
    }
}
