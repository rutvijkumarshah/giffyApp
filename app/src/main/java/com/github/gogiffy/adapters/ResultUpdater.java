package com.github.gogiffy.adapters;

import com.github.gogiffy.models.Gif;

import java.util.List;

/**
 * Created by Rutvijkumar Shah on 7/9/16.
 */
public interface ResultUpdater {
    /**
     *
     * Updates results by combining existing reuslt with inputs
     * @param gifs
     */
    void addAll(List<Gif> gifs);

    /**
     * Clears all results
     */
    void clearAll();
}
