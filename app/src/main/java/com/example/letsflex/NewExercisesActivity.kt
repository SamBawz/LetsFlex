package com.example.letsflex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.letsflex.databinding.ActivityNewExercisesBinding

class NewExercisesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewExercisesBinding

    private lateinit var listViewAdapter: ExpandableListAdapter
    private lateinit var exerciseNameList : List<String>
    private lateinit var exerciseDescriptionList: HashMap<String, List<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewExercisesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createList()

        listViewAdapter = ExpandableListAdapter(this, exerciseNameList, exerciseDescriptionList)
        binding.expandableListView.setAdapter(listViewAdapter)
    }

    private fun createList() {
        exerciseNameList  = ArrayList()
        exerciseDescriptionList = HashMap()

        (exerciseNameList as ArrayList<String>).add("Child pose")
        (exerciseNameList as ArrayList<String>).add("Downward Facing Dog")
        (exerciseNameList as ArrayList<String>).add("Mountain Pose")
        (exerciseNameList as ArrayList<String>).add("Warrior I")
        (exerciseNameList as ArrayList<String>).add("Warrior II")
        (exerciseNameList as ArrayList<String>).add("Extended Side Angle")
        (exerciseNameList as ArrayList<String>).add("Triangle Pose")

        val description1: MutableList<String> = ArrayList()
        description1.add("This calming pose is a good default pause position. You can use child’s pose to rest and refocus before continuing to your next pose. It gently stretches your lower back, hips, thighs, knees and ankles and relaxes your spine, shoulders and neck.")

        val description2: MutableList<String> = ArrayList()
        description2.add("Beginners often lean too far forward in this posture, making it more like a plank. Instead, remember to keep your weight mostly in your legs and reach your hips high, with your heels stretching toward the floor (they do not need to touch the floor).")

        val description3: MutableList<String> = ArrayList()
        description3.add("The alignment in Mountain pose draws a straight line from the crown of your head to your heels, with the shoulders and pelvis stacked along the line. Every person's body is different, so focus on rooting down with your feet and lengthening up with your spine.")

        val description4: MutableList<String> = ArrayList()
        description4.add("The critical thing to remember in Warrior I is that the hips face forward. Think of your hip points as headlights—they should be roughly parallel with the front of your mat. This may require you to take a wider stance.")

        val description5: MutableList<String> = ArrayList()
        description5.add("Unlike Warrior I, in Warrior II, the hips face the side of the mat. The hips and shoulders open to the side when moving from Warrior I to Warrior II.\n" +
                "\n" +
                "You'll also rotate your back foot, angling your toes at about 45 degrees. In both Warrior poses, aim to keep your front knee stacked over the ankle. Your front toes face forward.")

        val description6: MutableList<String> = ArrayList()
        description6.add("One modification of Extended Side Angle Pose is to bring your forearm to your thigh instead of placing your hand on the floor. It should rest lightly on your thigh and not bear much weight. This modification enables you to keep your shoulders open. You can also place your hand on a block.")

        val description7: MutableList<String> = ArrayList()
        description7.add("Triangle offers many benefits: Strength (in the legs), flexibility (in the groin, hamstrings, and hips, as well as opening the chest and shoulders), and balance.")

        exerciseDescriptionList[exerciseNameList[0]] = description1
        exerciseDescriptionList[exerciseNameList[1]] = description2
        exerciseDescriptionList[exerciseNameList[2]] = description3
        exerciseDescriptionList[exerciseNameList[3]] = description4
        exerciseDescriptionList[exerciseNameList[4]] = description5
        exerciseDescriptionList[exerciseNameList[5]] = description6
        exerciseDescriptionList[exerciseNameList[6]] = description7
    }

    fun addExercise(view: View) {
        Toast.makeText(this, view.contentDescription.toString() + " has been added to your exercises!", Toast.LENGTH_SHORT).show()
    }
}