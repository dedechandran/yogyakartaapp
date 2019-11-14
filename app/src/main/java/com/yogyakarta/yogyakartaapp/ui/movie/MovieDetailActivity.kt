package com.yogyakarta.yogyakartaapp.ui.movie

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexboxLayout
import com.yogyakarta.yogyakartaapp.R
import com.yogyakarta.yogyakartaapp.base.BaseActivity
import com.yogyakarta.yogyakartaapp.domain.Movie
import com.yogyakarta.yogyakartaapp.fixtures.EXTRA_MOVIE_ID
import com.yogyakarta.yogyakartaapp.fixtures.IMAGE_URL
import com.yogyakarta.yogyakartaapp.fixtures.StatusFixture
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject

class MovieDetailActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private var movieViewModel : MovieViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(toolbar_movieDetail)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
        collapsingToolbar_movieDetail.apply {
            title = context.getString(R.string.detail_title)
            setExpandedTitleColor(Color.TRANSPARENT)
            setCollapsedTitleTextColor(Color.WHITE)
        }

        movieViewModel = ViewModelProviders.of(this,viewModelFactory)[MovieViewModel::class.java]

        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID,0)
        movieViewModel?.getMovieDetail(movieId)?.observe(this, Observer {movieResource ->
            when(movieResource.status){
                StatusFixture.LOADING -> shimmer_movieDetail_container.startShimmer()
                StatusFixture.SUCCESS -> {
                    shimmer_movieDetail_container.stopShimmer()
                    shimmer_movieDetail_container.visibility = View.GONE
                    movieResource.data?.let { showMovieDetail(it) }
                }
                StatusFixture.ERROR -> Toast.makeText(this,movieResource.message,Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showMovieDetail(movie: Movie) {
        Glide.with(this)
            .load("$IMAGE_URL${movie.moviePoster}")
            .into(imageView_movieDetail_image)
        textView_movieDetail_title.text = movie.movieTitle
        textView_movieDetail_date.text = movie.movieReleaseDate

        textView_movieDetail_durationLabel.text = resources.getString(R.string.detail_duration_label)
        textView_movieDetail_duration.text = movie.movieDuration

        textView_movieDetail_languageLabel.text = resources.getString(R.string.detail_language_label)
        textView_movieDetail_language.text = movie.movieLanguage

        textView_movieDetail_budgetLabel.text = resources.getString(R.string.detail_budget_label)
        textView_movieDetail_budget.text = movie.movieBudget

        textView_movieDetail_revenueLabel.text = resources.getString(R.string.detail_revenue_label)
        textView_movieDetail_revenue.text = movie.movieRevenue

        textView_movieDetail_overviewLabel.text = resources.getString(R.string.detail_overview_label)
        textView_movieDetail_overview.text = movie.movieOverview

        textView_movieDetail_genreLabel.text = resources.getString(R.string.detail_genres_label)
        showGenres(movie.movieGenres!!)
    }


    private fun showGenres(genres: List<String>) {
        for (i in genres.indices) {
            val genreView = layoutInflater.inflate(
                R.layout.item_genre,
                flexBox_movieDetail_genresContainer
            ) as FlexboxLayout
            val genreText = genreView.getChildAt(i) as TextView
            genreText.text = genres[i]
        }
    }
}
