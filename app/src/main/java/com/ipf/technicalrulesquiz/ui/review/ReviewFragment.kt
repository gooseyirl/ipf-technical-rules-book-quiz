package com.ipf.technicalrulesquiz.ui.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.core.os.bundleOf
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.ipf.technicalrulesquiz.BuildConfig
import com.ipf.technicalrulesquiz.R
import com.ipf.technicalrulesquiz.billing.BillingManager
import com.ipf.technicalrulesquiz.databinding.FragmentReviewBinding
import com.ipf.technicalrulesquiz.ui.quiz.QuizViewModel

class ReviewFragment : Fragment() {

    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!

    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = viewModel.getQuizResult()
        val adapter = AnswerReviewAdapter(result.answeredQuestions) { pageNumber ->
            findNavController().navigate(
                R.id.action_review_to_pdf,
                bundleOf("pageNumber" to pageNumber)
            )
        }
        binding.answersRecyclerView.adapter = adapter

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        if (BuildConfig.SHOW_ADS && !BillingManager.isAdsRemoved(requireContext())) {
            MobileAds.initialize(requireContext()) {
                if (_binding != null) {
                    binding.adView.loadAd(AdRequest.Builder().build())
                }
            }
        } else {
            binding.adView.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        if (BuildConfig.SHOW_ADS && !BillingManager.isAdsRemoved(requireContext())) {
            binding.adView.resume()
        }
    }

    override fun onPause() {
        if (BuildConfig.SHOW_ADS && !BillingManager.isAdsRemoved(requireContext())) {
            binding.adView.pause()
        }
        super.onPause()
    }

    override fun onDestroyView() {
        binding.adView.destroy()
        super.onDestroyView()
        _binding = null
    }
}
