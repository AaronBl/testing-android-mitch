package com.fintecimal.testingespresso.ui

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.fintecimal.testingespresso.R
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.fintecimal.testingespresso.ui.ImageViewHasDrawableMatcher.hasDrawable


@RunWith(AndroidJUnit4ClassRunner::class)
class CameraActivityTest{

    @get:Rule
    val intentsTestRule = IntentsTestRule(CameraActivity::class.java)

    @Test
    fun  test_cameraIntent() {

        // GIVEN
        val activityResult = createImageCaptureActivityResultStub()
        val expectedIntent: Matcher<Intent> =
            IntentMatchers.hasAction(MediaStore.ACTION_IMAGE_CAPTURE)
        Intents.intending(expectedIntent).respondWith(activityResult)

        // Execute and Verify
        Espresso.onView(withId(R.id.image))
            .check(ViewAssertions.matches(CoreMatchers.not(hasDrawable())))
        Espresso.onView(withId(R.id.button_launch_camera)).perform(ViewActions.click())
        Intents.intended(expectedIntent)
        Espresso.onView(withId(R.id.image)).check(ViewAssertions.matches(hasDrawable()))
    }

    private fun createImageCaptureActivityResultStub(): Instrumentation.ActivityResult? {
        val bundle = Bundle()
        bundle.putParcelable(
            KEY_IMAGE_DATA, BitmapFactory.decodeResource(
                intentsTestRule.getActivity().getResources(),
                R.drawable.ic_launcher_background
            )
        )
        val resultData = Intent()
        resultData.putExtras(bundle)
        return Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
    }
}