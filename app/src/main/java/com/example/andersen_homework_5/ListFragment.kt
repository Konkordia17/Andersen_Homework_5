package com.example.andersen_homework_5

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener

class ListFragment : Fragment(R.layout.fragment_list) {
    private lateinit var contact1: LinearLayout
    private lateinit var contact2: LinearLayout
    private lateinit var contact3: LinearLayout
    private lateinit var nameContact1: TextView
    private lateinit var surnameContact1: TextView
    private lateinit var phoneContact1: TextView
    private lateinit var nameContact2: TextView
    private lateinit var surnameContact2: TextView
    private lateinit var phoneContact2: TextView
    private lateinit var nameContact3: TextView
    private lateinit var surnameContact3: TextView
    private lateinit var phoneContact3: TextView
    private var listUser = mutableListOf(
        User(
            "Иван",
            "Петров",
            "945894759832",
            1
        ), User(
            "Сергей",
            "Собянин",
            "1488",
            2
        ), User(
            "Семен",
            "Слепаков",
            "13",
            3
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(
            "requestKey",
        ) { _, bundle ->
            val user = bundle.getParcelable<User>("bundleKey")
            when (user?.id) {
                1 -> updateFirstUser(user)
                2 -> updateSecondUser(user)
                3 -> updateThirdUser(user)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("key", ArrayList(listUser))
    }

    private fun updateThirdUser(user: User) {
        nameContact3.text = user.name
        surnameContact3.text = user.surName
        phoneContact3.text = user.phone
        listUser[2] = user
    }

    private fun updateSecondUser(user: User) {
        nameContact2.text = user.name
        surnameContact2.text = user.surName
        phoneContact2.text = user.phone
        listUser[1] = user
    }

    private fun updateFirstUser(user: User) {
        nameContact1.text = user.name
        surnameContact1.text = user.surName
        phoneContact1.text = user.phone
        listUser[0] = user
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val users = savedInstanceState?.getParcelableArrayList<User>("key")
        users?.let { listUser = it.toMutableList() }
        contact1 = requireView().findViewById(R.id.contact1)
        contact2 = requireView().findViewById(R.id.contact2)
        contact3 = requireView().findViewById(R.id.contact3)
        nameContact1 = requireView().findViewById(R.id.nameContact1)
        surnameContact1 = requireView().findViewById(R.id.secondNameContact1)
        phoneContact1 = requireView().findViewById(R.id.phoneContact1)
        nameContact2 = requireView().findViewById(R.id.nameContact2)
        surnameContact2 = requireView().findViewById(R.id.secondNameContact2)
        phoneContact2 = requireView().findViewById(R.id.phoneContact2)
        nameContact3 = requireView().findViewById(R.id.nameContact3)
        surnameContact3 = requireView().findViewById(R.id.secondNameContact3)
        phoneContact3 = requireView().findViewById(R.id.phoneContact3)

        updateFirstUser(listUser[0])
        updateSecondUser(listUser[1])
        updateThirdUser(listUser[2])

        contact1.setOnClickListener {
            (activity as? Navigate)?.navigateTo(
                DetailInfoFragment.newInstance(
                    User(
                        name = nameContact1.text.toString(),
                        surName = surnameContact1.text.toString(),
                        phone = phoneContact1.text.toString(),
                        id = 1
                    )
                ), true
            )
        }

        contact2.setOnClickListener {
            (activity as? Navigate)?.navigateTo(
                DetailInfoFragment.newInstance(
                    User(
                        nameContact2.text.toString(),
                        surnameContact2.text.toString(),
                        phoneContact2.text.toString(),
                        2
                    )
                ), true
            )
        }

        contact3.setOnClickListener {
            (activity as? Navigate)?.navigateTo(
                DetailInfoFragment.newInstance(
                    User(
                        nameContact3.text.toString(),
                        surnameContact3.text.toString(),
                        phoneContact3.text.toString(), 3
                    )
                ), true
            )
        }
    }
}




