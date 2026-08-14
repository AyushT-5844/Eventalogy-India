package com.example.eventologyvendor.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ═══════════════════════════════════════════════════════════════════════════
//  DESIGN TOKENS (local to this file)
// ═══════════════════════════════════════════════════════════════════════════

private val BgDark = Color(0xFF07070A)
private val CardDark = Color(0xFF1A1A1A)
private val YellowAccent = Color(0xFFFFC500)
private val GrayText = Color(0xFF8C8C8C)
private val DimText = Color(0xFF5C5C5C)
private val ChipBg = Color(0xFF2A2A2A)
private val FieldBg = Color(0xFF141414)
private val DividerColor = Color(0xFF2A2A2A)
private val MapBg = Color(0xFF1E1E1E)

// ═══════════════════════════════════════════════════════════════════════════
//  MAIN SCREEN
// ═══════════════════════════════════════════════════════════════════════════

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EditProfileScreen(
    onBack: () -> Unit = {},
    onNavigateToLeads: () -> Unit = {}
) {
    var companyName by remember { mutableStateOf("Eventology Premium Partner") }
    var selectedCategory by remember { mutableStateOf("Event Production") }
    var basePricing by remember { mutableStateOf("50000") }
    var bio by remember { mutableStateOf("Premium hardware-heavy vendor specializing in high-end sound reinforcement, custom lighting rigs, and weather-proof outdoor structures across India.") }
    var instagramUrl by remember { mutableStateOf("") }
    var linkedinUrl by remember { mutableStateOf("") }
    var websiteUrl by remember { mutableStateOf("") }
    val keywords = remember {
        mutableStateListOf(
            "On-site Support", "Power Backup", "Waterproof Tents",
            "Lighting Design", "Logistics"
        )
    }
    var inventoryExpanded by remember { mutableStateOf(false) }
    var technicalRiderExpanded by remember { mutableStateOf(false) }
    var equipmentListExpanded by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableIntStateOf(2) } // Profile tab selected

    Surface(modifier = Modifier.fillMaxSize(), color = BgDark) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Scrollable content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(Modifier.height(48.dp))

                // ─── Top Bar ─────────────────────────────────────
                ProfileTopBar(onBack = onBack)

                Spacer(Modifier.height(24.dp))

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                    // ─── Media Gallery ────────────────────────────────
                    SectionLabel("MEDIA GALLERY")
                    Spacer(Modifier.height(4.dp))

                    // Add Media button
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { }
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Filled.AddPhotoAlternate,
                                contentDescription = "Add Media",
                                tint = YellowAccent,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                "Add Media",
                                color = YellowAccent,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Spacer(Modifier.height(8.dp))

                    // Media thumbnails
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // Existing media placeholder 1
                        MediaThumbnail(
                            hasContent = true,
                            label = "EVENT\nMANAGEMENT",
                            bgColor = Color(0xFF1A1A0A)
                        )
                        // Existing media placeholder 2
                        MediaThumbnail(
                            hasContent = true,
                            label = null,
                            bgColor = Color(0xFF2A1A0A)
                        )
                        // Add new media slot
                        MediaThumbnail(
                            hasContent = false,
                            label = null,
                            bgColor = CardDark
                        )
                    }

                    Spacer(Modifier.height(28.dp))

                    // ─── Profile Info ─────────────────────────────────
                    SectionLabel("PROFILE INFO")

                    Spacer(Modifier.height(16.dp))

                    // Company/Service Name
                    FieldLabel("COMPANY/SERVICE NAME")
                    Spacer(Modifier.height(6.dp))
                    ProfileTextField(
                        value = companyName,
                        onValueChange = { companyName = it }
                    )

                    Spacer(Modifier.height(16.dp))

                    // Category & Base Pricing side by side
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            FieldLabel("CATEGORY")
                            Spacer(Modifier.height(6.dp))
                            DropdownField(value = selectedCategory)
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            FieldLabel("BASE PRICING")
                            Spacer(Modifier.height(6.dp))
                            ProfileTextField(
                                value = "₹ $basePricing",
                                onValueChange = {
                                    basePricing = it.removePrefix("₹ ")
                                }
                            )
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    // Warehouse/Office Location
                    FieldLabel("WAREHOUSE/OFFICE LOCATION")
                    Spacer(Modifier.height(8.dp))

                    // Map placeholder
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MapBg)
                    ) {
                        // Map pin icon in center
                        Icon(
                            Icons.Filled.LocationOn,
                            contentDescription = "Location Pin",
                            tint = YellowAccent,
                            modifier = Modifier
                                .size(32.dp)
                                .align(Alignment.Center)
                        )

                        // Change Pin button
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(8.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(YellowAccent)
                                .clickable { }
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                        ) {
                            Text(
                                "CHANGE PIN",
                                color = Color.Black,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(Modifier.height(8.dp))

                    // Address
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 4.dp)
                    ) {
                        Icon(
                            Icons.Filled.LocationOn,
                            contentDescription = null,
                            tint = GrayText,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "Industrial Estate, Sector 14, Gurugram, HR",
                            color = GrayText,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    // Bio
                    FieldLabel("BIO")
                    Spacer(Modifier.height(6.dp))
                    ProfileTextField(
                        value = bio,
                        onValueChange = { bio = it },
                        minLines = 4,
                        maxLines = 6
                    )

                    Spacer(Modifier.height(24.dp))

                    // ─── Expertise Keywords ───────────────────────────
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SectionLabel("EXPERTISE KEYWORDS")
                        Text(
                            "${keywords.size}/10",
                            color = GrayText,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(Modifier.height(12.dp))

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        keywords.forEachIndexed { index, keyword ->
                            KeywordChip(
                                text = keyword,
                                onRemove = { keywords.removeAt(index) }
                            )
                        }

                        // + Add button
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .border(1.dp, DimText, RoundedCornerShape(20.dp))
                                .clickable { }
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Filled.Add,
                                contentDescription = "Add keyword",
                                tint = GrayText,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text("Add", color = GrayText, fontSize = 13.sp)
                        }
                    }

                    Spacer(Modifier.height(28.dp))

                    // ─── Technical Details ─────────────────────────────
                    SectionLabel("TECHNICAL DETAILS")

                    Spacer(Modifier.height(12.dp))

                    AccordionItem(
                        icon = Icons.Filled.Inventory2,
                        title = "Inventory & Stock",
                        expanded = inventoryExpanded,
                        onToggle = { inventoryExpanded = !inventoryExpanded }
                    )

                    Spacer(Modifier.height(8.dp))

                    AccordionItem(
                        icon = Icons.Filled.Description,
                        title = "Technical Rider",
                        expanded = technicalRiderExpanded,
                        onToggle = { technicalRiderExpanded = !technicalRiderExpanded }
                    )

                    Spacer(Modifier.height(8.dp))

                    AccordionItem(
                        icon = Icons.Filled.Description,
                        title = "Equipment List",
                        expanded = equipmentListExpanded,
                        onToggle = { equipmentListExpanded = !equipmentListExpanded }
                    )

                    Spacer(Modifier.height(28.dp))

                    // ─── Social Connectivity ──────────────────────────
                    SectionLabel("SOCIAL CONNECTIVITY")

                    Spacer(Modifier.height(12.dp))

                    SocialField(
                        icon = Icons.Filled.PlayCircle,
                        iconTint = Color(0xFFE1306C),
                        placeholder = "Instagram URL",
                        value = instagramUrl,
                        onValueChange = { instagramUrl = it }
                    )

                    Spacer(Modifier.height(10.dp))

                    SocialField(
                        icon = Icons.Filled.Link,
                        iconTint = Color(0xFF0A66C2),
                        placeholder = "LinkedIn URL",
                        value = linkedinUrl,
                        onValueChange = { linkedinUrl = it }
                    )

                    Spacer(Modifier.height(10.dp))

                    SocialField(
                        icon = Icons.Filled.Link,
                        iconTint = GrayText,
                        placeholder = "Website URL",
                        value = websiteUrl,
                        onValueChange = { websiteUrl = it }
                    )

                    Spacer(Modifier.height(24.dp))
                }
            }

            // Bottom Navigation (shared component from VendorLeadHubScreen)
            LeadHubBottomNav(
                selectedIndex = selectedTab,
                onTabSelected = { index ->
                    selectedTab = index
                    if (index == 0) {
                        onNavigateToLeads()
                    }
                }
            )
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════
//  TOP BAR
// ═══════════════════════════════════════════════════════════════════════════

