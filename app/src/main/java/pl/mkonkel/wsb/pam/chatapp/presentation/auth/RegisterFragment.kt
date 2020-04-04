package pl.mkonkel.wsb.pam.chatapp.presentation.auth

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_base_auth.*
import pl.mkonkel.wsb.pam.chatapp.domain.AuthService
import pl.mkonkel.wsb.pam.chatapp.repository.Repository

class RegisterFragment : BaseAuthFragment() {
    private val authService = AuthService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hello_text.text = "Hello!\nPlease create your awesome account!"
        submit_button.setOnClickListener {
            if (validate()) {
                login()
            }
        }
    }

    private fun login() {
        toggleProgress()

        authService.register(
            email(),
            password(),
            authCallback
        )
    }

    private val authCallback = object : Repository.AuthCallback {
        override fun onSuccess() {
            toggleProgress()
            fragmentListener?.onSuccess()
        }

        override fun onFailure() {
            toggleProgress()
            fragmentListener?.onFailure()
        }
    }
}