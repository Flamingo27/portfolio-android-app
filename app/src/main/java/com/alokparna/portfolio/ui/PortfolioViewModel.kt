package com.alokparna.portfolio.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alokparna.portfolio.data.ContactRepository
import com.alokparna.portfolio.data.Portfolio
import com.alokparna.portfolio.data.PortfolioRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PortfolioViewModel : ViewModel() {
    private val _portfolio = MutableStateFlow(PortfolioRepo.portfolio)
    val portfolio: StateFlow<Portfolio> = _portfolio

    private val _contactUiState = MutableStateFlow<ContactUiState>(ContactUiState.Idle)
    val contactUiState: StateFlow<ContactUiState> = _contactUiState

    fun sendContactMessage(name: String, email: String, message: String) {
        viewModelScope.launch {
            _contactUiState.value = ContactUiState.Loading
            val success = ContactRepository.sendContactMessage(name, email, message)
            _contactUiState.value = if (success) ContactUiState.Success else ContactUiState.Error
        }
    }

    fun resetContactState() {
        _contactUiState.value = ContactUiState.Idle
    }
}

sealed class ContactUiState {
    object Idle : ContactUiState()
    object Loading : ContactUiState()
    object Success : ContactUiState()
    object Error : ContactUiState()
}