package com.example.newsaggregator.feature.description_screen

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val descriptionScreenModule = module {
    viewModel { DescriptionScreenViewModel() }
}