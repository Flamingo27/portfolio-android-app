package com.alokparna.portfolio.ui

import androidx.lifecycle.ViewModel
import com.alokparna.portfolio.data.PortfolioRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.alokparna.portfolio.data.Portfolio

class PortfolioViewModel : ViewModel() {
    private val _portfolio = MutableStateFlow(PortfolioRepo.portfolio)
    val portfolio: StateFlow<Portfolio> = _portfolio
}