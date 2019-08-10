package com.nezspencer.tutorials

import android.graphics.Color
import android.os.Bundle
import android.text.Annotation
import android.text.SpannableString
import android.text.SpannedString
import android.text.method.LinkMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val termsText = getText(R.string.terms_conditions_prompt) as SpannedString
        val annotations = termsText.getSpans(0, termsText.length, Annotation::class.java)
        val termsCopy = SpannableString(termsText)

        for (annotation in annotations) {
            if (annotation.key == "action") {
                termsCopy.setSpan(
                    createClickSpan(annotation.value),
                    termsText.getSpanStart(annotation),
                    termsText.getSpanEnd(annotation),
                    SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }

        tv_terms_conditions.text = termsCopy
        tv_terms_conditions.movementMethod = LinkMovementMethod.getInstance()
        tv_terms_conditions.highlightColor = Color.TRANSPARENT
    }

    private fun createClickSpan(action: String) = when (action.toLowerCase()) {
        "accepttc" -> CustomClickSpan({ showToast("Terms ACCEPTED!") }, Color.RED)
        "opentc" -> CustomClickSpan({ showToast("Terms and condition OPENED!") }, Color.BLUE, true)
        else -> throw NotImplementedError("action $action not implemented")
    }

    private fun showToast(prompt: String) = Toast.makeText(this, prompt, Toast.LENGTH_SHORT).show()
}
