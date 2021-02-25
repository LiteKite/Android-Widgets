/*
 * Copyright 2021 LiteKite Startup. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.litekite.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * This ViewGroup contains a single view, which will be rotated by 90 degrees counterclockwise.
 *
 * <p>This implementation was referenced from Android Open Source Project's MusicFX Android App:
 * aosp_root/packages/apps/MusicFX/src/com/android/musicfx/SeekBarRotator.java</p>
 *
 * @author Vignesh S
 * @version 1.0, 03/11/2020
 * @since 1.0
 */
public class ViewRotator @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

	override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
		val childView = getChildAt(0)
		if (childView != null && childView.visibility != GONE) {
			// swap width and height for child
			measureChild(childView, heightMeasureSpec, widthMeasureSpec)
			setMeasuredDimension(childView.measuredHeightAndState, childView.measuredWidthAndState)
		} else {
			setMeasuredDimension(
				resolveSizeAndState(0, widthMeasureSpec, 0),
				resolveSizeAndState(0, heightMeasureSpec, 0)
			)
		}
	}

	override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
		val childView = getChildAt(0)
		if (childView != null && childView.visibility != GONE) {
			// rotate the child 90 degrees counterclockwise around its upper-left
			childView.pivotX = 0f
			childView.pivotY = 0f
			childView.rotation = -90f
			// place the child below this view, so it rotates into view
			val width = r - l
			val height = b - t
			childView.layout(0, height, height, width + height)
		}
	}

}