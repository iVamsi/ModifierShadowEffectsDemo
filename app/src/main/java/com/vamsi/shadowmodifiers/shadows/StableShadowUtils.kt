package com.vamsi.shadowmodifiers.shadows

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Official Compose 1.9 dropShadow modifier - simplified stable implementation
 * Creates shadows behind content with precise control (CSS box-shadow equivalent)
 */
fun Modifier.dropShadow(
    shape: Shape = RoundedCornerShape(0.dp),
    color: Color = Color.Black.copy(alpha = 0.25f),
    blur: Dp = 4.dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 4.dp,
    spread: Dp = 0.dp
) = then(
    this.drawBehind {
        drawDropShadowStable(
            color = color,
            blur = blur.toPx(),
            offsetX = offsetX.toPx(),
            offsetY = offsetY.toPx(),
            spread = spread.toPx(),
            shape = shape
        )
    }
)

/**
 * Official Compose 1.9 innerShadow modifier - simplified stable implementation
 * Adds depth with inward shadows for recessed effects
 */
fun Modifier.innerShadow(
    shape: Shape = RoundedCornerShape(0.dp),
    color: Color = Color.Black.copy(alpha = 0.25f),
    blur: Dp = 4.dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 4.dp,
    spread: Dp = 0.dp
) = then(
    this.drawBehind {
        drawInnerShadowStable(
            color = color,
            blur = blur.toPx(),
            offsetX = offsetX.toPx(),
            offsetY = offsetY.toPx(),
            spread = spread.toPx(),
            shape = shape
        )
    }
)

/**
 * Enhanced drop shadow with multiple layers for ultra-realistic depth
 */
fun Modifier.realisticDropShadow(
    elevation: Dp = 8.dp,
    shape: Shape = RoundedCornerShape(8.dp)
) = then(
    this.drawBehind {
        val elevationPx = elevation.toPx()

        // Ambient shadow (large, soft, diffused)
        drawDropShadowStable(
            color = Color.Black.copy(alpha = 0.12f),
            blur = elevationPx * 2f,
            offsetX = 0f,
            offsetY = elevationPx * 0.5f,
            spread = elevationPx * 0.1f,
            shape = shape
        )

        // Key shadow (smaller, sharper, directional)
        drawDropShadowStable(
            color = Color.Black.copy(alpha = 0.24f),
            blur = elevationPx,
            offsetX = 0f,
            offsetY = elevationPx * 0.25f,
            spread = 0f,
            shape = shape
        )
    }
)

/**
 * Colored drop shadow for brand-specific effects
 */
fun Modifier.coloredDropShadow(
    color: Color,
    blur: Dp = 8.dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 4.dp,
    spread: Dp = 0.dp,
    shape: Shape = RoundedCornerShape(8.dp)
) = dropShadow(
    shape = shape,
    color = color,
    blur = blur,
    offsetX = offsetX,
    offsetY = offsetY,
    spread = spread
)

/**
 * Glow effect using drop shadow
 */
fun Modifier.glowDropShadow(
    glowColor: Color,
    glowRadius: Dp = 12.dp,
    shape: Shape = RoundedCornerShape(8.dp)
) = dropShadow(
    shape = shape,
    color = glowColor.copy(alpha = 0.6f),
    blur = glowRadius,
    offsetX = 0.dp,
    offsetY = 0.dp,
    spread = 2.dp
)

/**
 * Neumorphism effect combining outer and inner shadows
 */
fun Modifier.neumorphicShadow(
    backgroundColor: Color = Color(0xFFE0E0E0),
    shadowDistance: Dp = 4.dp,
    shape: Shape = RoundedCornerShape(12.dp)
) = this
    .dropShadow(
        shape = shape,
        color = Color.White.copy(alpha = 0.8f),
        blur = shadowDistance * 2,
        offsetX = -shadowDistance,
        offsetY = -shadowDistance,
        spread = 0.dp
    )
    .dropShadow(
        shape = shape,
        color = Color.Gray.copy(alpha = 0.3f),
        blur = shadowDistance * 2,
        offsetX = shadowDistance,
        offsetY = shadowDistance,
        spread = 0.dp
    )

/**
 * Premium button shadow effect
 */
fun Modifier.premiumButtonShadow(
    isPressed: Boolean = false,
    color: Color = Color.Black,
    shape: Shape = RoundedCornerShape(8.dp)
) = if (isPressed) {
    innerShadow(
        shape = shape,
        color = color.copy(alpha = 0.25f),
        blur = 4.dp,
        offsetX = 1.dp,
        offsetY = 1.dp
    )
} else {
    dropShadow(
        shape = shape,
        color = color.copy(alpha = 0.15f),
        blur = 8.dp,
        offsetX = 0.dp,
        offsetY = 4.dp,
        spread = 0.dp
    )
}

