package com.example.andersen_homework_5

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult

class DetailInfoFragment : Fragment(R.layout.fragment_detail_info) {
    private lateinit var contactName: EditText
    private lateinit var contactSurname: EditText
    private lateinit var contactPhone: EditText
    private lateinit var imageView: ImageView
    lateinit var changeButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactName = requireView().findViewById(R.id.contactName)
        contactSurname = requireView().findViewById(R.id.contactSurname)
        contactPhone = requireView().findViewById(R.id.contactPhone)
        changeButton = requireView().findViewById(R.id.changeContactButton)
        imageView = requireView().findViewById(R.id.contactImage)

        val user = arguments?.getParcelable(USER_KEY) ?: User(
            name = "",
            surName = "",
            phone = "",
            id = -1
        )
        contactName.setText(user.name)
        contactSurname.setText(user.surName)
        contactPhone.setText(user.phone)

        changeButton.setOnClickListener {
            val name = contactName.text.toString()
            val surName = contactSurname.text.toString()
            val phone = contactPhone.text.toString()
            val userResponse = User(name, surName, phone, user.id)

            setFragmentResult(
                "requestKey",
                bundleOf("bundleKey" to userResponse)
            )
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    companion object {
        private const val USER_KEY = "userKey"
        fun newInstance(user: User): DetailInfoFragment {
            return DetailInfoFragment().withArguments {
                putParcelable(USER_KEY, user)
            }
        }
    }
}