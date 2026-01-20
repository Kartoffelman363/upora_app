package work.aljazroglic.kino

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import work.aljazroglic.kino.databinding.MovieCardBinding
import work.aljazroglic.kinolib.MovieBase
import work.aljazroglic.kinolib.RatedMovie
import work.aljazroglic.kinolib.Theatre
import kotlin.uuid.ExperimentalUuidApi

class MovieAdapter(
    private val data: Theatre,
    private val launcher: ActivityResultLauncher<Intent>,
    private val showItemDeleteDialog: (Int) -> Unit
): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val context = parent.context
        val binding = MovieCardBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding, context, data, launcher, showItemDeleteDialog)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val movie = data.movies[position]
        holder.bindItem(movie, position)
    }

    override fun getItemCount(): Int {
        return data.movies.size
    }

    class ViewHolder(
        private val binding: MovieCardBinding,
        private val context: Context,
        private val data: Theatre,
        private val launcher: ActivityResultLauncher<Intent>,
        private val showItemDeleteDialog: (Int) -> Unit
    ):
            RecyclerView.ViewHolder(binding.root) {
        @OptIn(ExperimentalUuidApi::class)
        fun bindItem(movie: MovieBase, position: Int) {
            with(binding) {
                card.setOnClickListener {
                    //Edit line
                    val intent = Intent(context, AddMovieActivity::class.java)
                    intent.putExtra(AddMovieActivity.MOVIE_ID, data.movies[position].id.toString())
                    launcher.launch(intent)
                    //context.startActivity(intent)
                }
                card.setOnLongClickListener {
                    //Open delete line dialogue
                    showItemDeleteDialog(position)
                    true
                }

                textViewTitle.text = movie.name
                when(movie) {
                    is RatedMovie -> textViewScore.text =
                        context.getString(R.string.rated_movie_out_of_ten, movie.rating)

                    else -> {}
                }
                //TODO("Set image")
            }
        }
    }
}