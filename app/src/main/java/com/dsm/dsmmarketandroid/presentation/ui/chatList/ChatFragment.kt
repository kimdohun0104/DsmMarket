package com.dsm.dsmmarketandroid.presentation.ui.chatList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.trash_model.ChatRoomModel
import com.dsm.dsmmarketandroid.presentation.ui.adapter.ChatRoomListAdapter
import kotlinx.android.synthetic.main.fragment_chat.view.*

class ChatFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_chat, container, false)
        activity?.setTitle(R.string.chatting)

        val adapter = ChatRoomListAdapter()
        rootView.rv_chat_room.adapter = adapter
        adapter.addItems(
            listOf(
                ChatRoomModel("https://t1.daumcdn.net/news/201805/13/tvreport/20180513145934483utso.jpg", "안녕하세요", "기억이 나질 않습니다."),
                ChatRoomModel("https://t1.daumcdn.net/news/201805/13/tvreport/20180513145934483utso.jpg", "안녕하세요", "기억이 나질 않습니다."),
                ChatRoomModel("https://t1.daumcdn.net/news/201805/13/tvreport/20180513145934483utso.jpg", "안녕하세요", "기억이 나질 않습니다."),
                ChatRoomModel("https://t1.daumcdn.net/news/201805/13/tvreport/20180513145934483utso.jpg", "안녕하세요", "기억이 나질 않습니다."),
                ChatRoomModel("https://t1.daumcdn.net/news/201805/13/tvreport/20180513145934483utso.jpg", "안녕하세요", "기억이 나질 않습니다.")
            )
        )

        return rootView
    }
}