package com.test.git.repos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.test.domain.ReposResponseModel
import com.test.domain.SearchRepoUseCase
import com.test.domain.Failure
import com.test.git.BaseViewModel
import com.test.git.Resource
import com.test.git.common.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(private val searchRepoUseCase: SearchRepoUseCase,
                                         private val savedStateHandle: SavedStateHandle
                                         ) : BaseViewModel() {

    val listRepos = ArrayList<ReposResponseModel>()

    val username = MutableLiveData<String>()
    val usernameError = MutableLiveData<String>()

    val responseSearch = SingleLiveEvent<Resource<List<ReposResponseModel>>>()


    fun isValid() :Boolean{
        var isValid = true
        if(username.value.isNullOrBlank()){
            usernameError.value = "Field requiered"
            isValid = false
        }else{
            usernameError.value = ""
            isValid = true
        }
        return isValid
    }

    fun searchAction(){
        responseSearch.postValue(Resource.loading())
        launch {
            val params = SearchRepoUseCase.Params(username.value!!)
            searchRepoUseCase.invoke(this,params){
                fun handleFailureSearch(failure: Failure) {
                    failure.exception.printStackTrace()
                    responseSearch.postValue(Resource.error("An error has occurred"))

                }

                fun handleSuccessSearch(repos: List<ReposResponseModel>) {
                    if(repos.isNotEmpty())
                        responseSearch.postValue(Resource.next(repos))
                    else
                        responseSearch.postValue(Resource.error("This user doesn't has any repos"))
                }
                it.either( ::handleFailureSearch,::handleSuccessSearch)
            }
        }
    }


}