package com.example.whatscooking.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.test.core.app.ApplicationProvider;

import com.example.whatscooking.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MediaOperations {

    /*
      https://www.droidcon.com/news-detail?content-id=/repository/collaboration/Groups/spaces/droidcon_hq/Documents/public/news/android-news/Working%20with%20Scoped%20Storage
      Here we store the placeholder image but on actual case it will come from the camera app
      as a uri and we will read from onActivityResult()
     */
    public static URI storeImage(Context context, int drawableId, String imageName) {

        File path = new File(context.getFilesDir(), Constants.IMAGES_DIR);
        if (!path.exists()) {
            path.mkdirs();
        }

        File imageFile = new File(path, imageName);

        Bitmap bitmap;
        bitmap = BitmapFactory.decodeResource(context.getResources(), drawableId);
        URI imageUri = null;
        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            imageUri = imageFile.toURI();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return imageUri;
    }


    public static String getUniqueImageName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
        return "JPEG_" + timeStamp + ".jpg";
    }

    public static File createImageFile(Context context) {
        String imageFileName = getUniqueImageName();
        File image = new File(context.getFilesDir(), imageFileName);
        return image;
    }

    public static String createPlaceholderImage(Context context) {
        String imageName = "placeholder_image";
        File path = new File(context.getFilesDir(), Constants.IMAGES_DIR);
        File imageFile = new File(path, "placeholder_image");
        if (imageFile.exists()) {
            return imageFile.toURI().toString();
        } else {
            return MediaOperations.storeImage(context, R.drawable.placeholder_image, imageName)
                    .toString();
        }
    }

    /* First we create the file with createImageFile and then the URI.
       Then we pass the URI as a parameter to the ACTION_IMAGE_PICTURE
       The rest in here https://developer.android.com/training/camera/photobasics
     */
//    static final int REQUEST_TAKE_PHOTO = 1;

//    private void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // Ensure that there's a camera activity to handle the intent
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//            ...
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                Uri photoURI = FileProvider.getUriForFile(this,
//                        "com.example.android.fileprovider",
//                        photoFile);
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//            }
//        }
//    }


    public static void saveImageInStorage(Context context) throws IOException {
        File imageFile = createImageFile(context);
    }
}
