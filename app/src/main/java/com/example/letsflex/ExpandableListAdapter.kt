package com.example.letsflex

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView
import android.widget.Toast

class ExpandableListAdapter internal constructor(private val context: Context, private val exerciseNameList: List<String>, private val exerciseDescriptionList: HashMap<String, List<String>>, private val exerciseDifficulty: String = "intermediate"):
    BaseExpandableListAdapter() {
    override fun getChildrenCount(groupPosition: Int): Int {
        return this.exerciseDescriptionList[this.exerciseNameList[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return exerciseNameList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return this.exerciseDescriptionList[this.exerciseNameList[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return exerciseNameList.size
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val exerciseName = getGroup(groupPosition) as String

        if(convertView == null) {
            //Find the premade layout xml file for the list items (exercise_name_list)
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.exercise_name_list, null)
        }

        //Assign the name to the element inside the premade layout xml file
        val listExerciseName = convertView!!.findViewById<TextView>(R.id.listExerciseName)
        listExerciseName.text = exerciseName

        return convertView
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val exerciseDescription = getChild(groupPosition, childPosition) as String

        if(convertView == null) {
            //Find the premade layout xml file for the list items (exercise_description_list)
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.exercise_description_list, null)
        }

        //Assign the description to the element inside the premade layout xml file
        val listExerciseDescription = convertView!!.findViewById<TextView>(R.id.listExerciseDetails)
        listExerciseDescription.text = exerciseDescription

        val listExerciseDifficulty = convertView!!.findViewById<TextView>(R.id.listExerciseDifficulty)
        listExerciseDifficulty.text = exerciseDifficulty

        val listExerciseAddB = convertView!!.findViewById<TextView>(R.id.listExerciseAddButton)
        val exerciseName = getGroup(groupPosition) as String
        listExerciseAddB.contentDescription = exerciseName

        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

}