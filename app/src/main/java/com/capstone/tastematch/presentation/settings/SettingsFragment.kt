package com.capstone.tastematch.presentation.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.capstone.tastematch.R
import com.capstone.tastematch.presentation.auth.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val logoutPreference = findPreference<Preference>("logout_preference")
        logoutPreference?.setOnPreferenceClickListener {
            logout()
            true
        }

        val contactPreference = findPreference<Preference>("contact_preference")
        contactPreference?.setOnPreferenceClickListener {
            openInstagramProfile()
            true
        }

        val themePreference = findPreference<SwitchPreferenceCompat>("theme_preference")
        themePreference?.setOnPreferenceChangeListener { _, newValue ->
            val isDarkThemeEnabled = newValue as Boolean
            // Implement theme change logic here
            true
        }
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }

    private fun openInstagramProfile() {
        val instagramUsername = "andimulyafirman"

        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.instagram.com/$instagramUsername")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
