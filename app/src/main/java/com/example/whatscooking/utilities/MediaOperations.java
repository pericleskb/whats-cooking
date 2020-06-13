package com.example.whatscooking.utilities;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MediaOperations {

    /*
      https://www.droidcon.com/news-detail?content-id=/repository/collaboration/Groups/spaces/droidcon_hq/Documents/public/news/android-news/Working%20with%20Scoped%20Storage
      Here we store the placeholder image but on actual case it will come from the camera app
      as a uri and we will read from onActivityResult()
     */
    public static Uri storeImage(Context context, int drawableId) throws IOException {

        ContentResolver resolver = context.getContentResolver();

        Bitmap bitmap;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(resolver, uri));
//        } else {
//            InputStream is = resolver.openInputStream(uri);
//            bitmap = BitmapFactory.decodeStream(is);
//        }

        bitmap = BitmapFactory.decodeResource(context.getResources(), drawableId);

        Uri imageCollection = MediaStore.Images.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL_PRIMARY);

        ContentValues newImageDetails = new ContentValues();
        newImageDetails.put(MediaStore.Images.Media.DISPLAY_NAME, getUniqueImageName());
        newImageDetails.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        newImageDetails.put(MediaStore.Images.Media.RELATIVE_PATH, Constants.IMAGES_RELATIVE_DIR);
        newImageDetails.put(MediaStore.Images.Media.IS_PENDING, 1);

        Uri imageUri = resolver
                .insert(imageCollection, newImageDetails);

        OutputStream os = resolver.openOutputStream(imageUri);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, os);

        newImageDetails.clear();
        newImageDetails.put(MediaStore.Images.Media.IS_PENDING, 0);
        resolver.update(imageUri, newImageDetails, null, null);
        return imageUri;
    }

    public static void setImageToView(Context context, String imageUri, ImageView imageView) {

        Bitmap bitmap = null;
        if (imageUri != null) {
            //TODO get scaled down image
            Uri uri = Uri.parse(imageUri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
                bitmap = null;
            }
        }
        imageView.setImageBitmap(bitmap);
    }


    public static String getUniqueImageName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return "JPEG_" + timeStamp;
    }

    public static File createImageFile(Context context) throws IOException {
        String imageFileName = getUniqueImageName();
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        // Save a file: path for use with ACTION_VIEW intents
//        currentPhotoPath = image.getAbsolutePath();
        return image;

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
