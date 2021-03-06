package com.example.lab9

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.net.URL

class ProjectInfoFragment(
    private val project: Project = Project()
) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_project_info, container, false)
        if(project.avatarURL != ""){
            GlobalScope.launch {
                val buf = URL(project.avatarURL).readBytes()
                val bitmap = BitmapFactory.decodeByteArray(buf, 0, buf.size);
                MainScope().launch{
                    view.findViewById<ImageView>(R.id.avatarImageView).setImageBitmap(bitmap)
                }
            }
        }
        view.findViewById<TextView>(R.id.projectNameTextView).text = "Name: ${project.name}"
        view.findViewById<TextView>(R.id.projectDescriptionTextView).text = "Description: ${project.description}"
        view.findViewById<TextView>(R.id.projectURLtextView).text = "${project.projectURL}"
        view.findViewById<TextView>(R.id.watchersCountTextView).text = "Watches: ${project.watchersCount}"
        view.findViewById<TextView>(R.id.sizeTextView).text = "Size: ${project.size}"
        view.findViewById<TextView>(R.id.createdAtTextView).text = "Created at: ${project.createdAt}"
        view.findViewById<TextView>(R.id.updatedAtTextView).text = "Last update at: ${project.updatedAt}"
        view.findViewById<TextView>(R.id.licenseTextView).text = "License: ${project.license}"
        return view
    }
}