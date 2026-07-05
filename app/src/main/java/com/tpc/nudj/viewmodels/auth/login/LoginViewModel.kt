package com.tpc.nudj.viewmodels.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpc.nudj.model.AuthResult
import com.tpc.nudj.model.enums.Role
import com.tpc.nudj.repository.auth.AuthRepository
import com.tpc.nudj.ui.navigation.ScreenRoute
import com.tpc.nudj.ui.screen.auth.login.LoginUiState
import com.tpc.nudj.utils.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    fun onEmailChange(email: String) {
        _loginUiState.update {
            it.copy(email = email)
        }
    }

    fun onPasswordChange(password: String) {
        _loginUiState.update {
            it.copy(password = password)
        }


    }
    fun togglePasswordVisibility() {
        _loginUiState.update {
            it.copy(
                passwordVisible = !it.passwordVisible
            )
        }
    }
    fun onLoginClick() {
        //        viewModelScope.launch {
//            _loginUiState.update { it.copy(isLoading = true) }
//            delay(2000)
//            _loginUiState.update { it.copy(isLoading = false) }
//        }
        val currentEmail=_loginUiState.value.email
        Validator.isValidEmail(currentEmail,
            {performFirebaseLogin()},
            {errorMessage -> displayErrorMessage(errorMessage)}
        )
    }
    fun performFirebaseLogin(){
        if(_loginUiState.value.password.isBlank()){
            displayErrorMessage("Password can't be empty")
            return
        }
        viewModelScope.launch {
            val email = _loginUiState.value.email
            val password = _loginUiState.value.password
            authRepository.signInWithEmailAndPassword(email,password).collect {status ->
                when(status){
                    AuthResult.Loading -> {
                        _loginUiState.update { it.copy(isLoading = true) }
                    }
                    AuthResult.Initial -> {}
                    is AuthResult.VerificationNeeded -> {
                        _loginUiState.update { it.copy(isLoading = false,
                            navigateToRoute = ScreenRoute.Auth.EmailVerification)
                        }
                    }
                    is AuthResult.Success -> {
                        val selectedUiRole = _loginUiState.value.role
                        val registeredDatabaseRole = status.user.role
                        if(selectedUiRole!=registeredDatabaseRole){
                            authRepository.signOut()
                            _loginUiState.update{
                                it.copy(isLoading = false)
                            }
                            displayErrorMessage("Unauthorized. Please use the correct Portal layout role to login.")
                        }
                        else{
                            _loginUiState.update {
                                it.copy(isLoading = false)
                            }
                        }
                    }
                    is AuthResult.Error -> {
                        _loginUiState.update { it.copy(isLoading = false) }
                        displayErrorMessage(status.message)
                    }
                }
            }

        }
    }
    fun onNavigationHandled(){
        _loginUiState.update{
            it.copy(navigateToRoute = null)
        }
    }
    fun displayErrorMessage(errorMessage: String){
        _loginUiState.update{
            it.copy(errorMessage=errorMessage)
        }
    }
    fun clearError(){
        _loginUiState.update{
            it.copy(
                errorMessage = null
            )
        }
    }

    fun onForgotPasswordClick() {}

    fun onGoogleClick() {}

    fun onRoleSelected(role: Role) {
        _loginUiState.update {
            it.copy(role = role)
        }
    }

}