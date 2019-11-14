package com.yogyakarta.yogyakartaapp.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogyakarta.yogyakartaapp.R
import com.yogyakarta.yogyakartaapp.domain.Movie
import com.yogyakarta.yogyakartaapp.fixtures.IMAGE_URL
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter constructor(private val listener: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.ArticleViewHolder>() {
    private val data = mutableListOf<Movie>()

    fun setData(data: List<Movie>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder =
        ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(data[position], listener)
    }

    inner class ArticleViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: Movie, listener: (Movie) -> Unit) {
            containerView.card_article_container.setOnClickListener {
                listener(item)
            }
            Glide.with(itemView).load("${IMAGE_URL}${item.moviePoster}")
                .into(containerView.imageView_movie_image)
            containerView.textView_movie_title.text = item.movieTitle
            containerView.textView_movie_date.text = item.movieReleaseDate
        }
    }
}