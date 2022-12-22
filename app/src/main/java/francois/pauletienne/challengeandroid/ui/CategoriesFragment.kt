package francois.pauletienne.challengeandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import francois.pauletienne.challengeandroid.R
import francois.pauletienne.challengeandroid.model.Category
import francois.pauletienne.challengeandroid.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi

class CategoriesFragment : Fragment(), CategoryAdapter.CategoryItemListener {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        setupRecyclerView(view)
        subscribeObservers()

        if (viewModel.categories.value == null) {
            viewModel.getCategories()
        }

        return view
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun subscribeObservers() {
        viewModel.categories.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Success<List<Category>> -> {
                    (requireActivity() as MainActivity).displayLoading(false)
                    populateRecyclerView(dataState.data)
                }
                is DataState.Loading -> {
                    (requireActivity() as MainActivity).displayLoading(true)
                }
                is DataState.Error -> {
                    (requireActivity() as MainActivity).displayLoading(false)
                    displayError(dataState.exception.message)
                }
            }
        }
    }

    private fun displayError(message: String?) {
        if (message.isNullOrEmpty()) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "Erreur inconnue", Toast.LENGTH_LONG).show()
        }
    }

    private fun populateRecyclerView(categories: List<Category>) {
        if (categories.isNotEmpty()) adapter.setItems(ArrayList(categories))
    }

    private fun setupRecyclerView(view: View) {
        adapter = CategoryAdapter(this)
        val recyclerView = view.findViewById<RecyclerView>(R.id.category_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    override fun onCategoryClicked(category: Category) {
        viewModel.selectCategory(category)
        if (viewModel.selectedCategory.value?.subCategories?.isEmpty() == false) {
            requireView().findNavController().navigate(R.id.action_categories_to_subcategories)
        } else {
            Toast.makeText(requireContext(), "Cette catégorie est vide.", Toast.LENGTH_LONG).show()
        }
    }

}