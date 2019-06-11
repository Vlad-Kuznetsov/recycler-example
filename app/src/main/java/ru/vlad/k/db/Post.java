package ru.vlad.k.db;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Post {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String content;


    @Ignore
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
