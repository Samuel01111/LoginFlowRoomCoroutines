package br.com.douglasmotta.naivagtioncomponentappmirror.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.douglasmotta.naivagtioncomponentappmirror.R
import br.com.douglasmotta.naivagtioncomponentappmirror.data.db.AppDataBase
import br.com.douglasmotta.naivagtioncomponentappmirror.data.repository.UserDbDataSource
import br.com.douglasmotta.naivagtioncomponentappmirror.ui.login.LoginViewModel
import br.com.douglasmotta.naivagtioncomponentappmirror.ui.registration.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()

    private val registrationViewModel: RegistrationViewModel by activityViewModels(
        factoryProducer = {
            val database = AppDataBase.getDatabase(requireContext())

            RegistrationViewModel.RegistrationViewModelFactory(
                userRepository = UserDbDataSource(database.userDao())
            )
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        viewModel.authenticationStateEvent.observe(this, Observer { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.Authenticated -> {
                    val user = registrationViewModel.getUserNameById(1)
                    textProfileWelcome.text = getString(R.string.profile_text_welcome, user?.name)
                }
                LoginViewModel.AuthenticationState.Unauthenticated -> {
                    navController.navigate(R.id.loginFragment)
                }
            }
        })
    }

}
