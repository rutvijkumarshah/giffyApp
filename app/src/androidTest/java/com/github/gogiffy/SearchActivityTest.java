package com.github.gogiffy;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.github.gogiffy.activities.SearchActivity;
import com.github.gogiffy.data.TestData;
import com.github.gogiffy.util.Constants;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.github.gogiffy.utils.TestUtils.waitForGiphyData;
import static com.github.gogiffy.utils.TestUtils.withRecyclerView;

/**
 * Created by Rutvijkumar Shah on 7/10/16.
 */
@RunWith(AndroidJUnit4.class)
public class SearchActivityTest {


    @Rule
    public final ActivityTestRule<SearchActivity> searchactivity=new ActivityTestRule<SearchActivity>(SearchActivity.class){
        @Override
        protected void afterActivityLaunched() {
            super.afterActivityLaunched();
            //On load of Activity Trending gifs will be loaded
            //wait will make sure the data is loaded before any test executes
            waitForGiphyData();
        }
    };


    @Test
    public void shouldBeAbleToLaunch(){
        Context context = InstrumentationRegistry.getTargetContext();
        String appName=context.getResources().getString(R.string.app_name);
        onView(withText(appName)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldBeAbleToDisplayTrendingGifs(){
        //Verify that RecyclerView exists and displayed
        onView(withId(R.id.rvGifs)).check(matches(isDisplayed()));

        //Verify Results displayed at first position.
        onView(withRecyclerView(R.id.rvGifs).atPositionOnView(1,R.id.imgGif)).check(matches(isDisplayed()));


        //Verify results after scrolling
        onView(withId(R.id.rvGifs)).perform(RecyclerViewActions.scrollToPosition(Constants.PAGE_SIZE));

        onView(withRecyclerView(R.id.rvGifs).atPositionOnView(5,R.id.imgGif)).check(matches(isDisplayed()));

    }


    @Test
    public void shouldBeAbleSearchAndHaveResultsVisibleOnRecyclerView(){
        onView(withId(R.id.etSearchGif)).check(matches(isDisplayed()));
        onView(withId(R.id.etSearchGif)).perform(typeText("cat"));
        Espresso.closeSoftKeyboard();

        waitForGiphyData();

        //Verify that RecyclerView exists and displayed
        onView(withId(R.id.rvGifs)).check(matches(isDisplayed()));


        //Verify Results displayed at first position.
        onView(withRecyclerView(R.id.rvGifs).atPositionOnView(1,R.id.imgGif)).check(matches(isDisplayed()));


        //Verify results after scrolling
        onView(withId(R.id.rvGifs)).perform(RecyclerViewActions.scrollToPosition(Constants.PAGE_SIZE));
        onView(withRecyclerView(R.id.rvGifs).atPositionOnView(5,R.id.imgGif)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldNotDisplayAnyResultsWhenSearchedWithNonExistingData(){
        onView(withId(R.id.etSearchGif)).check(matches(isDisplayed()));
        onView(withId(R.id.etSearchGif)).perform(typeText(TestData.NO_DATA_FOR_THIS_KEYWORD));
        Espresso.closeSoftKeyboard();

        waitForGiphyData();
        //onView(withRecyclerView(R.id.rvGifs).atPositionOnView(40, R.id.imgGif)).check(doesNotExist());

        onView(withId(R.id.imgGif)).check(doesNotExist());

    }

    @Test
    public void shouldBeAbleLaunchFullScreenActivityOnClickOfImage(){
        //waitForGiphyData();
        onView(withId(R.id.rvGifs)).perform(RecyclerViewActions.actionOnItemAtPosition(1,click()));

        waitForGiphyData();
        onView(withId(R.id.tvURL))
                .check(matches(isDisplayed()));

        onView(withId(R.id.imgFullScreenImage))
                .check(matches(isDisplayed()));
    }



}
