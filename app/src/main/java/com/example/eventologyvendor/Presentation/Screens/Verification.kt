package com.example.eventologyvendor.Presentation.Screens

/**
 * Vendor Registration – Full Jetpack Compose UI  (3 screens)
 *
 * Required dependencies (build.gradle / build.gradle.kts):
 * ──────────────────────────────────────────────────────────
 * implementation("androidx.compose.material3:material3:<latest>")
 * implementation("androidx.compose.material:material-icons-extended:<latest>")
 * implementation("androidx.compose.foundation:foundation:<latest>")
 * implementation("androidx.compose.ui:ui:<latest>")
 *
 * Entry point: call  VendorRegistrationApp()  from your Activity / NavHost.
 */

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.GppGood
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.WbIncandescent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ═══════════════════════════════════════════════════════════════════════════
//  DESIGN TOKENS
// ═══════════════════════════════════════════════════════════════════════════

private val BgDark       = Color(0xFF0D0D0D)
private val CardDark     = Color(0xFF1A1A1A)
private val YellowAccent = Color(0xFFFFBB00)
private val GrayText     = Color(0xFF777777)
private val DimText      = Color(0xFF444444)

// ═══════════════════════════════════════════════════════════════════════════
//  ROOT – manages which screen is shown
// ═══════════════════════════════════════════════════════════════════════════

@Composable
fun VendorRegistrationApp() {
    var step by remember { mutableStateOf(0) }
    Surface(modifier = Modifier.fillMaxSize(), color = BgDark) {
        when (step) {
            0 -> PersonalDetailsScreen(onNext = { step = 1 })
            1 -> CategoryScreen(onNext = { step = 2 }, onBack = { step = 0 })
            2 -> VerificationScreen(onComplete = { /* navigate away */ }, onBack = { step = 1 })
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════
//  SHARED COMPONENTS
// ═══════════════════════════════════════════════════════════════════════════

/** Numbered circle + label progress indicator used on Screens 2 & 3. */
@Composable
private fun StepProgressWithLabels(currentStep: Int, labels: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        labels.forEachIndexed { index, label ->
            // Circle + label column
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.wrapContentWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(
                            when {
                                index < currentStep  -> YellowAccent
                                index == currentStep -> YellowAccent
                                else                 -> Color(0xFF2A2A2A)
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (index < currentStep) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(18.dp)
                        )
                    } else {
                        Text(
                            text = "${index + 1}",
                            color = if (index == currentStep) Color.Black else GrayText,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
                Spacer(Modifier.height(5.dp))
                Text(
                    text = label.uppercase(),
                    color = if (index <= currentStep) YellowAccent else GrayText,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.8.sp
                )
            }
            // Connector line (skip after last item)
            if (index < labels.size - 1) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 17.dp) // vertically align with center of 36dp circle
                        .height(2.dp)
                        .background(
                            if (index < currentStep) YellowAccent else Color(0xFF2A2A2A)
                        )
                )
            }
        }
    }
}

/** Dark-themed OutlinedTextField consistent across all screens. */
@Composable
private fun DarkTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = GrayText, fontSize = 14.sp) },
        leadingIcon = leadingIcon?.let {
            { Icon(it, contentDescription = null, tint = GrayText, modifier = Modifier.size(20.dp)) }
        },
        modifier = modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor   = CardDark,
            unfocusedContainerColor = CardDark,
            focusedBorderColor      = YellowAccent,
            unfocusedBorderColor    = Color.Transparent,
            focusedTextColor        = Color.White,
            unfocusedTextColor      = Color.White,
            cursorColor             = YellowAccent
        ),
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

