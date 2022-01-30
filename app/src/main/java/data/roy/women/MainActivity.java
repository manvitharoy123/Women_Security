package data.roy.women;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {
MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation=findViewById(R.id.bottom_navigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.location));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_baseline_person_24));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment=null;
switch(item.getId()){


    case 2:
        fragment=new HomeFragment();
        break;
    case 3:
        fragment=new LocationFragment();
        break;

    case 4:
        fragment=new ParentFragment();
        break;
}

                loadFragment(fragment);

            }




        });


        bottomNavigation.setCount(1,"10");
        bottomNavigation.show(2, true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(),"you clicked" + item.getId(),Toast.LENGTH_SHORT).show();
            }
        });


        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(),"you reselected " + item.getId(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
    }

}