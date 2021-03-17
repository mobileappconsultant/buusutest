package com.android.countrysearch.core.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope

val Fragment.viewScope: LifecycleCoroutineScope
    get() = this.viewLifecycleOwner.lifecycleScope
