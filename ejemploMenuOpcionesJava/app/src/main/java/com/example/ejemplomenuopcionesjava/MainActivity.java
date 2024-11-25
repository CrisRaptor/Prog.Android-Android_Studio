package com.example.ejemplomenuopcionesjava;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final int MnOp1=1;
    private static final int MnOp2=2;
    private static final int MnOp2_1=4;
    private static final int MnOp2_2=5;
    private static final int MnOp3=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //Sin Submenus
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(menu.NONE,MnOp1,menu.NONE,"Opcion A desde Java");
//        menu.add(menu.NONE,MnOp2,menu.NONE,"Opcion B desde Java");
//        menu.add(menu.NONE,MnOp3,menu.NONE,"Opcion C desde Java");
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int opcion = item.getItemId();
//        switch (item.getItemId()){
//            case MnOp1:
//                Toast.makeText(this, "Menu "+item.getTitle(), Toast.LENGTH_SHORT).show();
//                break;
//       case MnOp2:
//        Toast.makeText(this, "Menu "+item.getTitle(), Toast.LENGTH_SHORT).show();
//        break;
//        case MnOp3:
//        Toast.makeText(this, "Menu "+item.getTitle(), Toast.LENGTH_SHORT).show();
//        break;
//    }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(menu.NONE,MnOp1,menu.NONE,"Opcion A desde Java");
        SubMenu smenu = menu.addSubMenu(menu.NONE,MnOp2,menu.NONE,"Opcion B desde Java");
        smenu.add(menu.NONE,MnOp2_1,menu.NONE,"Opcion B1 desde Java");
        smenu.add(menu.NONE,MnOp2_2,menu.NONE,"Opcion B2 desde Java");
        menu.add(menu.NONE,MnOp3,menu.NONE,"Opcion C desde Java");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int opcion = item.getItemId();
        switch (item.getItemId()){
            case MnOp1:
                Toast.makeText(this, "Menu "+item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case MnOp2:
                Toast.makeText(this, "Menu "+item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case MnOp3:
                Toast.makeText(this, "Menu "+item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case MnOp2_1:
                Toast.makeText(this, "SubMenu "+item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case MnOp2_2:
                Toast.makeText(this, "SubMenu "+item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}