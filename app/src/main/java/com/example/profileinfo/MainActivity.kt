package com.example.profileinfo


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.profileinfo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var b:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        onSaveButtonCLick()
        onClearButtonCLick()

    }


    private fun onSaveButtonCLick(){
        b.btnSave.setOnClickListener {
            checkIfFieldsAreEmpty()
            isValidEmail()
            checkUserNameLength()
        }
    }

    private fun onClearButtonCLick(){
        b.btnClear.setOnLongClickListener{
            clearAllFields()
            true
        }
    }


    //    clears all the field inputs if they exist, and then clears the error messages too on the second hold.
    private fun clearAllFields(){
        if (
            b.etAge.text.isNullOrEmpty().not() ||
            b.etEmail.text.isNullOrEmpty().not() ||
            b.etUsername.text.isNullOrEmpty().not() ||
            b.etLastName.text.isNullOrEmpty().not() ||
            b.etFirstName.text.isNullOrEmpty().not())
        {
            b.etAge.text?.clear()
            b.etEmail.text?.clear()
            b.etUsername.text?.clear()
            b.etLastName.text?.clear()
            b.etFirstName.text?.clear()
            Toast.makeText(this,"hold again to clear everything",Toast.LENGTH_SHORT).show()
        }

        else if(
            b.etAge.text.isNullOrEmpty() ||
            b.etEmail.text.isNullOrEmpty() ||
            b.etUsername.text.isNullOrEmpty() ||
            b.etLastName.text.isNullOrEmpty()||
            b.etFirstName.text.isNullOrEmpty())
        {
            b.tvEmailWarning.isVisible = false
            b.tvFirstnameWarning.isVisible = false
            b.tvAgeWarning.isVisible = false
            b.tvLastnameWarning.isVisible = false
            b.tvUsernameWarning.isVisible = false
        }
    }

    //    checks whether firstname, lastname and age are empty
    private fun checkIfFieldsAreEmpty(){
        if (b.etAge.text.isNullOrEmpty()){
            b.tvAgeWarning.text = "ეს ველი უნდა იყოს შევსებული"
            b.tvAgeWarning.isVisible = true
        }
        else{
            b.tvAgeWarning.isVisible = false
        }

        if (b.etFirstName.text.isNullOrEmpty()){
            b.tvFirstnameWarning.text = "ეს ველი უნდა იყოს შევსებული"
            b.tvFirstnameWarning.isVisible = true
        }else{
            b.tvFirstnameWarning.isVisible = false
        }

        if (b.etLastName.text.isNullOrEmpty()){
            b.tvLastnameWarning.text = "ეს ველი უნდა იყოს შევსებული"
            b.tvLastnameWarning.isVisible = true
        }else{
            b.tvLastnameWarning.isVisible = false
        }

    }

    //    checks whether username length is more than 10 symbols (No need to check for emptiness because if its empty it's still less than 10)
    private fun checkUserNameLength(){
        if (b.etUsername.text.toString().length < 10){
            b.tvUsernameWarning.text = "მომხმარებლის სახელი არ უნდა შეიცავდეს 10-ზე ნაკლებ სიმბოლოს"
            b.tvUsernameWarning.isVisible = true
        }else{
            b.tvUsernameWarning.isVisible = false
        }
    }

    //    checks the email format validity.( no need to check for emptiness because if it's empty it's not valid email address)
    private fun isValidEmail() {

        if(Patterns.EMAIL_ADDRESS.matcher(b.etEmail.text.toString()).matches().not()){
            b.tvEmailWarning.text = "მეილი უნდა იყოს ამ ფორმატში: example@somemail.com"
            b.tvEmailWarning.isVisible = true
        }
        else{
            b.tvEmailWarning.isVisible = false
        }
    }


}