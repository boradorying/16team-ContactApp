package com.example.contacts


object ContactsManager {
    val contactsList = mutableListOf<Contact>()
    init {
        val contact1 = Contact("박세준","010-9151-9326", "jemini9595@gmail.com", null, R.drawable.sejun,false,false)
        val contact2 = Contact("추민수","010-2817-9282", "softychoo@gmail.com", null, R.drawable.minsu,false,false)
        val contact3 = Contact("이승현","010-4924-4338", "shyr0809@gmail.com", null, R.drawable.seunghyun,false,false)
        val contact4 = Contact("남경화","010-5738-7117", "snoopn@naver.com", null, R.drawable.gyeonghwa,false,false)
        val contact5 = Contact("남라떼","010-1234-5678", "snoopn@naver.com", null, R.drawable.latte,false,false)
        val contact6 = Contact("박동이","010-7777-1234", "snoopn@naver.com", null, R.drawable.donge,false,false)
        val contact7 = Contact("박뽀시","010-1234-7777", "snoopn@naver.com", null, R.drawable.bbosi,false,false)
        val contact8 = Contact("김민수","010-5738-7117", "snoopn@naver.com", null, R.drawable.minsu2,false,false)
        val contact9 = Contact("조경화","010-5738-7117", "snoopn@naver.com", null, R.drawable.gyeonghwa2,false,false)
        val contact10 = Contact("차승현","010-5738-7117", "snoopn@naver.com", null, R.drawable.seounghyun2,false,false)
        val contact11 = Contact("황세준","010-5738-7117", "snoopn@naver.com", null, R.drawable.sejun2,false,false)

        contactsList.addAll(listOf( contact1,contact2,contact3,contact4,contact5,contact6,contact7,contact8,contact9,contact10,contact11))

        contactsList.sortBy { it.name }
    }

}