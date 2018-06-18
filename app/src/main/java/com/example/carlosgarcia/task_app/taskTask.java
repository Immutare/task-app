package com.example.carlosgarcia.task_app;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class taskTask {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "strLongDesc")
    private String strLongDesc;
    @ColumnInfo(name = "strShortDesc")
    private String strShortDesc;
    @ColumnInfo(name = "intPercentage")
    private int intPercentage;

    public taskTask( //
            String strShortDesc,
            String strLongDesc,
            int intPercentage
        )
    {
        this.strShortDesc = strShortDesc;
        this.strLongDesc = strLongDesc;
        this.intPercentage = intPercentage;
    }

    public int getIntPercentage()
    {
        return intPercentage;
    }

    public String getStrLongDesc()
    {
        return strLongDesc;
    }

    public String getStrShortDesc()
    {
        return strShortDesc;
    }

    public void setIntPercentage(int intPercentage_I)
    {
        this.intPercentage = intPercentage_I;
    }

    public void setStrShortDesc(String strShortDesc_I)
    {
        this.strShortDesc = strShortDesc_I;
    }

    public void setStrLongDesc(String strLongDesc_I)
    {
        this.strLongDesc = strLongDesc_I;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