/** Country-code prefix + phone number row. */
@Composable
private fun PhoneInputRow(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // +91 selector
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(CardDark)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { }
                .padding(horizontal = 12.dp, vertical = 18.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🇮🇳", fontSize = 14.sp)
                Spacer(Modifier.width(4.dp))
                Text("+91", color = Color.White, fontSize = 13.sp)
                Icon(
                    Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = GrayText,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
        Spacer(Modifier.width(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = GrayText, fontSize = 14.sp) },
            modifier = Modifier.weight(1f),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor   = CardDark,
                unfocusedContainerColor = CardDark,
                focusedBorderColor      = YellowAccent,
                unfocusedBorderColor    = Color.Transparent,
                focusedTextColor        = Color.White,
                unfocusedTextColor      = Color.White,
                cursorColor             = YellowAccent
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
    }
}

/** Small uppercase field label above an input. */
@Composable
private fun FieldLabel(text: String, accent: Boolean = false) {
    Text(
        text = text,
        color = if (accent) YellowAccent else GrayText,
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 1.sp
    )
}

/** Tag/chip pill used on the category screen. */
@Composable
private fun TagChip(tag: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) Color(0xFF2A2000) else Color(0xFF1E1E1E))
            .border(
                width = 1.dp,
                color = if (isSelected) YellowAccent else Color(0xFF333333),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Text(
            text = tag,
            color = if (isSelected) YellowAccent else Color.White,
            fontSize = 13.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

// ═══════════════════════════════════════════════════════════════════════════
//  SCREEN 1 – Personal & Company Details
// ═══════════════════════════════════════════════════════════════════════════

@Composable
fun PersonalDetailsScreen(onNext: () -> Unit) {
    var fullName         by remember { mutableStateOf("") }
    var email            by remember { mutableStateOf("") }
    var isCompany        by remember { mutableStateOf(false) }
    var cin              by remember { mutableStateOf("") }
    var panCard          by remember { mutableStateOf("") }
    var primaryContact   by remember { mutableStateOf("") }
    var secondaryContact by remember { mutableStateOf("") }
    var backupContact    by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(52.dp))

        // ── Top row: back button ─────────────────────────────────────────
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(CardDark)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(Modifier.width(12.dp))
            Text(
                "Vendor Registration",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.size(40.dp))
        }

        Spacer(Modifier.height(16.dp))

        // ── Step progress with circles (same style as Screens 2 & 3) ─────
        StepProgressWithLabels(currentStep = 0, labels = listOf("Profile", "Category", "Verify"))

        Spacer(Modifier.height(28.dp))

        Text("Vendor Registration", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(4.dp))
        Text("Step 1: Personal & Company Details", color = GrayText, fontSize = 14.sp)

        Spacer(Modifier.height(28.dp))

        // ── Full Name ────────────────────────────────────────────────────
        FieldLabel("FULL NAME")
        Spacer(Modifier.height(8.dp))
        DarkTextField(fullName, { fullName = it }, "John Doe", Icons.Default.Person)

        Spacer(Modifier.height(18.dp))

        // ── Email ────────────────────────────────────────────────────────
        FieldLabel("EMAIL ADDRESS")
        Spacer(Modifier.height(8.dp))
        DarkTextField(
            email, { email = it }, "john@example.com",
            Icons.Default.Email, KeyboardType.Email
        )

        Spacer(Modifier.height(18.dp))

        // ── Entity Type toggle ───────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(CardDark)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Entity Type", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text("Register as Individual or Company", color = GrayText, fontSize = 12.sp)
            }
            Switch(
                checked = isCompany,
                onCheckedChange = { isCompany = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor    = Color.Black,
                    checkedTrackColor    = YellowAccent,
                    checkedBorderColor   = Color.Transparent,
                    uncheckedThumbColor  = Color(0xFF555555),
                    uncheckedTrackColor  = Color(0xFF303030),
                    uncheckedBorderColor = Color.Transparent
                )
            )
        }

        Spacer(Modifier.height(18.dp))

        // ── CIN ──────────────────────────────────────────────────────────
        FieldLabel("CIN (CORPORATE ID)")
        Spacer(Modifier.height(8.dp))
        DarkTextField(cin, { cin = it }, "U12345DL2023PTC123456", Icons.Default.AccountBalance)

        Spacer(Modifier.height(18.dp))

        // ── PAN Card ─────────────────────────────────────────────────────
        FieldLabel("PAN CARD NUMBER")
        Spacer(Modifier.height(8.dp))
        DarkTextField(panCard, { panCard = it }, "ABCDE1234F", Icons.Default.CreditCard)

        Spacer(Modifier.height(22.dp))

        // ── Contact Trio ─────────────────────────────────────────────────
        FieldLabel("CONTACT TRIO (3 MANDATORY)", accent = true)
        Spacer(Modifier.height(10.dp))
        PhoneInputRow(primaryContact,   { primaryContact = it },   "Primary Contact")
        Spacer(Modifier.height(8.dp))
        PhoneInputRow(secondaryContact, { secondaryContact = it }, "Secondary Contact")
        Spacer(Modifier.height(8.dp))
        PhoneInputRow(backupContact,    { backupContact = it },    "Backup Contact")

        Spacer(Modifier.height(36.dp))

        // ── Next Step button ─────────────────────────────────────────────
        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = YellowAccent),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text("Next Step  →", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "SECURE REGISTRATION VIA EVENTOLOGY NODE",
            color = DimText,
            fontSize = 10.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            letterSpacing = 1.sp
        )

        Spacer(Modifier.height(36.dp))
    }
}

