package com.yogyakarta.yogyakartaapp.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogyakarta.yogyakartaapp.R
import com.yogyakarta.yogyakartaapp.base.BaseActivity
import com.yogyakarta.yogyakartaapp.fixtures.EXTRA_MOVIE_ID
import com.yogyakarta.yogyakartaapp.fixtures.StatusFixture
import com.yogyakarta.yogyakartaapp.ui.account.AccountActivity
import kotlinx.android.synthetic.main.activity_movie.*
import javax.inject.Inject

class MovieActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var movieViewModel: MovieViewModel? = null

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        movieViewModel = ViewModelProviders.of(this, viewModelFactory)[MovieViewModel::class.java]

        movieAdapter = MovieAdapter {
            Intent(this, MovieDetailActivity::class.java).run {
                putExtra(EXTRA_MOVIE_ID, it.movieId)
                startActivity(this)
            }
        }

        rv_article_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }

        movieViewModel?.getMovies()?.observe(this, Observer { movieResource ->
            when (movieResource.status) {
                StatusFixture.LOADING -> shimmer_movie_container.startShimmer()
                StatusFixture.SUCCESS -> {
                    shimmer_movie_container.stopShimmer()
                    shimmer_movie_container.visibility = View.GONE
                    movieResource.data?.let { movieAdapter.setData(it) }
                }
                StatusFixture.ERROR -> Toast.makeText(this,movieResource.message,Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.account_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_menu_account){
            Intent(this, AccountActivity::class.java).run {
                startActivity(this)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