@Composable
private fun ProfileTopBar(onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Text(
            text = "Edit Profile",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(YellowAccent)
                .clickable { }
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                "Save",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════
//  SECTION / FIELD LABELS
// ═══════════════════════════════════════════════════════════════════════════

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text,
        color = GrayText,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 1.2.sp
    )
}

@Composable
private fun FieldLabel(text: String) {
    Text(
        text = text,
        color = DimText,
        fontSize = 10.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.8.sp
    )
}

// ═══════════════════════════════════════════════════════════════════════════
//  MEDIA THUMBNAILS
// ═══════════════════════════════════════════════════════════════════════════

@Composable
private fun MediaThumbnail(
    hasContent: Boolean,
    label: String?,
    bgColor: Color
) {
    Box(
        modifier = Modifier
            .size(width = 120.dp, height = 90.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .border(1.dp, DimText.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        if (!hasContent) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Add Media",
                tint = DimText,
                modifier = Modifier.size(32.dp)
            )
        } else if (label != null) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Bulb icon placeholder
                Text("💡", fontSize = 20.sp)
                Spacer(Modifier.height(4.dp))
                Text(
                    text = label,
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp,
                    lineHeight = 10.sp
                )
            }
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════
//  TEXT FIELDS
// ═══════════════════════════════════════════════════════════════════════════

@Composable
private fun ProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    minLines: Int = 1,
    maxLines: Int = 1
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = YellowAccent,
            focusedBorderColor = YellowAccent,
            unfocusedBorderColor = DimText.copy(alpha = 0.5f),
            focusedContainerColor = FieldBg,
            unfocusedContainerColor = FieldBg
        ),
        shape = RoundedCornerShape(12.dp),
        minLines = minLines,
        maxLines = maxLines,
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 14.sp,
            color = Color.White
        )
    )
}

