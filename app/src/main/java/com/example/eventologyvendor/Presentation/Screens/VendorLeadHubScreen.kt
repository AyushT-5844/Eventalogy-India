package com.example.eventologyvendor.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ═══════════════════════════════════════════════════════════════════════════
//  DESIGN TOKENS
// ═══════════════════════════════════════════════════════════════════════════

private val BgDark = Color(0xFF07070A)
private val CardDark = Color(0xFF1A1A1A)
private val CardSurface = Color(0xFF141414)
private val YellowAccent = Color(0xFFFFC500)
private val GrayText = Color(0xFF8C8C8C)
private val DimText = Color(0xFF5C5C5C)
private val GreenActive = Color(0xFF30D158)
private val OrangeTag = Color(0xFFFF9500)

// ═══════════════════════════════════════════════════════════════════════════
//  DATA MODELS
// ═══════════════════════════════════════════════════════════════════════════

private data class LeadItem(
    val title: String,
    val date: String,
    val guests: String,
    val location: String,
    val budgetRange: String,
    val description: String,
    val tags: List<LeadTag>,
    val ctaText: String,
    val ctaIcon: ImageVector,
    val imageColor: Color // placeholder color for event image
)

private data class LeadTag(
    val text: String,
    val bgColor: Color,
    val textColor: Color
)

// ═══════════════════════════════════════════════════════════════════════════
//  SAMPLE DATA
// ═══════════════════════════════════════════════════════════════════════════

private val sampleLeads = listOf(
    LeadItem(
        title = "Luxury Corporate Gala",
        date = "Oct 24, 2024",
        guests = "250 Guests",
        location = "Gurugram, HR",
        budgetRange = "₹15L - ₹20L",
        description = "Premium tech client looking for end-to-end event production, intelligent lighting, and…",
        tags = listOf(
            LeadTag("HIGH VALUE", Color(0xFF1A1A2E), Color(0xFFFFBB00)),
            LeadTag("AI MATCHED", Color(0xFF0D2818), GreenActive)
        ),
        ctaText = "Place Bid / Show Interest",
        ctaIcon = Icons.Filled.MonetizationOn,
        imageColor = Color(0xFF3A2E1A)
    ),
    LeadItem(
        title = "Floral Wedding Reception",
        date = "Nov 15, 2024",
        guests = "500 Guests",
        location = "Jaipur, RJ",
        budgetRange = "₹25L - ₹35L",
        description = "Elaborate floral-themed wedding reception with custom mandap, stage design, and…",
        tags = listOf(
            LeadTag("Pending Admin Review", Color(0xFF2E2A1A), OrangeTag)
        ),
        ctaText = "Place Bid / Show Interest",
        ctaIcon = Icons.Filled.MonetizationOn,
        imageColor = Color(0xFF1A2E1A)
    ),
    LeadItem(
        title = "Annual Tech Meet 2024",
        date = "Dec 05, 2024",
        guests = "1000 Guests",
        location = "Bangalore, KA",
        budgetRange = "₹30L - ₹45L",
        description = "Large-scale technology conference with multiple stages, LED walls, and live streaming…",
        tags = listOf(
            LeadTag("Assigned", Color(0xFF0D2818), GreenActive)
        ),
        ctaText = "Chat with Client",
        ctaIcon = Icons.AutoMirrored.Filled.Chat,
        imageColor = Color(0xFF1A1A2E)
    )
)

// ═══════════════════════════════════════════════════════════════════════════
//  MAIN SCREEN
// ═══════════════════════════════════════════════════════════════════════════

