/*
 * Copyright 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.support.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * a customized scroll view can be used for quick return tricks.
 */
public class QuickReturnScrollView extends ScrollView {
  private Callbacks callbacks;

  public QuickReturnScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onScrollChanged(int l, int t, int oldl, int oldt) {
    super.onScrollChanged(l, t, oldl, oldt);
    if (callbacks != null) {
      callbacks.onScrollChanged(t);
    }
  }

  @Override
  public int computeVerticalScrollRange() {
    return super.computeVerticalScrollRange();
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    if (callbacks != null) {
      switch (ev.getActionMasked()) {
        case MotionEvent.ACTION_DOWN:
          callbacks.onDownMotionEvent();
          break;
        case MotionEvent.ACTION_UP:
          // fall through
        case MotionEvent.ACTION_CANCEL:
          callbacks.onUpOrCancelMotionEvent();
          break;
        default:
          break;
      }
    }
    return super.onTouchEvent(ev);
  }

  public void setCallbacks(Callbacks callbacks) {
    this.callbacks = callbacks;
  }

  public static interface Callbacks {
    int STATE_ON_SCREEN = 0;
    int STATE_OFF_SCREEN = 1;
    int STATE_RETURNING = 2;

    void onScrollChanged(int scrollY);

    void onDownMotionEvent();

    void onUpOrCancelMotionEvent();
  }
}