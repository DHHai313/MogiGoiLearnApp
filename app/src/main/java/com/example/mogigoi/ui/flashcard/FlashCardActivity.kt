package com.example.mogigoi.ui.flashcard

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mogigoi.R
import com.example.mogigoi.data.model.Vocabulary
import com.example.mogigoi.databinding.ActivityFlashcardBinding
import com.example.mogigoi.viewmodel.FlashCardViewModel
import kotlin.math.abs

class FlashCardActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LESSON_ID    = "lesson_id"
        const val EXTRA_LEVEL_ID     = "level_id"
        const val EXTRA_LESSON_TOPIC = "lesson_topic"

        private const val FLIP_DURATION              = 150L   // ms per half-flip
        private const val SWIPE_THRESHOLD             = 80
        private const val SWIPE_VELOCITY_THRESHOLD    = 200
    }

    private lateinit var binding: ActivityFlashcardBinding
    private val viewModel: FlashCardViewModel by viewModels()

    /** True while a flip or swipe animation is running — blocks new gestures */
    private var isAnimating = false

    // ── Gesture Detector ────────────────────────────────────────────────────
    private val gestureDetector: GestureDetector by lazy {
        GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {

            override fun onDown(e: MotionEvent): Boolean = true

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                if (isAnimating) return false
                val diffX = e2.x - (e1?.x ?: 0f)
                val diffY = e2.y - (e1?.y ?: 0f)

                if (abs(diffX) > abs(diffY)
                    && abs(diffX) > SWIPE_THRESHOLD
                    && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD
                ) {
                    if (diffX < 0) animateSwipe(toLeft = true)  { viewModel.nextWord() }
                    else            animateSwipe(toLeft = false) { viewModel.previousWord() }
                    return true
                }
                return false
            }
        })
    }

    // ════════════════════════════════════════════════════════════════════════
    //  Lifecycle
    // ════════════════════════════════════════════════════════════════════════

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlashcardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lessonId   = intent.getIntExtra(EXTRA_LESSON_ID, -1)
        val levelId    = intent.getStringExtra(EXTRA_LEVEL_ID) ?: "N5"
        val lessonTopic = intent.getStringExtra(EXTRA_LESSON_TOPIC) ?: ""

        // Pre-set cameraDistance once (before any measurement)
        binding.cardFlashcard.post {
            val density = resources.displayMetrics.density
            binding.cardFlashcard.cameraDistance =
                binding.cardFlashcard.height.coerceAtLeast(400) * 8f * density
        }

        setupToolbar(lessonTopic)
        setupButtons()
        observeViewModel()

        if (lessonId != -1) viewModel.loadVocabulary(lessonId)
        else                 viewModel.loadVocabularyByLevel(levelId)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean =
        gestureDetector.onTouchEvent(event) || super.onTouchEvent(event)

    // ════════════════════════════════════════════════════════════════════════
    //  Setup
    // ════════════════════════════════════════════════════════════════════════

    private fun setupToolbar(topic: String) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = if (topic.isNotEmpty()) "📚 $topic" else "Flashcard"
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setupButtons() {
        // Tap card → flip
        binding.cardFlashcard.setOnClickListener {
            if (!isAnimating) performFlip()
        }

        // Attach gesture to card for swipe
        binding.cardFlashcard.setOnTouchListener { v, event ->
            val consumed = gestureDetector.onTouchEvent(event)
            if (!consumed) v.performClick()
            consumed
        }

        binding.btnFlip.setOnClickListener     { if (!isAnimating) performFlip() }
        binding.btnNext.setOnClickListener     { if (!isAnimating) animateSwipe(toLeft = true)  { viewModel.nextWord() } }
        binding.btnPrevious.setOnClickListener { if (!isAnimating) animateSwipe(toLeft = false) { viewModel.previousWord() } }

        // Completion screen buttons
        binding.btnRestart.setOnClickListener {
            showStudyLayout()
            viewModel.restart()
        }
        binding.btnBackToLesson.setOnClickListener { finish() }
    }

    private fun observeViewModel() {
        viewModel.currentVocabulary.observe(this) { vocab ->
            vocab?.let { displayWord(it) }
        }

        viewModel.currentIndex.observe(this) { index ->
            val total = viewModel.totalCount.value ?: 0
            if (total > 0) {
                binding.tvProgress.text   = "${index + 1}/$total"
                binding.progressBar.progress = ((index + 1) * 100) / total
                binding.tvTotalCount.text = total.toString()
            }
        }

        viewModel.totalCount.observe(this) { total ->
            binding.tvTotalCount.text = total.toString()
        }

        viewModel.isFinished.observe(this) { finished ->
            if (finished) showCompletionScreen()
        }
    }

    // ════════════════════════════════════════════════════════════════════════
    //  Display helpers
    // ════════════════════════════════════════════════════════════════════════

    private fun displayWord(vocab: Vocabulary) {
        // Instantly reset card — no rotation, show front
        binding.cardFlashcard.rotationY = 0f
        showFront(vocab)
    }

    private fun showFront(vocab: Vocabulary) {
        binding.layoutFront.visibility = View.VISIBLE
        binding.layoutBack.visibility  = View.GONE
        binding.tvKanji.text           = vocab.kanji
        binding.tvFurigana.text        = vocab.furigana
    }

    private fun showBack(vocab: Vocabulary) {
        binding.layoutFront.visibility      = View.GONE
        binding.layoutBack.visibility       = View.VISIBLE
        binding.tvKanjiBack.text            = vocab.kanji
        binding.tvFuriganaBack.text         = vocab.furigana
        binding.tvMeaning.text              = vocab.meaning
        binding.tvExample.text              = vocab.exampleJapanese
        binding.tvExampleTranslation.text   = vocab.exampleVietnamese
    }

    private fun showStudyLayout() {
        binding.layoutStudy.visibility      = View.VISIBLE
        binding.layoutCompletion.visibility = View.GONE
        binding.layoutProgress.visibility   = View.VISIBLE
    }

    private fun showCompletionScreen() {
        val total = viewModel.totalCount.value ?: 0
        binding.tvCompletionCount.text    = total.toString()
        binding.tvCompletionMessage.text  =
            "Bạn đã học xong $total từ vựng trong bài học này. Thật tuyệt vời! 🌟"

        binding.layoutStudy.visibility      = View.GONE
        binding.layoutCompletion.visibility = View.VISIBLE
        binding.layoutProgress.visibility   = View.GONE

        // Animate completion screen entrance
        binding.layoutCompletion.alpha = 0f
        binding.layoutCompletion.animate()
            .alpha(1f)
            .setDuration(400)
            .start()
    }

    // ════════════════════════════════════════════════════════════════════════
    //  Flip animation  ── THE FIX ──
    //
    //  Strategy: the CardView always starts and ends at rotationY = 0.
    //
    //  Phase-1  (FLIP OUT): rotationY  0  →  90   (card turns edge-on,
    //                        content becomes invisible at 90°)
    //           Interpolator: Accelerate  → feels like picking up speed
    //
    //  [mid-point]  Switch visibility: front ↔ back
    //
    //  Phase-2  (FLIP IN):  rotationY -90  →  0   (card swings back to
    //                        flat, showing new content)
    //           Interpolator: Decelerate  → eases into resting position
    //
    //  The card is ALWAYS at 0° at rest, so there is never any accumulated
    //  rotation and the mirror-bug cannot reappear.
    // ════════════════════════════════════════════════════════════════════════
    private fun performFlip() {
        val vocab            = viewModel.currentVocabulary.value ?: return
        val isCurrentlyBack  = viewModel.isFlipped.value ?: false
        isAnimating          = true

        // Phase 1 – spin to edge (0 → 90°)
        val flipOut = ObjectAnimator.ofFloat(binding.cardFlashcard, "rotationY", 0f, 90f).apply {
            duration     = FLIP_DURATION
            interpolator = AccelerateInterpolator()
        }

        // Phase 2 – spin back to flat (-90 → 0°)
        val flipIn = ObjectAnimator.ofFloat(binding.cardFlashcard, "rotationY", -90f, 0f).apply {
            duration     = FLIP_DURATION
            interpolator = DecelerateInterpolator()
        }

        flipOut.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                // At the invisible 90° edge: swap content, then animate in
                if (isCurrentlyBack) showFront(vocab) else showBack(vocab)
                flipIn.start()
            }
        })

        flipIn.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                isAnimating = false
                viewModel.flipCard()          // toggle isFlipped state in VM
            }
        })

        flipOut.start()
    }

    // ════════════════════════════════════════════════════════════════════════
    //  Swipe slide animation
    // ════════════════════════════════════════════════════════════════════════

    private fun animateSwipe(toLeft: Boolean, action: () -> Unit) {
        if (isAnimating) return
        isAnimating = true

        val sw       = resources.displayMetrics.widthPixels.toFloat()
        val slideOut = if (toLeft) -sw else sw
        val slideIn  = if (toLeft)  sw else -sw

        binding.cardFlashcard.animate()
            .translationX(slideOut)
            .alpha(0f)
            .setDuration(200)
            .withEndAction {
                action()                                // update ViewModel (may show completion)
                binding.cardFlashcard.translationX = slideIn
                binding.cardFlashcard.alpha        = 0f
                binding.cardFlashcard.animate()
                    .translationX(0f)
                    .alpha(1f)
                    .setDuration(200)
                    .withEndAction { isAnimating = false }
                    .start()
            }
            .start()
    }
}