@Composable
fun VendorLeadHubScreen(
    onNavigateToProfile: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Surface(modifier = Modifier.fillMaxSize(), color = BgDark) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Scrollable content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(Modifier.height(48.dp))

                // Top Bar
                LeadHubTopBar()

                Spacer(Modifier.height(20.dp))

                // Vendor Profile Card
                VendorProfileCard()

                Spacer(Modifier.height(16.dp))

                // Stats Row
                StatsRow()

                Spacer(Modifier.height(24.dp))

                // Leads for You header
                LeadsHeader()

                Spacer(Modifier.height(16.dp))

                // Lead Cards
                sampleLeads.forEach { lead ->
                    LeadCard(lead = lead)
                    Spacer(Modifier.height(16.dp))
                }

                Spacer(Modifier.height(16.dp))
            }

            // Bottom Navigation
            LeadHubBottomNav(
                selectedIndex = selectedTab,
                onTabSelected = { index ->
                    selectedTab = index
                    if (index == 2) { // Profile tab
                        onNavigateToProfile()
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
private fun LeadHubTopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Filled.Menu,
            contentDescription = "Menu",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = "Vendor Lead Hub",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Box(modifier = Modifier.size(24.dp)) {
            Icon(
                Icons.Filled.Notifications,
                contentDescription = "Notifications",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            // Yellow notification dot
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(YellowAccent)
                    .align(Alignment.TopEnd)
            )
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════
//  VENDOR PROFILE CARD
// ═══════════════════════════════════════════════════════════════════════════

@Composable
private fun VendorProfileCard() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar placeholder
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFF2A2A2A))
                .border(1.dp, DimText, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("E", color = YellowAccent, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Eventology Partner",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Spacer(Modifier.width(4.dp))
                // Verified badge
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(GreenActive),
                    contentAlignment = Alignment.Center
                ) {
                    Text("✓", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
            Text(
                text = "PREMIUM VENDOR",
                color = GrayText,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.sp
            )
        }

        // Star rating
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, YellowAccent.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.Star,
                contentDescription = "Rating",
                tint = YellowAccent,
                modifier = Modifier.size(14.dp)
            )
            Spacer(Modifier.width(3.dp))
            Text("4.9", color = YellowAccent, fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════
//  STATS ROW
// ═══════════════════════════════════════════════════════════════════════════

@Composable
private fun StatsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        StatCard(
            modifier = Modifier.weight(1f),
            label = "INQUIRIES",
            value = "128",
            subtitle = "+14 new",
            subtitleColor = GreenActive,
            accentColor = YellowAccent
        )
        StatCard(
            modifier = Modifier.weight(1f),
            label = "INTEREST",
            value = "42",
            subtitle = "8 active",
            subtitleColor = OrangeTag,
            accentColor = OrangeTag
        )
        StatCard(
            modifier = Modifier.weight(1f),
            label = "EARNINGS",
            value = "₹8.4L",
            subtitle = "All time",
            subtitleColor = GrayText,
            accentColor = GreenActive
        )
    }
}

@Composable
private fun StatCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    subtitle: String,
    subtitleColor: Color,
    accentColor: Color
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(CardDark)
            .border(1.dp, accentColor.copy(alpha = 0.15f), RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Column {
            Text(
                text = label,
                color = GrayText,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.8.sp
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = value,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = subtitle,
                color = subtitleColor,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════
//  LEADS HEADER
// ═══════════════════════════════════════════════════════════════════════════

@Composable
private fun LeadsHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Leads for You",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable { }
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.FilterList,
                contentDescription = "Filter",
                tint = YellowAccent,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text("Filter", color = YellowAccent, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════
//  LEAD CARD
// ═══════════════════════════════════════════════════════════════════════════

@Composable
private fun LeadCard(lead: LeadItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardDark)
    ) {
        // Image placeholder with tags
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(lead.imageColor, lead.imageColor.copy(alpha = 0.3f))
                    )
                )
        ) {
            // Tags overlay
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                lead.tags.forEach { tag ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(tag.bgColor.copy(alpha = 0.85f))
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = tag.text,
                            color = tag.textColor,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Bottom gradient overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, CardDark)
                        )
                    )
            )
        }

        // Content
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Text(
                text = lead.title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(10.dp))

            // Details row 1 - Date & Guests
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.CalendarMonth,
                        contentDescription = null,
                        tint = GrayText,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(lead.date, color = GrayText, fontSize = 12.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.Group,
                        contentDescription = null,
                        tint = GrayText,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(lead.guests, color = GrayText, fontSize = 12.sp)
                }
            }

            Spacer(Modifier.height(6.dp))

            // Details row 2 - Location & Budget
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = null,
                        tint = GrayText,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(lead.location, color = GrayText, fontSize = 12.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.Payments,
                        contentDescription = null,
                        tint = GrayText,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(lead.budgetRange, color = GrayText, fontSize = 12.sp)
                }
            }

            Spacer(Modifier.height(10.dp))

            // Description
            Text(
                text = lead.description,
                color = GrayText,
                fontSize = 13.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 18.sp
            )

            Spacer(Modifier.height(14.dp))

            // CTA Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(
                        if (lead.ctaText == "Chat with Client") CardSurface
                        else Color.Transparent
                    )
                    .then(
                        if (lead.ctaText == "Chat with Client") {
                            Modifier.border(1.dp, DimText, RoundedCornerShape(24.dp))
                        } else {
                            Modifier.border(1.5.dp, YellowAccent, RoundedCornerShape(24.dp))
                        }
                    )
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        lead.ctaIcon,
                        contentDescription = null,
                        tint = if (lead.ctaText == "Chat with Client") Color.White else YellowAccent,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = lead.ctaText,
                        color = if (lead.ctaText == "Chat with Client") Color.White else YellowAccent,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════
//  BOTTOM NAVIGATION
// ═══════════════════════════════════════════════════════════════════════════

data class BottomNavItem(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem("Leads", Icons.Filled.Bolt, Icons.Outlined.Bolt),
    BottomNavItem("Bids", Icons.Filled.Description, Icons.Outlined.Description),
    BottomNavItem("Profile", Icons.Filled.AccountCircle, Icons.Outlined.AccountCircle),
    BottomNavItem("Earnings", Icons.Filled.Payments, Icons.Outlined.Payments)
)

@Composable
fun LeadHubBottomNav(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardDark)
            .padding(vertical = 8.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        bottomNavItems.forEachIndexed { index, item ->
            val isSelected = selectedIndex == index
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onTabSelected(index) }
                    .padding(vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                    contentDescription = item.label,
                    tint = if (isSelected) YellowAccent else GrayText,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = item.label,
                    color = if (isSelected) YellowAccent else GrayText,
                    fontSize = 11.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════
//  PREVIEW
// ═══════════════════════════════════════════════════════════════════════════

@Preview(showSystemUi = true)
@Composable
private fun VendorLeadHubPreview() {
    VendorLeadHubScreen()
}
