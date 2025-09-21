package com.example.sklad.ui.login

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.sklad.MainActivity
import com.example.sklad.R
import com.example.sklad.data.database.AppDatabase
import com.example.sklad.data.repository.UserRepositoryImpl
import com.example.sklad.databinding.FragmentLoginBinding
import com.example.sklad.databinding.FragmentProductListBinding
import com.example.sklad.domain.models.UserModel
import com.example.sklad.domain.models.UserRoleModel
import com.example.sklad.domain.repository.UserRepository
import com.example.sklad.domain.usecases.user.AddUserUseCase
import com.example.sklad.domain.usecases.user.GetUserByUsernameUseCase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    override fun onResume() {
        super.onResume()

        (activity as? MainActivity)?.findViewById<FloatingActionButton>(R.id.fab)?.hide()
    }

    private val database: AppDatabase by lazy {
        AppDatabase.getDatabase(requireContext())
    }
    private val repository: UserRepository by lazy {
        UserRepositoryImpl(database.userDao())
    }
    private val getUserByUsernameUseCase: GetUserByUsernameUseCase by lazy{
        GetUserByUsernameUseCase(repository)
    }

    private val viewModel: LoginViewModel by viewModels{
        LoginViewModelFactory(getUserByUsernameUseCase)
    }


    private lateinit var binding: FragmentLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launch {
            viewModel.user.collect { user ->
                if(user != null){
                    if(user.password == binding.passwordText.text.toString())
                        findNavController().navigate(R.id.action_loginFragment_to_productListFragment)

                }
            }
        }


        binding.loginButton.setOnClickListener {
            val username = binding.usernameText.text.toString()
            viewModel.loadUser(username)
        }
    }
}