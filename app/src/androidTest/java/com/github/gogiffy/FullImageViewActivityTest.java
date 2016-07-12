package com.github.gogiffy;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.github.gogiffy.activities.FullImageViewActivity;
import com.github.gogiffy.data.TestData;
import com.github.gogiffy.util.Constants;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.github.gogiffy.utils.TestUtils.waitForGiphyData;

/**
 * Created by Rutvijkumar Shah on 7/10/16.
 */
@RunWith(AndroidJUnit4.class)
public class FullImageViewActivityTest {


    @Rule
    public final ActivityTestRule<FullImageViewActivity> fullImageActivity=new ActivityTestRule<FullImageViewActivity>(FullImageViewActivity.class){
        @Override
        protected void afterActivityLaunched() {
            super.afterActivityLaunched();
            waitForGiphyData();
        }

        @Override
        protected Intent getActivityIntent(){
            Intent intent = new Intent(getTargetContext(), FullImageViewActivity.class);
            intent.putExtra(Constants.EXTRA_FULL_IMG_URL, TestData.FULL_IMAGE_URL);
            return intent;
        }

    };


    @Test
    public void shouldBeAbleToDisplayImage(){
        onView(withId(R.id.imgFullScreenImage))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldBeAbleToDisplayUrl(){
        onView(withId(R.id.tvURL))
                .check(matches(isDisplayed()));

    }


}
