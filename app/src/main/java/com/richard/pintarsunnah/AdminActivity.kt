package com.richard.pintarsunnah

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.richard.pintarsunnah.databinding.ActivityAdminBinding
import com.richard.pintarsunnah.model.PelajaranModel

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding

    private lateinit var dRef : DatabaseReference
    private lateinit var fStor: FirebaseStorage
    private lateinit var sRef: StorageReference

    private lateinit var imageUri: Uri
    private lateinit var imageUri2: Uri
    private lateinit var uploadTask: UploadTask
    private lateinit var uploadTask2: UploadTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fStor = FirebaseStorage.getInstance("gs://tabbpvp2022-5bdca.appspot.com/")
        sRef = fStor.reference

        dRef = FirebaseDatabase.getInstance().reference

        binding.apply {
            ibGambar.setOnClickListener { selectImage() }
            ibTerjemahan.setOnClickListener { selectImage() }

            BtnKirim.setOnClickListener {
                val judul = EtJudul.text.toString()

                if (judul.isEmpty()){
                    Toast.makeText(this@AdminActivity, "isi judul", Toast.LENGTH_LONG).show()
                } else {
                    uploadImage(judul, imageUri, imageUri2)
                    Toast.makeText(this@AdminActivity, "Berhasil disimpan", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.data != null) {
            imageUri = data.data!!
            binding.ibGambar.setImageURI(imageUri)
        } else if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.data != null){
            imageUri = data.data!!
            binding.ibTerjemahan.setImageURI(imageUri)
        }
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 1)
    }

    private fun uploadImage(judul : String, imageUri: Uri, imageUri2: Uri) {
        val imageRef = sRef.child("gambar/${judul}.png")
        uploadTask = imageRef.putFile(imageUri)

        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            imageRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                val gambarAyat = downloadUri.toString()
                binding.ibGambar.setImageResource(R.drawable.ic_baseline_camera_alt_24)
                binding.ibGambar.setOnClickListener { selectImage() }

                dRef.child("data_pelajaran").child(judul).setValue(PelajaranModel(judul, gambar = gambarAyat))
                Toast.makeText(this, "Gambar berhasil diupload", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Gambar gagal diupload", Toast.LENGTH_LONG).show()
            }
        }

        val imageRef2 = sRef.child("gambar/${judul}.png")
        uploadTask2 = imageRef2.putFile(imageUri2)
        uploadTask2.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            imageRef2.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                val gambarDetail = downloadUri.toString()
                binding.ibTerjemahan.setImageResource(R.drawable.ic_baseline_camera_alt_24)
                binding.ibTerjemahan.setOnClickListener { selectImage() }

                dRef.child("data_pelajaran").child(judul).setValue(PelajaranModel(judul, detail = gambarDetail))
                Toast.makeText(this, "Gambar berhasil diupload", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Gambar gagal diupload", Toast.LENGTH_LONG).show()
            }
        }
    }
}