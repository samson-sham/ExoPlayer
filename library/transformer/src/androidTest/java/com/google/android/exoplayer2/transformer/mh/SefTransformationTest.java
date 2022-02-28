/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.exoplayer2.transformer.mh;

import static com.google.android.exoplayer2.transformer.AndroidTestUtil.SEF_ASSET_URI_STRING;
import static com.google.android.exoplayer2.transformer.AndroidTestUtil.runTransformer;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.google.android.exoplayer2.transformer.TransformationRequest;
import com.google.android.exoplayer2.transformer.Transformer;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import org.junit.Test;
import org.junit.runner.RunWith;

/** {@link Transformer} instrumentation test for SEF. */
@RunWith(AndroidJUnit4.class)
public class SefTransformationTest {

  private static final String TAG = "SefTransformationTest";

  @Test
  public void sefTransform() throws Exception {
    if (Util.SDK_INT < 25) {
      // TODO(b/210593256): Remove test skipping after removing the MediaMuxer dependency.
      Log.i(TAG, "Skipping on this API version due to lack of muxing support");
      return;
    }

    Context context = ApplicationProvider.getApplicationContext();
    Transformer transformer =
        new Transformer.Builder(context)
            .setTransformationRequest(
                new TransformationRequest.Builder().setFlattenForSlowMotion(true).build())
            .build();
    runTransformer(
        context,
        /* testId = */ "sefTransform",
        transformer,
        SEF_ASSET_URI_STRING,
        /* timeoutSeconds= */ 120,
        /* calculateSsim= */ false);
  }
}
