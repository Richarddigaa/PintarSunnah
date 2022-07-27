package com.richard.pintarsunnah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.richard.pintarsunnah.adapter.PelajaranAdapter
import com.richard.pintarsunnah.databinding.ActivityListPelajaranBinding
import com.richard.pintarsunnah.model.PelajaranModel

class ListPelajaranActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListPelajaranBinding
    private lateinit var listPelajaran : ArrayList<PelajaranModel>
    private lateinit var myAdapter : PelajaranAdapter

    private lateinit var dRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListPelajaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listPelajaran = arrayListOf()
        dRef = FirebaseDatabase.getInstance().reference

        myAdapter = PelajaranAdapter(listPelajaran)

        binding.RvListPelajaran.adapter = myAdapter
        binding.RvListPelajaran.layoutManager = LinearLayoutManager(this)
        binding.RvListPelajaran.setHasFixedSize(true)

        getListData()
    }

    private fun getListData() {
        dRef.child("data_pelajaran").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (listUserSnapshot in snapshot.children) {
                        val listUser = listUserSnapshot.getValue(PelajaranModel::class.java)
                        listPelajaran.add(listUser!!)
                    }
                    myAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}