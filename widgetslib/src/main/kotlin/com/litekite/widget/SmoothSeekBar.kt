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

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatSeekBar
import kotlin.math.abs
import kotlin.math.roundToInt

/**
 * @author Vignesh S
 * @version 1.0, 06/11/2020
 * @since 1.0
 */
public class SmoothSeekBar @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyleAttr: Int = 0
) : AppCompatSeekBar(context, attrs, defStyleAttr) {

	private var callback: OnSeekBarChangeListener? = null
	private var lastPointerId: Int = 0
	private var lastProgress = 0

	@SuppressLint("ClickableViewAccessibility")
	override fun onTouchEvent(event: MotionEvent): Boolean {
		if (!isEnabled) {
			return false
		}
		when (event.action) {
			MotionEvent.ACTION_DOWN -> {
				lastProgress = calculateProgress(event)
				callback?.onStartTrackingTouch(this)
				lastPointerId = event.getPointerId(event.actionIndex)
			}
			MotionEvent.ACTION_MOVE -> {
				isPressed = true
				// Updates last known progress based on the active pointer id
				// in-case of any multi-touch event.
				val currentPointerId = event.getPointerId(event.actionIndex)
				if (lastPointerId != currentPointerId) {
					lastProgress = calculateProgress(event)
					lastPointerId = currentPointerId
					return true
				}
				val newProgress = calculateProgress(event)
				makeProgress(newProgress)
				callback?.onProgressChanged(this, progress, true)
				lastProgress = newProgress
			}
			MotionEvent.ACTION_UP -> {
				isPressed = false
				performClick()
				callback?.onStopTrackingTouch(this)
			}
			MotionEvent.ACTION_CANCEL -> {
				isPressed = false
				callback?.onStopTrackingTouch(this)
			}
		}
		return true
	}

	override fun setOnSeekBarChangeListener(l: OnSeekBarChangeListener?) {
		callback = l
	}

	override fun getMin(): Int {
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
			return super.getMin()
		}
		return 0
	}

	private fun makeProgress(newProgress: Int) {
		val displacement = lastProgress - newProgress
		val currentProgress = if (displacement < 0) {
			// Progress increased
			progress + abs(displacement)
		} else {
			// Progress reduced
			progress - abs(displacement)
		}
		// Setting current progress
		progress = when {
			progress > max -> {
				max
			}
			progress < min -> {
				min
			}
			else -> {
				currentProgress
			}
		}
	}

	private fun calculateProgress(event: MotionEvent): Int {
		val scale = event.x / width
		val range = max - min
		return (scale * range + min).roundToInt()
	}

}