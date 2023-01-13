package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

open class ActivityMainViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
        get() = _shoe

    private val _errMsg = MutableLiveData<String>()
    val errMsg: LiveData<String>
        get() = _errMsg

    private val _textChanged = MutableLiveData<String>()
    val textChanged: LiveData<String>
        get() = _textChanged

    private val _toShoeListScreenEvent = MutableLiveData<Boolean>()
    val toShoeListScreenEvent: LiveData<Boolean>
        get() = _toShoeListScreenEvent

    private val _toShoeDetailScreenEvent = MutableLiveData<Boolean>()
    val toShoeDetailScreenEvent: LiveData<Boolean>
        get() = _toShoeDetailScreenEvent

    init {
        _shoe.value = Shoe(0, "Nike Pro", 0.45, "NIKE", "Sport Shoe")
        addNewShoe()
        _shoe.value = Shoe(1, "Adidas New", 0.42, "ADIDAS", "Blue football shoe for men")
        addNewShoe()
        clearDetailForm()
        _errMsg.value = ""
        _textChanged.value = ""
    }

    private fun clearDetailForm() {
        _shoe.value = Shoe(0, "", 0.0, "", "")
    }

    private fun validateInput(): Boolean {
        if (_shoe.value?.name?.isEmpty() == true || _shoe.value?.name?.isBlank() == true) {
            _errMsg.value = "please enter shoe name"
            return false
        }
        if (_shoe.value?.company?.isEmpty() == true || _shoe.value?.company?.isBlank() == true) {
            _errMsg.value = "please enter company name"
            return false
        }
        if (_shoe.value?.size?.toString()?.isEmpty() == true || _shoe.value?.size?.toString()
                ?.isBlank() == true
        ) {
            _errMsg.value = "please enter shoe size"
            return false
        }
        if (_shoe.value?.description?.isEmpty() == true || _shoe.value?.description?.isBlank() == true) {
            _errMsg.value = "please enter shoe description"
            return false
        }
        return true
    }

    private fun addNewShoe() {
        if (shoe.value != null)
            _shoeList.addNewItem(shoe.value as Shoe)
    }

    fun onSaveBtnClickEvent() {
        if (validateInput()) {
            addNewShoe()
            _toShoeListScreenEvent.value = true
            clearDetailForm()
        }
    }

    fun onCancelBtnClickEvent() {
        _toShoeListScreenEvent.value = true
        clearDetailForm()
    }

    fun onTextChange(owner:String){
        _textChanged.value = owner
        _errMsg.value = ""
    }

    fun onToShoeListScreenComplete() {
        _toShoeListScreenEvent.value = false
    }

    fun onFABclickEvent() {
        _toShoeDetailScreenEvent.value = true
    }

    fun onToShoeDetailComplete() {
        _toShoeDetailScreenEvent.value = false
    }

    /**
     * Methods to Trigger The shoeList Observer
     */
    companion object {
        fun <T : Any> MutableLiveData<MutableList<T>>.addNewItem(item: T) {
            val oldValue = this.value ?: mutableListOf()
            oldValue.add(item)
            this.value = oldValue
        }

        fun <T> MutableLiveData<MutableList<T>>.addNewItemAt(index: Int, item: T) {
            val oldValue = this.value ?: mutableListOf()
            oldValue.add(index, item)
            this.value = oldValue
        }

        fun <T> MutableLiveData<MutableList<T>>.removeItemAt(index: Int) {
            if (!this.value.isNullOrEmpty()) {
                val oldValue = this.value
                oldValue?.removeAt(index)
                this.value = oldValue
            } else {
                this.value = mutableListOf()
            }
        }
    }

}