GoGiffy
===========
GoGiffy is an android app to search images from Giphy’s API.


## Requirements:
A simple Android app, which launches to show a screen of the currently trending GIFs from Giphy’s API in a Grid format.
Just above the images there should be a search field on the screen where the user can start typing a search string and the images change to reflect the currently entered search text.
When one of the images in the grid is tapped, the screen moves to show the image full screen with an overlay of text at the bottom, which shows the image URL.


## Assumptions:
- For search filed separate EditText is used instead of SearchView in ActionBar
- Fullscreen of Image is scaled with adjusting view bounds so image looks normal (not streched ).
- Not used Dagger/ButterKnife because of this simple UI, which are otherwise important opensource lib for app.

## Third Party Utilities/source used for building this app:

 1. Retrofit
 2. Okhttp
 3. Picasso
 4. Leakcanary
 5. Espresso-contrib    ( To test Recyclerview)
 6. RecyclerViewMatcher ( To test Recyclerview)
 7. EndlessRecyclerViewScrollListener

## Limitations
-  Currently App does not support animating gif.
