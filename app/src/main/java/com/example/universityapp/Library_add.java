package com.example.universityapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.net.URI;

public class Library_add extends AppCompatActivity {
    Spinner category;
    DatabaseReference dbref;
    EditText title, author, ISBN;
    Button add, upload;
    ImageView cover;
    String  sId, id,categoryString, imgUrlDbString, testUrl;
    Uri imgUrl;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_add);
        dbref = FirebaseDatabase.getInstance().getReference("_LibraryBooks_");
        category = findViewById(R.id.spi_LibraryAdd_category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Library_add.this, R.array.library_book_categories, R.layout.support_simple_spinner_dropdown_item);
        category.setAdapter(adapter);
        title = findViewById(R.id.et_libraryAdd_title);
        author = findViewById(R.id.et_libraryAdd_author);
        ISBN = findViewById(R.id.et_libraryAdd_ISBN);
        add = findViewById(R.id.btn_libraryAdd_add);
        cover = findViewById(R.id.img_libraryAdd_cover);
        upload = findViewById(R.id.btn_libraryAdd_upload);

        add.setVisibility(View.INVISIBLE);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryString = category.getSelectedItem().toString();
                LibraryBook book = new LibraryBook(title.getText().toString(), author.getText().toString(), ISBN.getText().toString(), categoryString, false, imgUrlDbString, sId, id);
                dbref.child(id).setValue(book).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Library_add.this, "Book added successfully to the Library", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Library_add.this, "Adding book to Library failed", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = dbref.push().getKey();
                StorageReference sRef = FirebaseStorage.getInstance().getReference("images");
                StorageReference reference = sRef.child(id+"."+getExtension(imgUrl));
                reference.putFile(imgUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imgUrlDbString = uri.toString();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                reference.delete();
                                Toast.makeText(Library_add.this, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Library_add.this, "Image upload failed!", Toast.LENGTH_LONG).show();
                    }
                });

                add.setVisibility(View.VISIBLE);
            }

        });

        cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, 102);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode,data);

        if(requestCode == 102 && resultCode == RESULT_OK && data.getData() != null)
        {
            Picasso.get().load(data.getData()).fit().into(cover);
            imgUrl = data.getData();

        }
    }

    private String getExtension (Uri path)
    {
        ContentResolver resolver = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(resolver.getType(path));
    }


}