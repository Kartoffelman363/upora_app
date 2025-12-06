package work.aljazroglic.kino

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import work.aljazroglic.kino.databinding.MovieCardBinding
import work.aljazroglic.kinolib.RatedMovie
import work.aljazroglic.kinolib.Theatre

class MovieAdapter(private val data: Theatre): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.movie_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val dataLine = data.movies[position]
        with(holder.binding) {
            card.setOnClickListener {
                TODO("Edit line")
            }
            card.setOnLongClickListener {
                TODO("Open delete line dialogue")
            }
            textViewTitle.text = dataLine.name
            when(dataLine) {
                is RatedMovie -> textViewScore.text =
                    holder.itemView.context.getString(R.string.out_of_ten_rating, dataLine.rating)
                else -> {}
            }
            TODO("Set image")
        }
    }

    override fun getItemCount(): Int {
        return data.movies.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = MovieCardBinding.bind(itemView)
    }
}