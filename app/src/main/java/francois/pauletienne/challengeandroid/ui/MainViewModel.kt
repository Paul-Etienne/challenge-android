package francois.pauletienne.challengeandroid.ui

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import francois.pauletienne.challengeandroid.model.Category
import francois.pauletienne.challengeandroid.repository.MainRepository
import francois.pauletienne.challengeandroid.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _categories: MutableLiveData<DataState<List<Category>>> = MutableLiveData()
    private val _selectedCategory: MutableLiveData<Category> = MutableLiveData()

    val categories: LiveData<DataState<List<Category>>>
        get() = _categories

    val selectedCategory: LiveData<Category>
        get() = _selectedCategory

    fun selectCategory(category: Category) {
        _selectedCategory.value = category
    }

    fun getCategories() {
        viewModelScope.launch {
            mainRepository.getCategories()
                .onEach { dataState ->
                    _categories.value = dataState
                }
                .launchIn(viewModelScope)
        }
    }
}
