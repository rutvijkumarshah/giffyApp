package com.github.gogiffy;

import android.app.Activity;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.runner.AndroidJUnit4;

import com.github.gogiffy.activities.SearchActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Rutvijkumar Shah on 7/10/16.
 */
@RunWith(AndroidJUnit4.class)
public class SearchActivityTest {
    @Rule
    public final ActivityRule<SearchActivity> searchactivity=new ActivityRule<>(SearchActivity.class);

    @Test
    public void shouldBeAbleToLaunch(){

        onView(withText("GoGiffy")).check(ViewAssertions.matches(isDisplayed()));
    }
}
