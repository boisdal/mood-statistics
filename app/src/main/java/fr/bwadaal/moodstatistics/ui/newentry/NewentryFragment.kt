package fr.bwadaal.moodstatistics.ui.newentry

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.bwadaal.moodstatistics.R
import fr.bwadaal.moodstatistics.databinding.FragmentNewentryBinding


class NewentryFragment : Fragment() {

    private var _binding: FragmentNewentryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val newentryViewModel =
                ViewModelProvider(this).get(NewentryViewModel::class.java)

        _binding = FragmentNewentryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNewentry
        newentryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        var containerLayout = binding.containerLayout;
        var previousButton = binding.previousButton;
        var nextButton = binding.nextButton;
        var stepIndicatorsLayout = binding.stepIndicatorsLayout;
        var currentStep = 0

        var steps = arrayOf<View>(
            LayoutInflater.from(root.context).inflate(R.layout.fragment_moodrating, containerLayout, false),
            LayoutInflater.from(root.context).inflate(R.layout.fragment_sleeprating, containerLayout, false),
            LayoutInflater.from(root.context).inflate(R.layout.fragment_factorlisting, containerLayout, false)
        )

        var stepIndicators = initializeStepIndicators(steps, root.context, stepIndicatorsLayout);
        showCurrentStep(containerLayout, steps, currentStep, previousButton, nextButton, stepIndicators);


        // Seting click listener for previous button
        previousButton.setOnClickListener {
            if (currentStep > 0) {
                currentStep--
                showCurrentStep(containerLayout, steps, currentStep, previousButton, nextButton, stepIndicators)
            }
        }

        // Seting click listener for next button
        nextButton.setOnClickListener {
            if (currentStep < steps.size - 1) {
                currentStep++
                showCurrentStep(containerLayout, steps, currentStep, previousButton, nextButton, stepIndicators)
            } else {
                // When Last step reached submit the form
                submitForm(root.context)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeStepIndicators(steps: Array<View>, context: Context, stepIndicatorsLayout: LinearLayout): Array<TextView?> {
        var stepIndicators = arrayOfNulls<TextView>(steps.size)
        for (i in 0 until steps.size) {
            val stepIndicator = TextView(context)
            stepIndicator.text = (i + 1).toString()
            stepIndicator.setTextColor(Color.WHITE)
            stepIndicator.textSize = 18f
            stepIndicator.setBackgroundResource(R.drawable.circle_gray)
            stepIndicator.setGravity(Gravity.CENTER)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            // The Margins are set like this
            // Left margin: 10 pixels
            // Top margin: 0 pixels (no margin)
            // Right margin: 10 pixels
            // Bottom margin: 0 pixels (no margin)
            params.setMargins(10, 0, 10, 0)
            stepIndicator.setLayoutParams(params)
            stepIndicatorsLayout.addView(stepIndicator)
            stepIndicators[i] = stepIndicator
            if (i < steps.size - 1) {
                addArrowIndicator(stepIndicatorsLayout, context)
            }
        }
        return stepIndicators
    }


    // Adding arrow indicator between step indicators
    private fun addArrowIndicator(stepIndicatorsLayout: LinearLayout, context: Context) {
        val arrow = ImageView(context)
        // to add this create a new drawable resource file in res->drawable
        arrow.setImageResource(com.google.android.material.R.drawable.ic_arrow_back_black_24)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.CENTER_VERTICAL
        arrow.setLayoutParams(params)
        stepIndicatorsLayout.addView(arrow)
    }


    // Showing the current step
    private fun showCurrentStep(containerLayout: LinearLayout, steps: Array<View>, currentStep: Int, previousButton: Button, nextButton: Button, stepIndicators: Array<TextView?>) {
        containerLayout.removeAllViews()
        containerLayout.addView(steps.get(currentStep))
        // If Current Step is greater then 0 then making Previous Button Visible
        previousButton.setVisibility(if (currentStep > 0) View.VISIBLE else View.INVISIBLE)
        nextButton.setText(if (currentStep < steps.size - 1) "Next" else "Submit")
        updateStepIndicators(stepIndicators, currentStep)
    }


    // Updating the step indicators to highlight the current step
    private fun updateStepIndicators(stepIndicators: Array<TextView?>, currentStep: Int) {
        for (i in 0 until stepIndicators.size) {
            if (i == currentStep) {
                stepIndicators.get(i)?.setBackgroundResource(R.drawable.circle_green)
            } else {
                stepIndicators.get(i)?.setBackgroundResource(R.drawable.circle_gray)
            }
        }
    }

    // When clicked on submit button at last form/activity
    private fun submitForm(context: Context) {
        Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
    }
}