package com.wayne.cmoneytask.ui.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment


abstract class BaseFragment(@LayoutRes contentLayoutId: Int): Fragment(contentLayoutId) {
}