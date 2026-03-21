package com.example.mkdictionary.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ── Exercise 1: Custom Layout / Profile Card ──────────────────────────────────
@Composable
fun ProfileCardScreen() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.tertiary
                        )
                    )
                )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Јане Доу", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text("Android Developer", color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AssistChip(onClick = {}, label = { Text("Kotlin") })
                AssistChip(onClick = {}, label = { Text("Compose") })
                AssistChip(onClick = {}, label = { Text("Android") })
            }
        }
    }
}

// ── Exercise 2: Material 3 Theming ────────────────────────────────────────────
@Composable
fun ThemingScreen() {
    val colors = listOf(
        "Primary"   to MaterialTheme.colorScheme.primary,
        "Secondary" to MaterialTheme.colorScheme.secondary,
        "Tertiary"  to MaterialTheme.colorScheme.tertiary,
        "Error"     to MaterialTheme.colorScheme.error,
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Material 3 Палета", style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                colors.forEach { (name, color) ->
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(color)
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(name, style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }
    }
}

// ── Exercise 3: Animations ────────────────────────────────────────────────────
@Composable
fun AnimationsScreen() {
    var visible  by remember { mutableStateOf(true) }
    var rotated  by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (rotated) 360f else 0f,
        animationSpec = tween(600),
        label = "rotation"
    )
    val boxSize by animateDpAsState(
        targetValue = if (expanded) 100.dp else 50.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "boxSize"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Анимации", style = MaterialTheme.typography.titleMedium)

            // Visibility
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(onClick = { visible = !visible }) {
                    Text(if (visible) "Скриј" else "Прикажи")
                }
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn() + slideInHorizontally(),
                    exit  = fadeOut() + slideOutHorizontally()
                ) {
                    Text("✨ Анимиран текст", color = MaterialTheme.colorScheme.primary)
                }
            }

            // Rotation
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(onClick = { rotated = !rotated }) { Text("Ротирај") }
                Icon(
                    Icons.Default.Refresh,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .rotate(rotation),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }

            // Size
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(onClick = { expanded = !expanded }) { Text("Зголеми") }
                Box(
                    modifier = Modifier
                        .size(boxSize)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                )
            }
        }
    }
}

// ── Pathway 2 Main Screen ─────────────────────────────────────────────────────
@Composable
fun Pathway2Screen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text("Вежба 1: Profile Card Layout",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(Modifier.height(4.dp))
            ProfileCardScreen()
        }
        item {
            Text("Вежба 2: Material 3 Theming",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(Modifier.height(4.dp))
            ThemingScreen()
        }
        item {
            Text("Вежба 3: Анимации",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(Modifier.height(4.dp))
            AnimationsScreen()
            Spacer(Modifier.height(16.dp))
        }
    }
}