package com.oss.surftesttask_kotlinversion.ui.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    abstract val mViewModel: BaseViewModel
}