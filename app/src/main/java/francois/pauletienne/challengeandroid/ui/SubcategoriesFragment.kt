package francois.pauletienne.challengeandroid.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import francois.pauletienne.challengeandroid.R
import francois.pauletienne.challengeandroid.model.Category
import francois.pauletienne.challengeandroid.util.DataState

class SubcategoriesFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_subcategories, container, false)

        setupRecyclerView(view)
        val subCategories = viewModel.selectedCategory.value?.subCategories ?: return view

        populateRecyclerView(subCategories)

        view.findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        return view
    }

    private fun populateRecyclerView(categories: List<Category>) {
        if (categories.isNotEmpty()) adapter.setItems(ArrayList(categories))
    }

    private fun setupRecyclerView(view: View) {
        adapter = CategoryAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.category_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

}