// ═══════════════════════════════════════════════════════════════════════════
//  SCREEN 2 – Select Category
// ═══════════════════════════════════════════════════════════════════════════

private data class CategoryItem(val name: String, val icon: ImageVector)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryScreen(onNext: () -> Unit, onBack: () -> Unit) {
    var searchQuery      by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Venues") }
    val selectedTags = remember { mutableStateListOf("Terrace Garden") }

    val categories = listOf(
        CategoryItem("Venues",       Icons.Default.Home),
        CategoryItem("Catering",     Icons.Default.Restaurant),
        CategoryItem("Sound",        Icons.Default.VolumeUp),
        CategoryItem("Lighting",     Icons.Default.WbIncandescent),
        CategoryItem("Photography",  Icons.Default.CameraAlt),
        CategoryItem("Decor",        Icons.Default.AutoAwesome)
    )

    val allTags = listOf("Poolside", "Resort", "Terrace Garden", "Heritage Property")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(52.dp))

        // ── Top bar ──────────────────────────────────────────────────────
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Text(
                "Register Vendor",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.size(48.dp))
        }

        Spacer(Modifier.height(16.dp))

        StepProgressWithLabels(currentStep = 1, labels = listOf("Profile", "Category", "Verify"))

        Spacer(Modifier.height(28.dp))

        Text("Select Your Category", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(6.dp))
        Text(
            "Choose the primary service you offer. This helps us match you with the right leads.",
            color = GrayText,
            fontSize = 13.sp,
            lineHeight = 19.sp
        )

        Spacer(Modifier.height(20.dp))

        // ── Search ───────────────────────────────────────────────────────
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search categories (e.g. DJ, Florist)", color = GrayText, fontSize = 13.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = GrayText) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor   = CardDark,
                unfocusedContainerColor = CardDark,
                focusedBorderColor      = YellowAccent,
                unfocusedBorderColor    = Color.Transparent,
                focusedTextColor        = Color.White,
                unfocusedTextColor      = Color.White,
                cursorColor             = YellowAccent
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        Spacer(Modifier.height(20.dp))

        // ── Category 2-column grid ────────────────────────────────────────
        val filtered = if (searchQuery.isBlank()) categories
        else categories.filter { it.name.contains(searchQuery, ignoreCase = true) }

        val rowCount = (filtered.size + 1) / 2
        repeat(rowCount) { rowIdx ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                for (col in 0..1) {
                    val idx = rowIdx * 2 + col
                    if (idx < filtered.size) {
                        val cat = filtered[idx]
                        val isSelected = selectedCategory == cat.name
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(14.dp))
                                .background(if (isSelected) Color(0xFF2A2000) else CardDark)
                                .border(
                                    width = if (isSelected) 1.5.dp else 0.dp,
                                    color = if (isSelected) YellowAccent else Color.Transparent,
                                    shape = RoundedCornerShape(14.dp)
                                )
                                .clickable { selectedCategory = cat.name }
                                .padding(vertical = 28.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (isSelected) YellowAccent else Color(0xFF2A2A2A)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        cat.icon,
                                        contentDescription = cat.name,
                                        tint = if (isSelected) Color.Black else Color.White,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                                Spacer(Modifier.height(12.dp))
                                Text(
                                    cat.name,
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    } else {
                        Spacer(Modifier.weight(1f))
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
        }

        Spacer(Modifier.height(18.dp))

        // ── Confirm Category button ───────────────────────────────────────
        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = YellowAccent),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text("Confirm Category  →", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Spacer(Modifier.height(20.dp))

        // ── Subcategory tags ─────────────────────────────────────────────
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            allTags.forEach { tag ->
                val isSelected = selectedTags.contains(tag)
                TagChip(
                    tag = tag,
                    isSelected = isSelected,
                    onClick = {
                        if (isSelected) selectedTags.remove(tag) else selectedTags.add(tag)
                    }
                )
            }
        }

        Spacer(Modifier.height(8.dp))
        Text(
            "Select keywords that best describe your venue style.",
            color = GrayText,
            fontSize = 12.sp,
            fontStyle = FontStyle.Italic
        )

        Spacer(Modifier.height(36.dp))
    }
}