@Composable
private fun DropdownField(value: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(FieldBg)
            .border(1.dp, DimText.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
            .clickable { }
            .padding(horizontal = 14.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = value,
                color = Color.White,
                fontSize = 14.sp
            )
            Icon(
                Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expand",
                tint = GrayText,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════
//  KEYWORD CHIPS
// ═══════════════════════════════════════════════════════════════════════════

@Composable
private fun KeywordChip(text: String, onRemove: () -> Unit) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(YellowAccent.copy(alpha = 0.15f))
            .border(1.dp, YellowAccent.copy(alpha = 0.3f), RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = YellowAccent,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(Modifier.width(6.dp))
        Icon(
            Icons.Filled.Close,
            contentDescription = "Remove $text",
            tint = YellowAccent.copy(alpha = 0.7f),
            modifier = Modifier
                .size(14.dp)
                .clickable { onRemove() }
        )
    }
}

// ═══════════════════════════════════════════════════════════════════════════
//  ACCORDION ITEMS
// ═══════════════════════════════════════════════════════════════════════════

@Composable
private fun AccordionItem(
    icon: ImageVector,
    title: String,
    expanded: Boolean,
    onToggle: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(CardDark)
            .clickable { onToggle() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = YellowAccent,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Icon(
                if (expanded) Icons.Filled.KeyboardArrowUp
                else Icons.Filled.KeyboardArrowDown,
                contentDescription = if (expanded) "Collapse" else "Expand",
                tint = GrayText,
                modifier = Modifier.size(22.dp)
            )
        }

        if (expanded) {
            HorizontalDivider(color = DividerColor, thickness = 1.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "No items added yet. Tap to add details.",
                    color = GrayText,
                    fontSize = 13.sp
                )
            }
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════
//  SOCIAL FIELDS
// ═══════════════════════════════════════════════════════════════════════════

@Composable
private fun SocialField(
    icon: ImageVector,
    iconTint: Color,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(placeholder, color = DimText, fontSize = 14.sp)
        },
        leadingIcon = {
            Icon(
                icon,
                contentDescription = placeholder,
                tint = iconTint,
                modifier = Modifier.size(20.dp)
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = YellowAccent,
            focusedBorderColor = YellowAccent,
            unfocusedBorderColor = DimText.copy(alpha = 0.5f),
            focusedContainerColor = FieldBg,
            unfocusedContainerColor = FieldBg
        ),
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 14.sp,
            color = Color.White
        )
    )
}

// ═══════════════════════════════════════════════════════════════════════════
//  PREVIEW
// ═══════════════════════════════════════════════════════════════════════════

@Preview(showSystemUi = true)
@Composable
private fun EditProfilePreview() {
    EditProfileScreen()
}
