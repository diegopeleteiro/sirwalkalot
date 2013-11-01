package com.thoughtworks.beach.android;

/**
 * This interface provides a callback mechanism for the StepDetector.
 * 
 * This code is losely based on http://code.google.com/p/pedometer/
 * 
 *
 */
public interface StepListener {

    /**
     * Called when the StepDetector detects a step. Based on the sensitivity
     * setting.
     */
    public void onStep();
}
