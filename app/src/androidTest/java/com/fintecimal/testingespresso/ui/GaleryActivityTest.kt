package com.fintecimal.testingespresso.ui

import android.app.Activity
import android.app.Instrumentation
import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.provider.MediaStore
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.fintecimal.testingespresso.R
import com.fintecimal.testingespresso.ui.movie.GaleryActivity
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4ClassRunner::class)
class GaleryActivityTest{

    @get:Rule
    val intentsTestRule = IntentsTestRule(GaleryActivity::class.java)

    @Test
    fun  test_validateIntentSentToPickPackage() {

        // GIVEN
        val expectedIntent: Matcher<Intent> = Matchers.allOf(
            hasAction(Intent.ACTION_PICK),
            hasData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        )
        val activityResult = createGalleryPickActivityResultStub()
        intending(expectedIntent).respondWith(activityResult)

        // Execute and Verify
        Espresso.onView(withId(R.id.button_open_gallery)).perform(ViewActions.click())
        intended(expectedIntent)
    }

    private fun createGalleryPickActivityResultStub(): Instrumentation.ActivityResult {
        val resources: Resources = InstrumentationRegistry.getInstrumentation().context.resources
        val imageUri = Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    resources.getResourcePackageName(R.drawable.ic_launcher_background) + '/' +
                    resources.getResourceTypeName(R.drawable.ic_launcher_background) + '/' +
                    resources.getResourceEntryName(R.drawable.ic_launcher_background)
        )
        val resultIntent = Intent()
        resultIntent.setData(imageUri)
        return Instrumentation.ActivityResult(Activity.RESULT_OK, resultIntent)
    }
}
