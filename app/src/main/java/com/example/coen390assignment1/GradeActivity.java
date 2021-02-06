package com.example.coen390assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GradeActivity extends AppCompatActivity {

    private LinearLayout mainLinerLayout;
    private ArrayList<Assignment> assignments;//each course's assignments
    private ArrayList<ArrayList<Assignment>> A;//all course's assignments
    private TextView textView;
    private TextView temp;
    private ArrayList<TextView> textViewA;//To store each course's TextView
    private ArrayList<ArrayList<TextView>> textViewB;//To store all course's TextView
    private TextView averageView;//average view of each average text
    private ArrayList<Float> averageArray;
    private ArrayList<TextView> averageViewB;// all average views
    private boolean wantLetter = true;// a flag used to switch grade from letter to number or no to letter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        setupUI();
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        textViewB = new ArrayList<ArrayList<TextView>>();
        A = new ArrayList<ArrayList<Assignment>>();
        averageArray = new ArrayList<Float>();
        averageViewB = new ArrayList<TextView>();
        float average;
        int sum;
        for(int j=0; j<5; j++) {
            Course course = Course.generateRandomCourse();
            assignments = course.getAssignments();
            A.add(assignments);
            textView = new TextView(this);
            textView.setText(course.getCourseTitle());
            mainLinerLayout.addView(textView);
            textViewA = new ArrayList<TextView>();
            textViewB.add(textViewA);

            averageView = new TextView(this);
            average = 0;
            sum = 0;

            for (int i = 0; i < assignments.size(); i++) {
                temp = new TextView(this);
                String text = assignments.get(i).getAssignmentTitle() + " " + assignments.get(i).getAssignmentGrade();
                sum += assignments.get(i).getAssignmentGrade();
                temp.setText(text);
                textViewA.add(temp);
                mainLinerLayout.addView(temp);
            }
            if(assignments.size()!=0 ){
                average = ((float)sum/(float)assignments.size());
                String aveText = "Average: "+average;
                averageView.setText(aveText);
                mainLinerLayout.addView(averageView);
            }
            averageViewB.add(averageView);
            averageArray.add(average);

        }
    }

    private void setupUI(){
         mainLinerLayout = (LinearLayout) this.findViewById(R.id.MyTable);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.grade_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.convert_grade:
                if (wantLetter){
                    for(int j=0; j<5; j++){
                        for (int i = 0; i < A.get(j).size(); i++) {
                            int grade =A.get(j).get(i).getAssignmentGrade();
                            String c = transferGrade(grade);
                            String text = A.get(j).get(i).getAssignmentTitle() + " " + c;
                            textViewB.get(j).get(i).setText(text);
                        }
                        float f = averageArray.get(j).floatValue();
                        String  c = transferGrade(f);
                        String aveText = "Average: "+c;
                        averageViewB.get(j).setText(aveText);
                    }
                    wantLetter = false;
                }else{
                    for(int j=0; j<5; j++) {
                        for (int i = 0; i < A.get(j).size(); i++) {
                            String text = A.get(j).get(i).getAssignmentTitle() + " " + A.get(j).get(i).getAssignmentGrade();
                            textViewB.get(j).get(i).setText(text);
                        }
                        float f = averageArray.get(j).floatValue();
                        String aveText = "Average: "+f;
                        averageViewB.get(j).setText(aveText);
                    }
                    wantLetter = true;
                }

                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private String transferGrade(float grade){
        if (grade >= 90 && grade <= 100)
            return "A+";
        else if (grade >= 85 && grade <90)
            return "A";
        else if (grade >= 80 && grade <85)
            return "A-";
        else if (grade >= 77 && grade <80)
            return "B+";
        else if (grade >= 73 && grade < 77)
            return "B";
        else if (grade >= 70 && grade < 73)
            return "B-";
        else if (grade >= 67 && grade <70)
            return "C+";
        else if (grade >= 63 && grade <67)
            return "C";
        else if (grade >= 60 && grade < 63)
            return "C-";
        else if (grade >= 57 && grade < 60)
            return "D+";
        else if (grade >= 53 && grade < 57)
            return "D";
        else if (grade >= 50 && grade <53)
            return "D-";
        else if (grade>=0 && grade<50)
            return "F";
        else
            return "DNE";
    }
}
