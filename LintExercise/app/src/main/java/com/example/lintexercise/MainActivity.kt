package com.example.lintexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme

class MainActivity : ComponentActivity() {
    // ❌ Lint: Class body should not start with a blank line
    // (→ remove the empty line below this line)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // ❌ Lint issues:
            // - Missing newline after "("
            // - Each argument should be on a separate line
            // - Unexpected indentation (should be 16 spaces)
            // - Missing newline before ")"
            // - Missing trailing comma before ")"
            // - Trailing spaces detected
            Text(text = "Hello", // ← Arguments should not start on the same line as the function call
                style = MaterialTheme.typography.bodyLarge, // ← Incorrect indentation
                maxLines = 1) // ← Missing newline and trailing comma before ")"

            // ✅ Correct formatting:
            Text(
                text = "Hello",
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
            )
        }
    }
}
