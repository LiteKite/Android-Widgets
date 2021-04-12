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
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.graphics.drawable.toBitmap

/**
 * @author Vignesh S
 * @version 1.0, 22/07/2020
 * @since 1.0
 */
public class CircleImageButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageButton(context, attrs, defStyleAttr) {

    private var innerPadding = 0
    private var bgX = 0f
    private var bgY = 0f
    private val bgRectF = RectF()
    private val bgPaint = Paint().apply {
        isAntiAlias = true
    }
    private var bgDrawable: Drawable? = null
    private var rippleDrawable: Drawable? = null
    private var bgBitmap: Bitmap? = null
    private var imgX = 0f
    private var imgY = 0f
    private val imgRectF = RectF()
    private val imgPaint = Paint().apply {
        isAntiAlias = true
    }
    private var imgBitmap: Bitmap? = null

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImageButton)
        innerPadding = typedArray.getDimensionPixelSize(
            R.styleable.CircleImageButton_innerPadding,
            0
        )
        rippleDrawable = typedArray.getDrawable(R.styleable.CircleImageButton_rippleDrawable)
        // Recycles the TypedArray
        typedArray.recycle()
        // Sets ripple drawable as background
        super.setBackground(rippleDrawable)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateSizes()
        updateBgDrawable()
        updateImgDrawable()
        postInvalidate()
    }

    private fun updateSizes() {
        // Background coordinates
        bgX = width / 2f
        bgY = height / 2f
        // Background rect bounds
        bgRectF.left = 0f
        bgRectF.top = 0f
        bgRectF.right = width.toFloat()
        bgRectF.bottom = height.toFloat()
        // Source coordinates
        imgX = (width - innerPadding / 2f)
        imgY = (height - innerPadding / 2f)
        // Source rect bounds
        imgRectF.left = innerPadding + 0f
        imgRectF.top = innerPadding + 0f
        imgRectF.right = width.toFloat() - innerPadding
        imgRectF.bottom = height.toFloat() - innerPadding
    }

    override fun setBackground(background: Drawable?) {
        super.setBackground(if (rippleDrawable is RippleDrawable) rippleDrawable else null)
        bgDrawable = background
        postInvalidate()
    }

    override fun setForeground(foreground: Drawable?) {
        super.setForeground(null)
    }

    override fun setImageDrawable(drawable: Drawable?) {
        super.setImageDrawable(drawable)
        postInvalidate()
    }

    private fun getBitmap(drawable: Drawable?, rectF: RectF): Bitmap? {
        return drawable?.toBitmap(
            rectF.width().toInt(),
            rectF.height().toInt(),
            Bitmap.Config.ARGB_8888
        )
    }

    private fun updateBgDrawable() {
        bgBitmap = getBitmap(bgDrawable, bgRectF)
        updateBitmapShader(bgBitmap, bgRectF, bgPaint)
    }

    private fun updateImgDrawable() {
        imgBitmap = getBitmap(drawable, imgRectF)
        updateBitmapShader(imgBitmap, imgRectF, imgPaint)
    }

    private fun updateBitmapShader(bitmap: Bitmap?, rectF: RectF, paint: Paint) {
        bitmap?.let {
            val imgShaderMatrix = Matrix()
            // setup shader matrix
            imgShaderMatrix.setTranslate(rectF.left, rectF.top)
            val imgBitmapShader = BitmapShader(
                bitmap,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
            )
            imgBitmapShader.setLocalMatrix(imgShaderMatrix)
            paint.shader = imgBitmapShader
        }
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) {
            return
        }
        // Drawing Background
        if (bgDrawable != null) {
            canvas.drawRoundRect(bgRectF, bgX, bgY, bgPaint)
        }
        // Drawing Source
        if (drawable != null) {
            canvas.drawRoundRect(imgRectF, imgX, imgY, imgPaint)
        }
    }
}