// ═══════════════════════════════════════════════════════════════════════════
//  SCREEN 3 – Trust & Verification
// ═══════════════════════════════════════════════════════════════════════════

@Composable
fun VerificationScreen(onComplete: () -> Unit, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(52.dp))

        // ── Top bar ──────────────────────────────────────────────────────
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Text(
                "Step 3 of 3",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.size(48.dp))
        }

        Spacer(Modifier.height(16.dp))

        StepProgressWithLabels(
            currentStep = 2,
            labels = listOf("Profile", "Services", "Verification")
        )

        Spacer(Modifier.height(28.dp))

        Text("Trust & Verification", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(6.dp))
        Text(
            "Complete your KYC to unlock premium event bids and faster payouts.",
            color = GrayText,
            fontSize = 13.sp,
            lineHeight = 19.sp
        )

        Spacer(Modifier.height(20.dp))

        // ── "Verified Title" info banner ─────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF1A1500))
                .border(1.dp, Color(0xFF3A3000), RoundedCornerShape(12.dp))
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF2A2000)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.TrendingUp,
                    contentDescription = null,
                    tint = YellowAccent,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(Modifier.width(12.dp))
            Text(
                text = buildAnnotatedString {
                    append("Get the ")
                    withStyle(SpanStyle(color = YellowAccent, fontWeight = FontWeight.Bold)) {
                        append("Verified Title")
                    }
                    append(" to increase lead matching by 40%.")
                },
                color = YellowAccent,
                fontSize = 13.sp,
                lineHeight = 18.sp,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(20.dp))

        // ── Instant Verification card ────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(CardDark)
                .padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // DigiLocker icon placeholder
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF1A3A1A)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = null,
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(26.dp)
                    )
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(
                        "Instant Verification",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text("VIA DIGILOCKER", color = GrayText, fontSize = 10.sp, letterSpacing = 1.sp)
                }
                Icon(
                    Icons.Default.VerifiedUser,
                    contentDescription = null,
                    tint = Color(0xFF2196F3),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(Modifier.height(14.dp))

            Text(
                "Securely pull your government IDs directly from DigiLocker. Fast, paperless, and highly trusted by clients.",
                color = GrayText,
                fontSize = 13.sp,
                lineHeight = 19.sp
            )

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = YellowAccent),
                shape = RoundedCornerShape(25.dp)
            ) {
                Icon(
                    Icons.Default.OpenInNew,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "Verify via DigiLocker",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // ── Past Portfolio Review header ──────────────────────────────────
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Past Portfolio Review",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF252525))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text("Recommended", color = GrayText, fontSize = 11.sp)
            }
        }

        Spacer(Modifier.height(14.dp))

        // ── Upload Company Profile (dashed-like border) ───────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(CardDark)
                .border(1.dp, Color(0xFF323232), RoundedCornerShape(12.dp))
                .clickable { }
                .padding(36.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF222222)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.FileUpload,
                        contentDescription = null,
                        tint = GrayText,
                        modifier = Modifier.size(28.dp)
                    )
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    "Upload Company Profile",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "PDF or PPTX, Max 15MB. Include your past major events.",
                    color = GrayText,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        // ── GST/Trade License row ─────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(CardDark)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Description,
                contentDescription = null,
                tint = GrayText,
                modifier = Modifier.size(22.dp)
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text("GST/Trade License", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                Text("OPTIONAL", color = GrayText, fontSize = 10.sp, letterSpacing = 1.sp)
            }
            OutlinedButton(
                onClick = { },
                border = BorderStroke(1.dp, YellowAccent),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text("Add", color = YellowAccent, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            }
        }

        Spacer(Modifier.height(32.dp))

        // ── Trust shield icons ────────────────────────────────────────────
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Shield,   null, tint = DimText, modifier = Modifier.size(32.dp))
            Spacer(Modifier.width(24.dp))
            Icon(Icons.Default.Security, null, tint = DimText, modifier = Modifier.size(32.dp))
            Spacer(Modifier.width(24.dp))
            Icon(Icons.Default.GppGood,  null, tint = DimText, modifier = Modifier.size(32.dp))
        }

        Spacer(Modifier.height(28.dp))

        // ── Complete Registration button ──────────────────────────────────
        Button(
            onClick = onComplete,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(28.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Text(
                "COMPLETE REGISTRATION",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                letterSpacing = 1.sp
            )
        }

        Spacer(Modifier.height(14.dp))

        Text(
            "I'll do this later",
            color = GrayText,
            fontSize = 13.sp,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(44.dp))
    }
}