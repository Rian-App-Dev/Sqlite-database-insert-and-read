package com.example.gfgdatabaseapp2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gfgdatabaseapp2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addNameBtn.setOnClickListener {
            val name = binding.enterName.text.toString()
            val age = binding.enterAge.text.toString().toInt()
            val db = DBHelper(this@MainActivity,null)
            db.insert(name, age)
            binding.enterName.text.clear()
            binding.enterAge.text.clear()
            Toast.makeText(this@MainActivity, "$name added to database", Toast.LENGTH_SHORT).show()
        }
        binding.showNameBtn.setOnClickListener {
            val db = DBHelper(this,null)
            val cursor = db.getName()

            cursor!!.moveToFirst()
            binding.nameTv.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME))+"\n")
            binding.ageTv.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_AGE))+"\n")
            while (cursor.moveToNext()){
                binding.nameTv.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME))+"\n")
                binding.ageTv.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_AGE))+"\n")
            }
            cursor.close()
        }
    }
}