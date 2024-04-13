package fr.bwadaal.moodstatistics.ui.newentry.moodrating

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.bwadaal.moodstatistics.R

class MoodratingFragment : Fragment() {

    companion object {
        fun newInstance() = MoodratingFragment()
    }

    private val viewModel: MoodratingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_moodrating, container, false)
    }
}