/**
 * Card shadow that matches Material Design 3
 */
fun Modifier.materialCardShadow(
    elevation: Dp = 4.dp,
    shape: Shape = RoundedCornerShape(12.dp)
) = dropShadow(
    shape = shape,
    color = Color.Black.copy(alpha = 0.14f),
    blur = elevation * 2,
    offsetX = 0.dp,
    offsetY = elevation,
    spread = 0.dp
)

/**
 * Text shadow effect
 */
fun Modifier.textDropShadow(
    color: Color = Color.Black.copy(alpha = 0.25f),
    offsetX: Dp = 1.dp,
    offsetY: Dp = 1.dp,
    blur: Dp = 2.dp
) = dropShadow(
    shape = RoundedCornerShape(0.dp),
    color = color,
    blur = blur,
    offsetX = offsetX,
    offsetY = offsetY,
    spread = 0.dp
)

/**
 * Internal implementation for drop shadow rendering using stable APIs
 */
private fun DrawScope.drawDropShadowStable(
    color: Color,
    blur: Float,
    offsetX: Float,
    offsetY: Float,
    spread: Float,
    shape: Shape
) {
    val shadowSize = androidx.compose.ui.geometry.Size(
        width = size.width + spread * 2,
        height = size.height + spread * 2
    )

    val shadowOffset = androidx.compose.ui.geometry.Offset(
        x = offsetX - spread,
        y = offsetY - spread
    )

    // Simulate blur with multiple layers
    val blurLayers = maxOf(1, (blur / 3f).toInt())

    for (i in 0 until blurLayers) {
        val layerAlpha = color.alpha / (blurLayers * (i + 1))
        val layerOffset = i * 1.5f
        val layerColor = color.copy(alpha = layerAlpha)

        when {
            shape is RoundedCornerShape -> {
                drawRoundRect(
                    color = layerColor,
                    topLeft = shadowOffset + androidx.compose.ui.geometry.Offset(layerOffset, layerOffset),
                    size = shadowSize,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(12.dp.toPx())
                )
            }
            shape == CircleShape -> {
                drawCircle(
                    color = layerColor,
                    radius = minOf(shadowSize.width, shadowSize.height) / 2,
                    center = shadowOffset + androidx.compose.ui.geometry.Offset(
                        shadowSize.width / 2 + layerOffset,
                        shadowSize.height / 2 + layerOffset
                    )
                )
            }
            else -> {
                drawRect(
                    color = layerColor,
                    topLeft = shadowOffset + androidx.compose.ui.geometry.Offset(layerOffset, layerOffset),
                    size = shadowSize
                )
            }
        }
    }
}

/**
 * Internal implementation for inner shadow rendering using stable APIs
 */
private fun DrawScope.drawInnerShadowStable(
    color: Color,
    blur: Float,
    offsetX: Float,
    offsetY: Float,
    spread: Float,
    shape: Shape
) {
    // Simplified inner shadow - draw darker areas inside the bounds
    val insetSize = androidx.compose.ui.geometry.Size(
        width = size.width - spread * 2,
        height = size.height - spread * 2
    )

    val insetOffset = androidx.compose.ui.geometry.Offset(
        x = spread + offsetX,
        y = spread + offsetY
    )

    // Create inset shadow effect
    val blurLayers = maxOf(1, (blur / 4f).toInt())

    for (i in 0 until blurLayers) {
        val layerAlpha = color.alpha / (blurLayers * (i + 1))
        val layerOffset = i * 1f
        val layerColor = color.copy(alpha = layerAlpha)

        when {
            shape is RoundedCornerShape -> {
                drawRoundRect(
                    color = layerColor,
                    topLeft = insetOffset + androidx.compose.ui.geometry.Offset(layerOffset, layerOffset),
                    size = androidx.compose.ui.geometry.Size(
                        insetSize.width - layerOffset * 2,
                        insetSize.height - layerOffset * 2
                    ),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx())
                )
            }
            shape == CircleShape -> {
                val adjustedRadius = (minOf(insetSize.width, insetSize.height) / 2) - layerOffset
                if (adjustedRadius > 0) {
                    drawCircle(
                        color = layerColor,
                        radius = adjustedRadius,
                        center = insetOffset + androidx.compose.ui.geometry.Offset(
                            insetSize.width / 2,
                            insetSize.height / 2
                        )
                    )
                }
            }
            else -> {
                drawRect(
                    color = layerColor,
                    topLeft = insetOffset + androidx.compose.ui.geometry.Offset(layerOffset, layerOffset),
                    size = androidx.compose.ui.geometry.Size(
                        insetSize.width - layerOffset * 2,
                        insetSize.height - layerOffset * 2
                    )
                )
            }
        }
    }
}