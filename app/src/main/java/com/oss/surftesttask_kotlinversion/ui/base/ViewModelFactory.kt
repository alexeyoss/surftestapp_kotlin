package com.oss.surftesttask_kotlinversion.ui.base

//
//@HiltViewModel
//class ViewModelFactory
//@Inject
//constructor(
//    var repo: Repository
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        val viewModel = when (modelClass) {
//            MoviesListViewModel::class.java -> {
//                MoviesListViewModel(repo)
//            }
//            else -> {
//                throw IllegalArgumentException("Cannot create instance of the ViewModel")
//            }
//        }
//        return viewModel as T
//    }
//}
//
//inline fun <reified VM : ViewModel> BaseFragment.screenVewModel() = viewModels<VM>{
//    ViewModelFactory()
//}