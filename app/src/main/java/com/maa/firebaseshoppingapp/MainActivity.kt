package com.maa.firebaseshoppingapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.maa.firebaseshoppingapp.adapter.CategoryAdapter
import com.maa.firebaseshoppingapp.model.Category

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val search= findViewById<TextView>(R.id.main_search)
        val shapeable= findViewById<ShapeableImageView>(R.id.main_shapeable)
        val recyclerView= findViewById<RecyclerView>(R.id.main_recyclerView)

        shapeable.shapeAppearanceModel= ShapeAppearanceModel().toBuilder().setAllCorners(CornerFamily.ROUNDED, 20F).build()

        val itemList= ArrayList<Category>()
        val adapter= CategoryAdapter(itemList)

        setupRecyclerView(adapter,recyclerView)

        val db= FirebaseDatabase.getInstance().reference
        val valueEventListener= object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(i in snapshot.children){
                    itemList.add(Category(i.key.toString(), i.child("image").getValue().toString()))
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Error !", Toast.LENGTH_SHORT).show()
            }

        }

        db.child("category").addListenerForSingleValueEvent(valueEventListener)
    }

    fun setupRecyclerView(adapter: CategoryAdapter, recyclerView: RecyclerView){
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter= adapter
    }
}