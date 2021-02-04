package com.e.jarvis.models.modelsQuiz

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.e.jarvis.models.generics.GenericResults
import java.io.Serializable

@Entity(tableName = "Quiz")
class Quiz(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val question: String = "",
    @Embedded(prefix = "Alt1_")
    val alternative1: Alternatives = Alternatives(),
    @Embedded(prefix = "Alt2_")
    val alternative2: Alternatives = Alternatives(),
    @Embedded(prefix = "Alt3_")
    val alternative3: Alternatives = Alternatives(),
    @Embedded(prefix = "Alt4_")
    val alternative4: Alternatives = Alternatives()
) : Serializable