package com.sparshik.gradlegce;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

/**
 * Test class for GCE Async Task
 */
@RunWith(AndroidJUnit4.class)
public class TestAsyncGCE {
    @Test
    public void checkString() throws Exception {
        String result = null;
        Context appContext = InstrumentationRegistry.getTargetContext();

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(appContext);
        endpointsAsyncTask.execute();
        try {
            result = endpointsAsyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(result);
    }
}
