package in.mvi.sqlitefirst;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDbHelper;

    // declare variables for XML component...
    EditText editName;
    EditText editSurname;
    EditText editMarks;
    EditText editId;
    Button addButton;
    Button viewAllButton;
    Button updateStudentButton;
    Button deleteStudentButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDbHelper = new DatabaseHelper(this);

        // defined the XML components ex- button, textView, images etc.....
        editName = findViewById(R.id.student_name);
        editSurname = findViewById(R.id.student_surname);
        editMarks = findViewById(R.id.student_marks);
        editId = findViewById(R.id.student_id);
        addButton = findViewById(R.id.addButton);
        viewAllButton = findViewById(R.id.viewButton);
        updateStudentButton = findViewById(R.id.updateButton);
        deleteStudentButton = findViewById(R.id.deleteButton);

        // Calling the  methods
        addData();
        viewAllStudent();
        updateStudents();
        deleteStudents();
    }

    public void addData(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean isInserted =  myDbHelper.insertData(editName.getText().toString(),
                        editSurname.getText().toString(),
                        editMarks.getText().toString());

               if (isInserted ==true){
                   Toast.makeText(MainActivity.this, "Data Inserted successfully...", Toast.LENGTH_LONG).show();
               }
               else{
                   Toast.makeText(MainActivity.this, "Data not inserted...", Toast.LENGTH_LONG).show();
               }
            }
        });
    }

    public void  viewAllStudent(){

        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Cursor res =  myDbHelper.getAllStudent();

               if (res.getCount()== 0){

                   // Show message..
                   showMessage ("Error", "Nothing Found");
                   return;
               }

               StringBuffer buffer = new StringBuffer();
               while (res.moveToNext()){
                   buffer.append("Id: " + res.getString(0)+ "\n");
                   buffer.append("Name: " + res.getString(1)+ "\n");
                   buffer.append("Surname: " + res.getString(2)+ "\n");
                   buffer.append("Marks: " + res.getString(3)+ "\n\n");
               }
               // Show all Data..
               showMessage("Data", buffer.toString());

            }
        });
    }
    public void showMessage(String title, String message ){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("title");
        builder.setMessage(message);
        builder.show();
    }

    public void  updateStudents(){
        updateStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isUpdated = myDbHelper.updateStudentData(editId.getText().toString(),
                        editName.getText().toString(),
                        editSurname.getText().toString(),
                        editMarks.getText().toString()
                );
                if (isUpdated== true){
                    Toast.makeText(MainActivity.this, "Data updated successfully...", Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(MainActivity.this, "Data not updated...", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void deleteStudents(){

        deleteStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows = myDbHelper.deleteStudetData(editId.getText().toString());

                if (deletedRows>0){
                    Toast.makeText(MainActivity.this, "Data deleted successfully...", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data not deleted...", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}
