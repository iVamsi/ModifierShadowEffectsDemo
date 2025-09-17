package com.vamsi.shadowmodifiers.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vamsi.shadowmodifiers.shadows.coloredDropShadow
import com.vamsi.shadowmodifiers.shadows.dropShadow
import com.vamsi.shadowmodifiers.shadows.glowDropShadow
import com.vamsi.shadowmodifiers.shadows.innerShadow
import com.vamsi.shadowmodifiers.shadows.materialCardShadow
import com.vamsi.shadowmodifiers.shadows.neumorphicShadow
import com.vamsi.shadowmodifiers.shadows.premiumButtonShadow
import com.vamsi.shadowmodifiers.shadows.realisticDropShadow

data class ModifierShadowExample(
    val title: String,
    val description: String,
    val codeSnippet: String,
    val content: @Composable () -> Unit,
)

@Composable
fun ModifierShadowShowcase(
    modifier: Modifier = Modifier,
) {
    val shadowExamples = remember {
        listOf(
            ModifierShadowExample(
                title = "Drop Shadow",
                description = "CSS box-shadow equivalent with precise control over blur, offset, and spread",
                codeSnippet = """
Modifier.dropShadow(
    shape = RoundedCornerShape(8.dp),
    color = Color.Black.copy(alpha = 0.25f),
    blur = 4.dp,
    offsetX = 2.dp,
    offsetY = 4.dp,
    spread = 1.dp
)
                """.trimIndent()
            ) {
                DropShadowExamples()
            },
            ModifierShadowExample(
                title = "Inner Shadow",
                description = "Inward shadows for depth and recessed effects",
                codeSnippet = """
Modifier.innerShadow(
    shape = RoundedCornerShape(12.dp),
    color = Color.Gray.copy(alpha = 0.3f),
    blur = 6.dp
)
                """.trimIndent()
            ) {
                InnerShadowExamples()
            },
            ModifierShadowExample(
                title = "Realistic Material Shadows",
                description = "Multi-layer shadows that match real-world lighting with ambient and key shadows",
                codeSnippet = """
Modifier.realisticDropShadow(
    elevation = 8.dp,
    shape = RoundedCornerShape(8.dp)
)
                """.trimIndent()
            ) {
                RealisticShadowExamples()
            },
            ModifierShadowExample(
                title = "Colored Brand Shadows",
                description = "Brand-specific colored shadows for modern UI aesthetics",
                codeSnippet = """
Modifier.coloredDropShadow(
    color = Color.Blue.copy(alpha = 0.4f),
    blur = 8.dp,
    offsetY = 4.dp
)
                """.trimIndent()
            ) {
                ColoredShadowExamples()
            },
            ModifierShadowExample(
                title = "Glow Effects",
                description = "Radial glow effects for highlighting and attention-grabbing elements",
                codeSnippet = """
Modifier.glowDropShadow(
    glowColor = Color.Cyan,
    glowRadius = 12.dp
)
                """.trimIndent()
            ) {
                GlowEffectExamples()
            },
            ModifierShadowExample(
                title = "Neumorphic Design",
                description = "Soft UI with combined light and dark shadows for subtle depth",
                codeSnippet = """
Modifier.neumorphicShadow(
    backgroundColor = Color(0xFFE0E0E0),
    shadowDistance = 4.dp
)
                """.trimIndent()
            ) {
                NeumorphicExamples()
            },
            ModifierShadowExample(
                title = "Interactive Button Shadows",
                description = "Premium button effects with press state transitions",
                codeSnippet = """
Modifier.premiumButtonShadow(
    isPressed = isPressed,
    color = Color.Black
)
                """.trimIndent()
            ) {
                InteractiveButtonExamples()
            }
        )
    }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        item {
            HeaderSection()
        }

        items(shadowExamples) { example ->
            ShadowExampleSection(example = example)
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun HeaderSection() {
    Column {
        Text(
            text = "Compose 1.9 Advanced Shadows",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun ShadowExampleSection(example: ModifierShadowExample) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .materialCardShadow(elevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = example.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = example.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )

            // Visual example
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(
                        Color(0xFFF5F5F5),
                        RoundedCornerShape(12.dp)
                    )
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                example.content()
            }

            // Code snippet
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                )
            ) {
                Text(
                    text = example.codeSnippet,
                    style = MaterialTheme.typography.bodySmall,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
private fun DropShadowExamples() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Basic drop shadow
        Box(
            modifier = Modifier
                .size(60.dp)
                .dropShadow(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.Black.copy(alpha = 0.25f),
                    blur = 4.dp,
                    offsetX = 2.dp,
                    offsetY = 4.dp,
                    spread = 1.dp
                )
                .background(
                    Color.White,
                    RoundedCornerShape(8.dp)
                )
        )

        // Larger shadow
        Box(
            modifier = Modifier
                .size(60.dp)
                .dropShadow(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.Black.copy(alpha = 0.2f),
                    blur = 8.dp,
                    offsetX = 0.dp,
                    offsetY = 6.dp,
                    spread = 2.dp
                )
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(12.dp)
                )
        )

        // Offset shadow
        Box(
            modifier = Modifier
                .size(60.dp)
                .dropShadow(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.Black.copy(alpha = 0.3f),
                    blur = 6.dp,
                    offsetX = 4.dp,
                    offsetY = 4.dp,
                    spread = 0.dp
                )
                .background(
                    MaterialTheme.colorScheme.secondary,
                    RoundedCornerShape(8.dp)
                )
        )
    }
}

@Composable
private fun InnerShadowExamples() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Subtle inner shadow
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(
                    Color(0xFFE0E0E0),
                    RoundedCornerShape(12.dp)
                )
                .innerShadow(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.Gray.copy(alpha = 0.3f),
                    blur = 6.dp,
                    offsetX = 2.dp,
                    offsetY = 2.dp
                )
        )

        // Pressed button effect
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    RoundedCornerShape(8.dp)
                )
                .innerShadow(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.Black.copy(alpha = 0.25f),
                    blur = 4.dp,
                    offsetX = 1.dp,
                    offsetY = 1.dp
                )
        )

        // Deep inset
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(
                    Color(0xFF2A2A2A),
                    RoundedCornerShape(8.dp)
                )
                .innerShadow(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.Black.copy(alpha = 0.6f),
                    blur = 8.dp,
                    offsetX = 3.dp,
                    offsetY = 3.dp
                )
        )
    }
}

