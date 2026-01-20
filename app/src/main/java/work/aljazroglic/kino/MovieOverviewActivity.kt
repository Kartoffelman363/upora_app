package work.aljazroglic.kino

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import work.aljazroglic.kino.databinding.ActivityMovieOverviewBinding
import kotlin.uuid.ExperimentalUuidApi

class MovieOverviewActivity : BaseActivity() {
    private val binding: ActivityMovieOverviewBinding by lazy {
        ActivityMovieOverviewBinding.inflate(layoutInflater)
    }

    private fun showItemDeleteDialog(context: Context, item: Int) {
        MaterialAlertDialogBuilder(context)
            .setMessage("Delete item?")
            .setPositiveButton("Cancel", null)
            .setNegativeButton("Delete") { _, _ ->
                if(item < 0) {
                    Log.i("UPORA", "Tried to delete non existing Control point from list")
                    return@setNegativeButton
                }
                app.theatre.movies.removeAt(item)
                binding.recyclerViewMovies.adapter?.notifyItemRemoved(item)
            }
            .show()
    }

    @OptIn(ExperimentalUuidApi::class)
    private val editLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == RESULT_OK) {
                val data = result.data ?: return@registerForActivityResult

                val movieId = data.getStringExtra(AddMovieActivity.MOVIE_ID) ?: return@registerForActivityResult
                val position = app.theatre.movies.indexOfFirst { m -> m.id.toString() == movieId }

                if (position != -1) {
                    binding.recyclerViewMovies.adapter?.notifyItemChanged(position)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recyclerViewMovies.adapter = MovieAdapter(
            app.theatre,
            editLauncher,
            {item: Int -> showItemDeleteDialog(this, item)})
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.recyclerViewMovies.adapter = null
    }

}