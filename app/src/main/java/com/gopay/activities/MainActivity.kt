package com.gopay.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gopay.PeopleListAdapter
import com.gopay.R
import com.gopay.extensions.gone
import com.gopay.extensions.visible
import com.gopay.network.responses.Results
import com.gopay.viewmodels.MainActivityViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.full_screen_view.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainActivityViewModel: MainActivityViewModel by viewModels { viewModelFactory }

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: PeopleListAdapter

    private var peopleListTemp = mutableListOf<Results>()

    var pageNum = 1
    var hasNext = false
    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        observeViewModels()

        mainActivityViewModel.callPeopleApi(pageNum)

        peopleList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleCount = layoutManager.childCount
                val pasVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = adapter.itemCount

                if (hasNext && isLoading.not()) {
                    if (visibleCount + pasVisibleItem >= total) {
                        pageNum++
                        mainActivityViewModel.callPeopleApi(pageNum)
                    }
                }
            }
        })
    }

    private fun observeViewModels() {
        mainActivityViewModel.peopleListLiveData.observe(this) {
            for (i in 0 until it.results.size) {
                peopleListTemp.add(it.results[i])
                hasNext = it.next.isNullOrBlank().not()
                adapter.notifyItemChanged(i)
            }
        }

        mainActivityViewModel.networkStatus.observe(this) {
            when (it) {
                "SUCCESS" -> {
                    isLoading = false
                    errorAndLoaderView.gone()
                    peopleList.visible()
                }
                "PROGRESS" -> {
                    isLoading = true
                    errorAndLoaderView.visible()
                    peopleList.gone()
                    loader.visible()
                    error_view.gone()
                    error_text.gone()
                }
                "ERROR" -> {
                    isLoading = false
                    errorAndLoaderView.visible()
                    peopleList.gone()
                    loader.gone()
                    error_view.visible()
                    error_text.visible()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = PeopleListAdapter(peopleListTemp)
        layoutManager = LinearLayoutManager(this)

        peopleList.layoutManager = layoutManager
        peopleList.adapter = adapter
    }


}