@Composable
private fun RealisticShadowExamples() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Low elevation
        Box(
            modifier = Modifier
                .size(60.dp)
                .realisticDropShadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(
                    Color.White,
                    RoundedCornerShape(8.dp)
                )
        )

        // Medium elevation
        Box(
            modifier = Modifier
                .size(60.dp)
                .realisticDropShadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(
                    Color.White,
                    RoundedCornerShape(8.dp)
                )
        )

        // High elevation
        Box(
            modifier = Modifier
                .size(60.dp)
                .realisticDropShadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(
                    Color.White,
                    RoundedCornerShape(8.dp)
                )
        )
    }
}

@Composable
private fun ColoredShadowExamples() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Blue brand shadow
        Box(
            modifier = Modifier
                .size(60.dp)
                .coloredDropShadow(
                    color = Color.Blue.copy(alpha = 0.4f),
                    blur = 8.dp,
                    offsetY = 4.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(
                    Color.White,
                    RoundedCornerShape(8.dp)
                )
        )

        // Red warning shadow
        Box(
            modifier = Modifier
                .size(60.dp)
                .coloredDropShadow(
                    color = Color.Red.copy(alpha = 0.3f),
                    blur = 6.dp,
                    offsetY = 3.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(
                    Color.White,
                    RoundedCornerShape(8.dp)
                )
        )

        // Purple premium shadow
        Box(
            modifier = Modifier
                .size(60.dp)
                .coloredDropShadow(
                    color = Color(0xFF9C27B0).copy(alpha = 0.35f),
                    blur = 10.dp,
                    offsetY = 5.dp,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(
                    Color.White,
                    RoundedCornerShape(12.dp)
                )
        )
    }
}

@Composable
private fun GlowEffectExamples() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Cyan glow
        Box(
            modifier = Modifier
                .size(60.dp)
                .glowDropShadow(
                    glowColor = Color.Cyan,
                    glowRadius = 12.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(
                    Color.Black,
                    RoundedCornerShape(8.dp)
                )
        )

        // Green success glow
        Box(
            modifier = Modifier
                .size(60.dp)
                .glowDropShadow(
                    glowColor = Color.Green,
                    glowRadius = 10.dp,
                    shape = CircleShape
                )
                .background(
                    Color.White,
                    CircleShape
                )
        )

        // Orange warning glow
        Box(
            modifier = Modifier
                .size(60.dp)
                .glowDropShadow(
                    glowColor = Color(0xFFFF9800),
                    glowRadius = 14.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(
                    Color.White,
                    RoundedCornerShape(8.dp)
                )
        )
    }
}

@Composable
private fun NeumorphicExamples() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Light neumorphic
        Box(
            modifier = Modifier
                .size(60.dp)
                .neumorphicShadow(
                    backgroundColor = Color(0xFFE0E0E0),
                    shadowDistance = 4.dp,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(
                    Color(0xFFE0E0E0),
                    RoundedCornerShape(12.dp)
                )
        )

        // Circular neumorphic
        Box(
            modifier = Modifier
                .size(60.dp)
                .neumorphicShadow(
                    backgroundColor = Color(0xFFF0F0F0),
                    shadowDistance = 6.dp,
                    shape = CircleShape
                )
                .background(
                    Color(0xFFF0F0F0),
                    CircleShape
                )
        )
    }
}

@Composable
private fun InteractiveButtonExamples() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var isPressed1 by remember { mutableStateOf(false) }
        var isPressed2 by remember { mutableStateOf(false) }

        // Premium button
        Box(
            modifier = Modifier
                .size(width = 80.dp, height = 40.dp)
                .premiumButtonShadow(
                    isPressed = isPressed1,
                    color = Color.Black,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(8.dp)
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    isPressed1 = !isPressed1
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "TAP",
                color = Color.White,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold
            )
        }

        // Material card button
        Card(
            modifier = Modifier
                .size(60.dp)
                .materialCardShadow(
                    elevation = if (isPressed2) 2.dp else 8.dp
                )
                .clickable {
                    isPressed2 = !isPressed2
                },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "PRESS",
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
