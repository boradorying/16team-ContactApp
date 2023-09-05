package com.example.contacts

object ContactsManager {
    val contactsList = mutableListOf<Contact>()

init {
    val contact1 = Contact("박세준","010-9151-9326", "jemini9595@gmail.com",R.drawable.sejun,false)
    val contact2 = Contact("추민수","010-2817-9282", "softychoo@gmail.com",R.drawable.minsu,false)
    val contact3 = Contact("이승현","010-4924-4338", "shyr0809@gmail.com",R.drawable.seunghyun,false)
    val contact4 = Contact("남경화","010-5738-7117", "snoopn@naver.com",R.drawable.gyeonghwa,false)
    val contact5 = Contact("남라떼","010-1234-5678", "snoopn@naver.com",R.drawable.latte,false)
    val contact6 = Contact("박동이","010-7777-1234", "snoopn@naver.com",R.drawable.donge,false)
    val contact7 = Contact("박뽀시","010-1234-7777", "snoopn@naver.com",R.drawable.bbosi,false)
    val contact8 = Contact("김민수","010-5738-7117", "snoopn@naver.com",R.drawable.minsu2,false)
    val contact9 = Contact("조경화","010-5738-7117", "snoopn@naver.com",R.drawable.gyeonghwa2,false)
    val contact10 = Contact("차승현","010-5738-7117", "snoopn@naver.com",R.drawable.seounghyun2,false)
    val contact11 = Contact("황세준","010-5738-7117", "snoopn@naver.com",R.drawable.sejun2,false)
    val contact12 = Contact("황세준","010-5738-7117", "snoopn@naver.com",R.drawable.sejun2,false)



    contactsList.addAll(listOf( contact1,contact2,contact3,contact4,contact5,contact6,contact7,contact8,contact9,contact10,contact11,contact12))
    contactsList.sortBy { it.name }
}

    fun addContact(name: String, phoneNumber: String, email: String, event: String) {
        val newContact = Contact(name, phoneNumber, email, R.drawable.me, false)
        contactsList.add(newContact)
        contactsList.sortBy { it.name }
    }

}
